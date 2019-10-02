package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.dcm4che3.data.Attributes;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmTest;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;

/**
 * Test class for DcmUtil
 * 
 * @author Maxime
 *
 */
public class DcmUtilTest extends DcmTest {
	
	@Test
	public void testReadDcmMetadata_OK() throws DcmException {
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
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
		
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
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
		
		String folderPathStr = this.getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_IMAGES);
		
		Attributes testAttributes = null;
		try {
			testAttributes = DcmUtil.readDcmMetadata(DcmFileUtil.getPath(folderPathStr, FILENAME_EXAMPLE_LOGO));
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(testAttributes);
	
	}
	
	

}
