package fr.mjeu.dcmm.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;

/**
 * DICOM file util class
 * 
 * @author Maxime
 *
 */
public class DcmFileUtil {

	/**
	 * Constructs File from Path
	 * @param path the path
	 * @return file file corresponding to the path
	 */
	public static File getFileFromPath(Path path) {
		
		File f = null;
		
		if(path != null) {
			f = path.toFile();
		}
		
		return f;
			
	}
	
	/**
	 * Constructs File from Path
	 * @param path the path
	 * @return file file corresponding to the path
	 */
	public static File getFileFromPathStr(String pathStr) throws DcmException {
		
		File f = null;
		Path p = getPath(pathStr);
		
		if(p != null) {
			f = getFileFromPath(p);
		}
		
		return f;
			
	}
	
	/**
	 * Constructs folder Path object from absolute folder path string
	 * @param absoluteFolderPathString absolute folder path string
	 * @return Path object
	 */
	public static Path getPath(String absoluteFolderPathString) throws DcmException {
		
		Path p = null;
		
		CheckerUtil.checkNotEmpty(absoluteFolderPathString);
		
		try {
			p = FileSystems.getDefault().getPath(absoluteFolderPathString);
		} catch (InvalidPathException ipe) {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_INVALID_PATH_EXCEPTION)
				.append(absoluteFolderPathString);
			throw new DcmException(sb.toString(), ipe);
		}
		
		return p;
		
	}
	
	/**
	 * Constructs file Path object from absolute folder path string and filename
	 * @param absoluteFolderPathString absolute folder path string
	 * @param filename
	 * @return Path object
	 */
	public static Path getPath(String absoluteFolderPathString, String filename) throws DcmException {
		
		Path p = null;
		
		CheckerUtil.checkNotEmpty(absoluteFolderPathString);
		CheckerUtil.checkNotEmpty(filename);
		
		try {
			p = FileSystems.getDefault().getPath(absoluteFolderPathString, filename);
		} catch (InvalidPathException ipe) {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_INVALID_PATH_EXCEPTION)
				.append(absoluteFolderPathString);
			throw new DcmException(sb.toString(), ipe);
		}
		
		return p;
	}
	
	
}
