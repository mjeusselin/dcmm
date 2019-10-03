package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.dcm4che3.data.Attributes;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmPrepareTest;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;

/**
 * Test class for DcmUtil
 * 
 * @author Maxime
 *
 */
public class DcmUtilTest extends DcmPrepareTest {
	
	@Test
	public void testReadDcmMetadata_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
		Attributes testAttributes = null;
		try {
			testAttributes = DcmUtil.readDcmMetadata(DcmFileUtil.getPath(folderPathStr, FILENAME_EXAMPLE_15_MO));
		} catch (CheckerException c) {
			fail();
		}
		
		assertNotNull(testAttributes);
	}
	
	@Test
	public void testReadDcmMetadata_path_null_KO() throws DcmException {
		
		Attributes testAttributes = null;
		try {
			testAttributes = DcmUtil.readDcmMetadata(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(testAttributes);
		
	}
	
	@Test
	public void testReadDcmMetadata_file_not_exists_KO() throws DcmException {
		
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
		Attributes testAttributes = null;
		try {
			testAttributes = DcmUtil.readDcmMetadata(DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO));
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(testAttributes);
	
	}
	
	@Test
	public void testReadDcmMetadata_wrong_file_KO() throws DcmException {
		
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_IMAGES);
		
		Attributes testAttributes = null;
		try {
			testAttributes = DcmUtil.readDcmMetadata(DcmFileUtil.getPath(folderPathStr, FILENAME_EXAMPLE_LOGO));
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(testAttributes);
	
	}
	
	@Test
	public void writeDcmMetadata_OK() throws DcmException {
		
		Attributes testAttributes = null;
		boolean writtenMetadata = false;
		
		try {
			Path testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testAttributes = DcmUtil.readDcmMetadata(testFilePath);
			writtenMetadata = DcmUtil.writeDcmMetadata(testAttributes, testFilePath);
		} catch (CheckerException c) {
			fail();
		}
		
		assertTrue(writtenMetadata);
		
	}
	
	

}
