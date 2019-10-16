package fr.mjeu.dcmm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.util.Arrays;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.strategy.DcmTagChange;
import fr.mjeu.dcmm.util.CheckerUtil;
import fr.mjeu.dcmm.util.DcmFileUtil;
import fr.mjeu.dcmm.util.DcmUtil;

/**
 * Test class for DcmManager
 * 
 * @author Maxime
 *
 */
public class DcmManagerTest extends DcmPrepareTest {
	
	@Test
	public void testConstructor_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
		} catch (DcmException de) {
			fail();
		}
		
		assertNotNull(dcmManager);
		
		assertEquals(true, dcmManager.isManualMode());
		
		assertEquals(changePatientIdValue, dcmManager.getChangePatientIDValue());
		
		assertTrue(dcmManager.isChangePatientIdOverwriteOriginalFile());
		
		Path expectedInFilePath = CheckerUtil.checkFileExists(inFolderAbsolutePathStr, inFilename);
		assertEquals(expectedInFilePath, dcmManager.getInFilePath());
		
		Path expectedInFolderPath = CheckerUtil.checkFolderExists(inFolderAbsolutePathStr);
		assertEquals(expectedInFolderPath, dcmManager.getInFolderPath());
		
		assertEquals(outFilenameSuffix, dcmManager.getOutFilenameSuffix());
		
		Path expectedOutFolderPath = CheckerUtil.checkFolderExists(outFolderAbsolutePathStr);
		assertEquals(expectedOutFolderPath, dcmManager.getOutFolderPath());
		
	}
	
	@Test
	public void testConstructor_change_patient_id_value_null_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = null;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_change_patient_id_value_empty_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = "";
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_change_patient_id_overwrite_original_file_false_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
		} catch (DcmException de) {
			fail();
		}
		
		assertNotNull(dcmManager);
		
		assertFalse(dcmManager.isChangePatientIdOverwriteOriginalFile());
		
	}
	
	@Test
	public void testConstructor_change_patient_id_overwrite_original_file_null_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = null;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
		} catch (DcmException de) {
			fail();
		}
		
		assertNotNull(dcmManager);
		
		assertFalse(dcmManager.isChangePatientIdOverwriteOriginalFile());
		
	}
	
	@Test
	public void testConstructor_change_patient_id_overwrite_original_file_monitoring_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		
		assertNull(dcmManager);
		
	}
	
	@Test
	public void testConstructor_file_not_exists_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_FILE_DOES_NOT_EXIST_KO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());

			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_filename_null_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = null;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
		} catch (DcmException de) {
			// nothing
		}
		assertNotNull(dcmManager);
		
		assertEquals(false, dcmManager.isManualMode());
		
		assertEquals(changePatientIdValue, dcmManager.getChangePatientIDValue());
		
		assertFalse(dcmManager.isChangePatientIdOverwriteOriginalFile());
		
		assertNull(dcmManager.getInFilePath());
		
		Path expectedInFolderPath = CheckerUtil.checkFolderExists(inFolderAbsolutePathStr);
		assertEquals(expectedInFolderPath, dcmManager.getInFolderPath());
		
		assertEquals(outFilenameSuffix, dcmManager.getOutFilenameSuffix());
		
		Path expectedOutFolderPath = CheckerUtil.checkFolderExists(outFolderAbsolutePathStr);
		assertEquals(expectedOutFolderPath, dcmManager.getOutFolderPath());
	}
	
	@Test
	public void testConstructor_filename_empty_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
		} catch (DcmException de) {
			// nothing
		}
		assertNotNull(dcmManager);
		
		assertEquals(false, dcmManager.isManualMode());
		
		assertEquals(changePatientIdValue, dcmManager.getChangePatientIDValue());
		
		assertFalse(dcmManager.isChangePatientIdOverwriteOriginalFile());
		
		assertNull(dcmManager.getInFilePath());
		
		Path expectedInFolderPath = CheckerUtil.checkFolderExists(inFolderAbsolutePathStr);
		assertEquals(expectedInFolderPath, dcmManager.getInFolderPath());
		
		assertEquals(outFilenameSuffix, dcmManager.getOutFilenameSuffix());
		
		Path expectedOutFolderPath = CheckerUtil.checkFolderExists(outFolderAbsolutePathStr);
		assertEquals(expectedOutFolderPath, dcmManager.getOutFolderPath());
	}
	
	@Test
	public void testConstructor_folder_not_exists_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr + "_ko";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_null_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_empty_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = "";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_image_not_exists_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr + "_ko";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO + "KO");
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_image_null_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		String imagePathStr = null;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_image_empty_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = "";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		String imagePathStr = "";
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testExecute_manual_mode_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			dcmManager.execute();
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmManager);		
	}
	
	@Test
	public void testNotifyInputFileToProcess_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = "";
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		
		DcmUnit expectedDcmUnit = null;
		Path expectedOutFilePath = null;
		Path inFilePath = null;
		Path outFilePath = null;
		DcmUnit outDcmUnit = null;
		
		
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr,
					imagePath.toString());
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_28_MO);
			
			// process
			dcmManager.notifyInputFileToProcess(inFilePath);
			
			// expected result
			expectedOutFilePath = DcmFileUtil.getPath(getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL + FILENAME_EXAMPLE_28_MO_WITH_TAG_AND_LOGO));
			expectedDcmUnit = DcmUtil.readDcm(expectedOutFilePath);
			byte[] expectedPixels = (byte[]) expectedDcmUnit.getDataset().getValue(Tag.PixelData);
			
			// result
			outFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_28_MO);
			outDcmUnit = DcmUtil.readDcm(outFilePath);
			byte[] outPixels = (byte[]) outDcmUnit.getDataset().getValue(Tag.PixelData);
			
			// test tag
			assertNotNull(outDcmUnit);
			Attributes dataset = outDcmUnit.getDataset();
			assertNotNull(dataset);
			assertEquals(
					TEST_CHANGE_PATIENT_ID_VALUE_TAG,
					dataset.getString(DcmTagChange.dataElementTag));
			
			// test logo
			// comparison between pixels just computed and expected pixels of a DICOM with logo
			assertTrue(Arrays.equals(expectedPixels, outPixels));
			
			
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmManager);		
	}
	
	@Test
	public void testNotifyInputFileToProcess_file_path_null_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = "";
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		Path inFilePath = null;
		dcmManager = new DcmManager(
				changePatientIdValue,
				changePatientIdOverwriteOriginalFile,
				inFilename,
				inFolderAbsolutePathStr,
				outFilenameSuffix,
				outFolderAbsolutePathStr,
				imagePath.toString());
		try {
			dcmManager.notifyInputFileToProcess(inFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNotNull(dcmManager);		
	}
	
	@Test
	public void testNotifyInputFileToProcess_file_path_not_exists_KO() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = "";
		String outFolderAbsolutePathStr = outFolderPathStr;
		Path imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		Path inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_28_MO + "ko");
		dcmManager = new DcmManager(
				changePatientIdValue,
				changePatientIdOverwriteOriginalFile,
				inFilename,
				inFolderAbsolutePathStr,
				outFilenameSuffix,
				outFolderAbsolutePathStr,
				imagePath.toString());
		try {
			dcmManager.notifyInputFileToProcess(inFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNotNull(dcmManager);		
	}
	
}
