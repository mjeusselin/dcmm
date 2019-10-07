package fr.mjeu.dcmm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.exception.DcmException;

/**
 * Test class for DcmManager
 * 
 * @author Maxime
 *
 */
public class DcmManagerTest extends DcmPrepareTest {

	private static final String TEST_CHANGE_PATIENT_ID_VALUE_TAG = "Hera-MI3";
	
	@Test
	public void testConstructor_OK() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_not_exists_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr + "_ko";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_null_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = null;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_folder_empty_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = "";
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_file_not_exists_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = FILENAME_FILE_DOES_NOT_EXIST_KO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_filename_null_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = null;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_filename_empty_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = null;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_change_patient_id_value_null_KO() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = null;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testConstructor_change_patient_id_value_empty_KO(){
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = "";
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmManager);		
	}
	
	@Test
	public void testExecute_OK() {
		DcmManager dcmManager = null;
		String inFolderStr = workFolderPathStr;
		String inFilename = FILENAME_EXAMPLE_15_MO;
		String changePatientIDValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		try {
			dcmManager = new DcmManager(inFolderStr,
					inFilename,
					changePatientIDValue);
			dcmManager.execute();
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmManager);		
	}
	
}
