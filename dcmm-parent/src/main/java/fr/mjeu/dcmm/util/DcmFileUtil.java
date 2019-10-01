package fr.mjeu.dcmm.util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

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
	public static File getFileFromPathStr(String pathStr) {
		
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
	public static Path getPath(String absoluteFolderPathString) {
		
		return FileSystems.getDefault().getPath(absoluteFolderPathString);
		
	}
	
	/**
	 * Constructs file Path object from absolute folder path string and filename
	 * @param absoluteFolderPathString absolute folder path string
	 * @param filename
	 * @return Path object
	 */
	public static Path getPath(String absoluteFolderPathString, String filename) {
		
		return FileSystems.getDefault().getPath(absoluteFolderPathString, filename);
		
	}
	
	
}
