/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package fr.mjeu.dcmm.monitoring;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.DcmManager;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.util.CheckerUtil;

/**
 * Class used to monitor a directory and read all the DICOM files from it
 * 
 * As mentionned above, this class has been reused and modified from existing one.
 * 
 * @author Maxime
 *
 */
public class WatchDir {

	static Logger logger = LoggerFactory.getLogger(WatchDir.class);
	
	private static final String DEBUG_EVENT = "{} : {}";
	private static final String DEBUG_REGISTER_DIR = "register directory : {}";
	private static final String DEBUG_REGISTER_UPDATE_DIR = "update : {} -> {}";
	private static final String DEBUG_SCANNING = "scanning {}";
	private static final String DEBUG_SCANNING_DONE = "scanning done";
	private static final String DEBUG_WAITING_FOR_INPUT = "waiting for input in directory {}";
	private static final String ERROR_WATCH_KEY_NOT_RECOGNIZED = "WatchKey not recognized";
	private static final String ERROR_PROCESSING_FILE = "error processing file {}";
	private static final String WARN_OVERFLOW = "events might have been lost or discarded";
	
    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final boolean recursive;
    private boolean trace = false;
    private DcmManager dcmManager;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
    	// we need to register to creation events to treat DICOM files from monitored directory
        WatchKey key = dir.register(watcher, ENTRY_CREATE);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
            	logger.debug(DEBUG_REGISTER_DIR, dir);
            } else {
                if (!dir.equals(prev)) {
                    logger.debug(DEBUG_REGISTER_UPDATE_DIR, prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    public WatchDir(Path dir, boolean recursive, DcmManager dcmManager) throws DcmException {
        CheckerUtil.checkNotNull(dir);
    	CheckerUtil.checkFolderExists(dir.toString());
    	CheckerUtil.checkNotNull(dcmManager);
    	
    	try {
    		this.watcher = FileSystems.getDefault().newWatchService();
    	} catch (IOException ie) {
    		throw new DcmException(DcmExceptionMessage.ERROR_INITIALIZING_WATCHER_SERVICE.getMessage(), ie);
    	}
    	
    	
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;
        this.dcmManager = dcmManager;

        try {
	        if (recursive) {
	            logger.debug(DEBUG_SCANNING, dir);
	            registerAll(dir);
	            logger.debug(DEBUG_SCANNING_DONE);
	            logger.debug(DEBUG_WAITING_FOR_INPUT, dir);
	        } else {
	            register(dir);
	        }
        } catch (IOException ie) {
    		throw new DcmException(DcmExceptionMessage.ERROR_REGISTER_DIR.getMessage() + dir.toString(), ie);
    	}
        

        // enable trace after initial registration
        this.trace = true;
    }

    /**
     * Process all events for keys queued to the watcher
     */
    public void processEvents() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                logger.error(ERROR_WATCH_KEY_NOT_RECOGNIZED);
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                @SuppressWarnings("rawtypes")
				WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                	logger.warn(WARN_OVERFLOW);
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                logger.debug(DEBUG_EVENT, event.kind().name(), child);
                try {
                	this.dcmManager.notifyInputFileToProcess(child);
                } catch (DcmException d){
                	logger.error(ERROR_PROCESSING_FILE, child.toString());
                	logger.error(d.getMessage());
                	if(d.getCause() != null) {
                		logger.error(d.getCause().getMessage());
                	}
                }

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
}