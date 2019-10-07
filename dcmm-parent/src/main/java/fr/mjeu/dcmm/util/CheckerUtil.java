package fr.mjeu.dcmm.util;

import java.io.File;
import java.nio.file.Path;

import org.springframework.util.StringUtils;

import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;

/**
 * Checker util class
 * 
 * @author Maxime
 *
 */
public class CheckerUtil {
	
	public static final String DCM_EXTENSION = ".dcm";
	public static final String PNG_EXTENSION = ".png";

	/**
	 * Compare String object to empty string or null
	 * @param s the string
	 * @throws CheckerException
	 */
	public static void checkNotEmpty(String s) throws CheckerException {
		
		if(StringUtils.isEmpty(s)) {
			
			throw new CheckerException(DcmExceptionMessage.ERROR_NULL_OR_EMPTY.getMessage());
			
		}
		
	}
	
	/**
	 * Compare object with null
	 * @param o the object
	 * @throws CheckerException
	 */
	public static void checkNotNull(Object o) throws CheckerException {
		
		if(o == null) {
			
			throw new CheckerException(DcmExceptionMessage.ERROR_NULL.getMessage());
			
		}
		
	}
	
	/**
	 * Check if file exists and is readable and writable
	 * @param folderPathStr string path of the parent folder
	 * @param filename filename
	 * @return Path object corresponding to file
	 * @throws CheckerException
	 */
	public static Path checkFileExists(String folderPathStr, String filename) throws CheckerException {
		
		CheckerUtil.checkNotEmpty(folderPathStr);
		CheckerUtil.checkNotEmpty(filename);
		
		Path p = null;
		StringBuilder sb = new StringBuilder();
		sb.append(DcmExceptionMessage.ERROR_FOLDER_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE.getMessage())
			.append(folderPathStr);
		
		try {
			p = DcmFileUtil.getPath(folderPathStr, filename);
		} catch (DcmException de){
			throw new CheckerException(sb.toString());
		}
		
		checkFileExistsFromPath(p);
		
		return p;
		
	}
	
	/**
	 * Check if file exists and is readable and writable
	 * @param p Path object corresponding to file
	 * @throws CheckerException
	 */
	public static void checkFileExistsFromPath(Path p) throws CheckerException {
		
		CheckerUtil.checkNotNull(p);
		
		File f = null;
		f = p.toFile();
		
		if(f == null || !f.exists() || f.isDirectory() || !f.canRead() || !f.canWrite()) {
			
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_FILE_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE.getMessage())
				.append(p.toString());
			throw new CheckerException(sb.toString());
			
		}
		
	}
	
	/**
	 * Check file name with DICOM extension
	 * @param filename the filename
	 * @throws CheckerException
	 */
	public static void checkFilenameDcmExtension(String filename) throws CheckerException {
		
		CheckerUtil.checkNotNull(filename);
		CheckerUtil.checkNotEmpty(filename);
		
		if(!filename.endsWith(DCM_EXTENSION)) {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_FILENAME_EXTENSION_DCM.getMessage())
				.append(filename);
			throw new CheckerException(sb.toString());
		}
		
	}
	
	/**
	 * Check file name with PNG extension
	 * @param filename the filename
	 * @throws CheckerException
	 */
	public static void checkFilenamePngExtension(String filename) throws CheckerException {
		
		CheckerUtil.checkNotNull(filename);
		CheckerUtil.checkNotEmpty(filename);
		
		if(!filename.endsWith(PNG_EXTENSION)) {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_FILENAME_EXTENSION_PNG.getMessage())
				.append(filename);
			throw new CheckerException(sb.toString());
		}
		
	}
	
	/**
	 * Check if folder exists and is readable and writable
	 * @param folderPathStr string path of the folder
	 * @return Path object corresponding to folder
	 * @throws CheckerException
	 */
	public static Path checkFolderExists(String folderPathStr) throws CheckerException {
		
		CheckerUtil.checkNotEmpty(folderPathStr);
		
		Path p = null;
		StringBuilder sb = new StringBuilder();
		sb.append(DcmExceptionMessage.ERROR_FOLDER_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE.getMessage())
			.append(folderPathStr);
		
		try {
			p = DcmFileUtil.getPath(folderPathStr);
		} catch (DcmException de){
			throw new CheckerException(sb.toString());
		}
		File d = null;
		
		if(p != null) {
			d = p.toFile();
		}
		
		if(d == null || !d.exists() || !d.isDirectory() || !d.canRead() || !d.canWrite()) {
			throw new CheckerException(sb.toString());
		}
		
		return p;
		
	}
	
	
}
