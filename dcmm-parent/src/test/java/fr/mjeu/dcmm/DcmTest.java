package fr.mjeu.dcmm;


import java.io.File;
import java.net.URI;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.CheckerUtilTest;

public class DcmTest {

	protected static String ERROR_UNABLE_TO_GET_ABSOLUTE_PATH_STR = "Unable to get absolute path string of test resource : ";
	protected static String S = File.separator;
	protected static String PATH_STR_FOLDER_EXISTS_OK = "util";
	protected static String PATH_STR_FOLDER_DOES_NOT_EXIST_KO = "utilDoesNotExist";
	protected static String PATH_STR_FILE_EXISTS_OK = "file.dcm";
	protected static String PATH_STR_FILE_DOES_NOT_EXIST_KO = "fileDoesNotExist.dcm";
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
