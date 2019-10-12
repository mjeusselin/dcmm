package fr.mjeu.dcmm;


import java.io.File;
import java.nio.file.Paths;

import fr.mjeu.dcmm.exception.DcmException;

public class DcmTest {

	protected static String S = File.separator;
	protected static String FILENAME_EXTENSION_DCM_OK = ".dcm";
	protected static String FILENAME_EXTENSION_DCM_KO = ".png";
	protected static String FILENAME_EXAMPLE_15_MO = "ge-0001-0000-00000000.dcm";
	protected static String FILENAME_EXAMPLE_15_MO_WITH_LOGO = "ge-0001-0000-00000000_with_logo.dcm";
	protected static String FILENAME_EXAMPLE_21_MO = "planmed-200064.424.dcm";
	protected static String FILENAME_EXAMPLE_28_MO = "hologic-MG02.dcm";
	protected static String FILENAME_EXAMPLE_57_MO = "fuji-50333032.466243176.dcm";
	protected static String FILENAME_FILE_EXISTS_OK = "file.dcm";
	protected static String FILENAME_FILE_DOES_NOT_EXIST_KO = "fileDoesNotExist.dcm";
	protected static String FILENAME_EXAMPLE_LOGO = "herami-logo.png";
	protected static String PATH_STR_FOLDER_EXAMPLES_OK = "in"+S+"phantoms-2d"+S;
	protected static String PATH_STR_FOLDER_EXAMPLES_IMAGES = "in"+S+"images"+S;
	protected static String PATH_STR_FOLDER_UTIL = "util"+S;
	protected static String PATH_STR_FOLDER_DOES_NOT_EXIST_KO = "utilDoesNotExist";
	protected static String PATH_TEST_RESOURCES = "src"+S+"test"+S+"resources"+S;
	protected static String PATH_TARGET = "target"+S;
	protected static final boolean TEST_OVERWRITE_TRUE = true;
	protected static final boolean TEST_OVERWRITE_FALSE = false;
	
	/**
	 * Get absolute path string for test resources
	 * @param resource
	 * @throws DcmException 
	 */
	protected static String getAbsolutePathStringOfTestResource(String resource) throws DcmException {
		return Paths.get(PATH_TEST_RESOURCES, resource).toString();
	}
}
