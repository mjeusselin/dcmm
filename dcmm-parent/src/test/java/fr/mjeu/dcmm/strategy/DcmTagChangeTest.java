package fr.mjeu.dcmm.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.util.DcmFileUtil;
import fr.mjeu.dcmm.util.DcmUtil;

/**
 * Test class for DcmTagChange
 * 
 * @author Maxime
 *
 */
public class DcmTagChangeTest extends DcmPrepareTest {

	private static final String TEST_CHANGE_PATIENT_ID_VALUE_TAG = "Hera-MI";
	
	@Test
	public void testConstructor_overwrite_true_OK() throws DcmException {
		DcmTagChange dcmTagChange = null;
		try {
			dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		} catch (CheckerException c) {
			fail();
		}
		assertNotNull(dcmTagChange);
		assertEquals(TEST_CHANGE_PATIENT_ID_VALUE_TAG, dcmTagChange.getDataValueField());
		assertTrue(dcmTagChange.getOverwriteOriginalFile());
	}
	
	@Test
	public void testConstructor_overwrite_false_OK() throws DcmException {
		DcmTagChange dcmTagChange = null;
		try {
			dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_FALSE);
		} catch (CheckerException c) {
			fail();
		}
		assertNotNull(dcmTagChange);
		assertEquals(TEST_CHANGE_PATIENT_ID_VALUE_TAG, dcmTagChange.getDataValueField());
		assertFalse(dcmTagChange.getOverwriteOriginalFile());
	}
	
	@Test
	public void testConstructor_null_KO() throws DcmException {
		DcmTagChange dcmTagChange = null;
		try {
			dcmTagChange = new DcmTagChange(null, TEST_OVERWRITE_TRUE);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		assertNull(dcmTagChange);
	}
	
	@Test
	public void testConstructor_empty_KO() throws DcmException {
		DcmTagChange dcmTagChange = null;
		try {
			dcmTagChange = new DcmTagChange("", TEST_OVERWRITE_TRUE);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		assertNull(dcmTagChange);
	}
	
	@Test
	public void testExecute_OK() throws DcmException {
		
		DcmUnit dcmUnit = null;
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		
		try {
			Path testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmUnit = DcmUtil.readDcm(testFilePath);
			dcmUnit = dcmTagChange.execute(dcmUnit);
		} catch (CheckerException c) {
			fail();
		}
		
		assertNotNull(dcmUnit);
		Attributes dataset = dcmUnit.getDataset();
		assertNotNull(dataset);
		assertEquals(
				TEST_CHANGE_PATIENT_ID_VALUE_TAG,
				dataset.getString(DcmTagChange.dataElementTag));
		
	}
	
	@Test
	public void testExecute_unit_null_KO() throws DcmException {
		
		DcmUnit dcmUnit = null;
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		
		try {
			dcmUnit = dcmTagChange.execute(dcmUnit);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNull(dcmUnit);
		
	}
	
	@Test
	public void testExecute_unit_dataset_null_KO() throws DcmException {
		
		DcmUnit dcmUnit = new DcmUnit();
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		
		try {
			dcmUnit = dcmTagChange.execute(dcmUnit);
			fail();
		} catch (CheckerException c) {
			// nothing
		}
		
		assertNotNull(dcmUnit);
		assertNull(dcmUnit.getDataset());
		
	}
	
}
