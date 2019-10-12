package fr.mjeu.dcmm.util;

import java.nio.file.Path;

import fr.mjeu.dcmm.exception.DcmException;

/**
 * Class used to get output file path according to the application properties
 * @author Maxime
 *
 */
public class DcmOutFilePathUtil {

	public static String DCM_EXTENSION = ".dcm";
	
	/**
	 * Get output file according to properties
	 * @param inFilePath path object of input file
	 * @param outFilenameSuffix suffix to add before extension in output filename
	 * can be empty but not null
	 * @param outFolderPath path object of output folder
	 * @return path object of output file
	 * @throws DcmException
	 */
	public static Path getOutFilePath(
			Path inFilePath,
			String outFilenameSuffix,
			Path outFolderPath
			) throws DcmException {
		
		Path outFilePath = null;
		
		CheckerUtil.checkFileExistsFromPath(inFilePath);
		CheckerUtil.checkNotNull(outFilenameSuffix);
		CheckerUtil.checkNotNull(outFolderPath);
		CheckerUtil.checkFolderExists(outFolderPath.toString());
		
		StringBuilder newFilenameBuilder = new StringBuilder();
		newFilenameBuilder.append(sanitizeDcmExtension(inFilePath.getFileName().toString()));
		newFilenameBuilder.append(outFilenameSuffix);
		newFilenameBuilder.append(DCM_EXTENSION);
		
		outFilePath = DcmFileUtil.getPath(outFolderPath.toString(), newFilenameBuilder.toString());
		
		return outFilePath;
			
	}
	
	/**
	 * Sanitize Dcm ".dcm" extension
	 * Sanitize only the last occurence of ".dcm"
	 * @param filename with extension
	 * @return filename without extension
	 */
	private static String sanitizeDcmExtension(String filename) {
		
		int lastIndex = filename.lastIndexOf(DCM_EXTENSION);
		if(lastIndex != -1) {
			return filename.substring(0, lastIndex);
		}
		return filename;
	}
	
}
