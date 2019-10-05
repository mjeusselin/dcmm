package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmPrepareTest;
import fr.mjeu.dcmm.DcmUnit;
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
	public void testReadDcm_OK() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
		DcmUnit dcmU = null;
		try {
			dcmU = DcmUtil.readDcm(DcmFileUtil.getPath(folderPathStr, FILENAME_EXAMPLE_15_MO));
		} catch (CheckerException c) {
			fail();
		}
		
		assertNotNull(dcmU);
	}
	
	@Test
	public void testReadDcm_path_null_KO() throws DcmException {
		
		DcmUnit dcmU = null;
		try {
			dcmU = DcmUtil.readDcm(null);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(dcmU);
		
	}
	
	@Test
	public void testReadDcm_file_not_exists_KO() throws DcmException {
		
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK);
		
		DcmUnit dcmU = null;
		try {
			dcmU = DcmUtil.readDcm(DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO));
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(dcmU);
	
	}
	
	@Test
	public void testReadDcmMetadata_wrong_file_KO() throws DcmException {
		
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_IMAGES);
		
		DcmUnit dcmU = null;
		try {
			dcmU = DcmUtil.readDcm(DcmFileUtil.getPath(folderPathStr, FILENAME_EXAMPLE_LOGO));
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(dcmU);
	
	}
	
	@Test
	public void writeDcm_OK() throws DcmException {
		
		DcmUnit dcmUnit = null;
		boolean writtenMetadata = false;
		
		try {
			Path testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmUnit = DcmUtil.readDcm(testFilePath);
			dcmUnit.getDataset().setString(Tag.PatientID, VR.LO, "Hera-MI");
			writtenMetadata = DcmUtil.writeDcm(dcmUnit, testFilePath);
		} catch (CheckerException c) {
			fail();
		}
		
		assertTrue(writtenMetadata);
		
	}
	
	

}
