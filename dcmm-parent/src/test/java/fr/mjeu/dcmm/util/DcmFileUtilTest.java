package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmTest;
import fr.mjeu.dcmm.exception.DcmException;

/**
 * Test class for DcmFileUtil
 * 
 * @author Maxime
 *
 */
public class DcmFileUtilTest extends DcmTest {

	@Test
	public void testGetFileFromPath_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_EXISTS_OK);
		File f = null;
		f = DcmFileUtil.getFileFromPath(p);
		assertNotNull(f);
		assertTrue(f.exists());
		assertTrue(f.isFile());
	}
	
	@Test
	public void testGetFileFromPath_file_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
		File f = null;
		f = DcmFileUtil.getFileFromPath(p);
		assertNotNull(f);
		assertTrue(!f.exists());
	}
	
	@Test
	public void testGetFileFromPath_path_null_KO() throws DcmException {
		Path p = null;
		File f = null;
		f = DcmFileUtil.getFileFromPath(p);
		assertNull(f);
	}
	
	@Test
	public void testGetFileFromPathStr_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_EXISTS_OK);
		File f = null;
		f = DcmFileUtil.getFileFromPathStr(p.toString());
		assertNotNull(f);
		assertTrue(f.exists());
		assertTrue(f.isFile());
	}
	
	@Test
	public void testGetFileFromPathStr_file_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
		File f = null;
		f = DcmFileUtil.getFileFromPathStr(p.toString());
		assertNotNull(f);
		assertFalse(f.exists());
	}
	
	@Test
	public void testGetFileFromPathStr_path_str_null_KO() throws DcmException {
		String pStr = null;
		File f = null;
		try {
			f = DcmFileUtil.getFileFromPathStr(pStr);
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(f);
	}
	
	@Test
	public void testGetPath_file_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_EXISTS_OK);
			assertNotNull(p);
		} catch (DcmException e) {
			fail();
		}
	}
	
	@Test
	public void testGetPath_file_not_exists_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			assertNotNull(p);
		} catch (DcmException e) {
			fail();
		}
	}
	
	@Test
	public void testGetPath_file_filename_null_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, null);
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
	@Test
	public void testGetPath_file_filename_empty_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, "");
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
	@Test
	public void testGetPath_file_folder_not_exists_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			assertNotNull(p);
		} catch (DcmException e) {
			fail();
		}
	}
	
	@Test
	public void testGetPath_file_folder_null_KO() throws DcmException {
		String folderPathStr = null;
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
	@Test
	public void testGetPath_file_folder_empty_KO() throws DcmException {
		Path p = null;
		try {
			p = DcmFileUtil.getPath("", FILENAME_FILE_EXISTS_OK);
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
	@Test
	public void testGetPath_folder_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXISTS_OK);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr);
			assertNotNull(p);
		} catch (DcmException e) {
			fail();
		}
		assertNotNull(p);
	}
	
	@Test
	public void testGetPath_folder_not_exists_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_DOES_NOT_EXIST_KO);
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr);
			assertNotNull(p);
		} catch (DcmException e) {
			fail();
		}
	}
	
	@Test
	public void testGetPath_folder_null_KO() throws DcmException {
		String folderPathStr = null;
		Path p = null;
		try {
			p = DcmFileUtil.getPath(folderPathStr);
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
	@Test
	public void testGetPath_folder_empty_KO() throws DcmException {
		Path p = null;
		try {
			p = DcmFileUtil.getPath("");
			fail();
		} catch (DcmException e) {
			// nothing
		}
		assertNull(p);
	}
	
}
