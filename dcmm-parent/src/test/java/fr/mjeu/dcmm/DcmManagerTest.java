package fr.mjeu.dcmm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.CheckerUtil;

/**
 * Test class for DcmManager
 * 
 * @author Maxime
 *
 */
public class DcmManagerTest extends DcmPrepareTest {

	private static final String TEST_CHANGE_PATIENT_ID_VALUE_TAG = "Hera-MI3";
	
	@Test
	public void testConstructor_OK() throws DcmException {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
	public void testConstructor_change_patient_id_value_null_KO() {
		DcmManager dcmManager = null;
		String changePatientIdValue = null;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_change_patient_id_value_empty_KO(){
		DcmManager dcmManager = null;
		String changePatientIdValue = "";
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		
		assertNull(dcmManager);
		
	}
	
	@Test
	public void testConstructor_file_not_exists_KO() {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_FILE_DOES_NOT_EXIST_KO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);

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
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
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
	public void testConstructor_folder_not_exists_KO() {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr + "_ko";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_null_KO() {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_empty_KO() {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = "";
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testExecute_OK() {
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "true";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		try {
			dcmManager = new DcmManager(
					changePatientIdValue,
					changePatientIdOverwriteOriginalFile,
					inFilename,
					inFolderAbsolutePathStr,
					outFilenameSuffix,
					outFolderAbsolutePathStr);
			dcmManager.execute();
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmManager);		
	}
	
}
