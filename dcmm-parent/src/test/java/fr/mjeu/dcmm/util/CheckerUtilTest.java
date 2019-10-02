package fr.mjeu.dcmm.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmTest;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.CheckerUtil;

public class CheckerUtilTest extends DcmTest {
	
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
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_EXISTS_OK);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFileExists_file_not_exists_KO() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_folder_not_exists_KO() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
		try {
			CheckerUtil.checkFileExists(folderPathStr, FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_file_null_KO() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		try {
			CheckerUtil.checkFileExists(folderPathStr, null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
	}
	
	@Test
	public void testCheckFileExists_file_empty_KO() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
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
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		try {
			CheckerUtil.checkFolderExists(folderPathStr);
		} catch (CheckerException c) {
			fail();
		}
	}
	
	@Test
	public void testCheckFolderExists_not_exists_KO() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
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
