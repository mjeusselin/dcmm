package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmTest;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;

public class CheckerUtilTest extends DcmTest {
	
	@Test
	public void testCheckBoolean_true_OK() {
		String booleanTestStr = "true";
		assertTrue(CheckerUtil.checkBoolean(booleanTestStr));
	}
	
	@Test
	public void testCheckBoolean_false_OK() {
		String booleanTestStr = "false";
		assertFalse(CheckerUtil.checkBoolean(booleanTestStr));
	}
	
	@Test
	public void testCheckBoolean_null_KO() {
		String booleanTestStr = null;
		assertFalse(CheckerUtil.checkBoolean(booleanTestStr));
	}
	
	@Test
	public void testCheckBoolean_empty_KO() {
		String booleanTestStr = "";
		assertFalse(CheckerUtil.checkBoolean(booleanTestStr));
	}
	
	@Test
	public void testCheckBoolean_other_string_KO() {
		String booleanTestStr = "test";
		assertFalse(CheckerUtil.checkBoolean(booleanTestStr));
	}
	
	@Test
	public void testCheckNotEmpty_OK() {
		try {
			CheckerUtil.checkNotEmpty("a");
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckNotEmpty_null_KO() {
		try {
			CheckerUtil.checkNotEmpty(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckNotEmpty_empty_KO() {
		try {
			CheckerUtil.checkNotEmpty("");
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckNotNull_OK(){
		try {
			CheckerUtil.checkNotNull("a");
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckNotNull_KO() {
		try {
			CheckerUtil.checkNotNull(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_EXISTS_OK);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFileExists_file_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_folder_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_file_null_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		try {
			CheckerUtil.checkFileExists(folderPathStr, null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_file_empty_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		try {
			CheckerUtil.checkFileExists(folderPathStr, "");
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_folder_null_KO() throws DcmException {
		try {
			CheckerUtil.checkFileExists(null, FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_folder_empty_KO() throws DcmException {
		try {
			CheckerUtil.checkFileExists(null, FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExistsFromPath_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_EXISTS_OK);
		try {
			CheckerUtil.checkFileExistsFromPath(p);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFileExistsFromPath_file_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
		try {
			CheckerUtil.checkFileExistsFromPath(p);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExistsFromPath_path_null_KO() throws DcmException {
		try {
			CheckerUtil.checkFileExistsFromPath(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFilenameDcmExtension_OK() throws DcmException {
		try {
			CheckerUtil.checkFilenameDcmExtension(FILENAME_EXTENSION_DCM_OK);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFilenameDcmExtension_wrong_KO() throws DcmException {
		try {
			CheckerUtil.checkFilenameDcmExtension(FILENAME_EXTENSION_DCM_KO);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFilenameDcmExtension_null_KO() throws DcmException {
		try {
			CheckerUtil.checkFilenameDcmExtension(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFilenameDcmExtension_empty_KO() throws DcmException {
		try {
			CheckerUtil.checkFilenameDcmExtension("");
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFolderExists_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		try {
			CheckerUtil.checkFolderExists(folderPathStr);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFolderExists_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
		try {
			CheckerUtil.checkFolderExists(folderPathStr);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFolderExists_null_KO() {
		try {
			CheckerUtil.checkFolderExists(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFolderExists_empty_KO() {
		try {
			CheckerUtil.checkFolderExists("");
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}

}
