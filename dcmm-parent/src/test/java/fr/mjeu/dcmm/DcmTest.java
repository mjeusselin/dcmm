package fr.mjeu.dcmm;


import java.io.File;
import java.net.URI;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.CheckerUtilTest;

public class DcmTest {

	protected static String S = File.separator;
	protected static String ERROR_UNABLE_TO_GET_ABSOLUTE_PATH_STR = "Unable to get absolute path string of test resource : ";
	protected static String FILENAME_EXTENSION_DCM_OK = ".dcm";
	protected static String FILENAME_EXTENSION_DCM_KO = ".png";
	protected static String FILENAME_EXAMPLE_15_MO = "ge-0001-0000-00000000.dcm";
	protected static String FILENAME_EXAMPLE_21_MO = "planmed-200064.424.dcm";
	protected static String FILENAME_EXAMPLE_28_MO = "hologic-MG02.dcm";
	protected static String FILENAME_EXAMPLE_57_MO = "fuji-50333032.466243176.dcm";
	protected static String FILENAME_FILE_EXISTS_OK = "file.dcm";
	protected static String FILENAME_FILE_DOES_NOT_EXIST_KO = "fileDoesNotExist.dcm";
	protected static String FILENAME_EXAMPLE_LOGO = "herami-logo.png";
	protected static String PATH_STR_FOLDER_EXAMPLES_OK = "in"+S+"phantoms-2d";
	protected static String PATH_STR_FOLDER_EXAMPLES_IMAGES = "in"+S+"images";
	protected static String PATH_STR_FOLDER_EXISTS_OK = "util";
	protected static String PATH_STR_FOLDER_DOES_NOT_EXIST_KO = "utilDoesNotExist";
	protected static String RELATIVE_TEST_RESOURCES_PATH = ".."+S+".."+S+".."+S+".."+S+".."+S+".."+S+"src"+S+"test"+S+"resources"+S;
	
	/**
	 * Get absolute path string for test resources
	 * @param resource
	 * @throws DcmException 
	 */
	protected String getAbsolutePathStringOfTestResource(String resource) throws DcmException {
		StringBuilder sb = new StringBuilder();
		
		try {
			URI currentURI = CheckerUtilTest.class.getResource(".").toURI();
			URI resolvedURI = currentURI.resolve(RELATIVE_TEST_RESOURCES_PATH);
			if(resolvedURI == null) {
				throw new DcmException(ERROR_UNABLE_TO_GET_ABSOLUTE_PATH_STR + resource);
			}
			sb.append(resolvedURI.getPath()).append(resource);
		} catch (DcmException d) {
			throw d;
		} catch (Exception e) {
			throw new DcmException(ERROR_UNABLE_TO_GET_ABSOLUTE_PATH_STR + resource, e);
		}
		return sb.toString();
		
		
	}
}
