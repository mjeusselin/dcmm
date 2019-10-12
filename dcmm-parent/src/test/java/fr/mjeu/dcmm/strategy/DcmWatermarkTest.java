package fr.mjeu.dcmm.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.util.Arrays;

import org.dcm4che3.data.Tag;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmPrepareTest;
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
public class DcmWatermarkTest extends DcmPrepareTest {

	@Test
	public void testConstructor_OK() throws DcmException {
		DcmWatermark dcmWatermark = null;
		Path imagePath = null;
		try {
			imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
			dcmWatermark = new DcmWatermark(imagePath);
		} catch (DcmException c) {
			fail();
		}
		assertNotNull(dcmWatermark);
		assertEquals(imagePath, dcmWatermark.getImagePath());
		assertFalse(dcmWatermark.getOverwriteOriginalFile());
	}
	
	@Test
	public void testConstructor_image_not_exists_KO() throws DcmException {
		DcmWatermark dcmWatermark = null;
		Path imagePath = null;
		try {
			imagePath = DcmFileUtil.getPath(workFolderPathStr, "KO"+FILENAME_EXAMPLE_LOGO);
			dcmWatermark = new DcmWatermark(imagePath);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(dcmWatermark);
	}
	
	@Test
	public void testConstructor_image_null_KO() throws DcmException {
		DcmWatermark dcmWatermark = null;
		Path imagePath = null;
		try {
			dcmWatermark = new DcmWatermark(imagePath);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(dcmWatermark);
	}
	
	@Test
	public void testConstructor_image_extension_KO() throws DcmException {
		DcmWatermark dcmWatermark = null;
		Path imagePath = null;
		try {
			imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXTENSION_DCM_KO);
			dcmWatermark = new DcmWatermark(imagePath);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(dcmWatermark);
	}
	
	@Test
	public void testExecute_OK() throws DcmException {
		DcmWatermark dcmWatermark = null;
		DcmUnit dcmUnit = null;
		DcmUnit dcmUnitExpected = null;
		Path dicomPath = null;
		Path imagePath = null;
		Path outFilePathExpected = null;
		try {
			dicomPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
			outFilePathExpected = DcmFileUtil.getPath(getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL + FILENAME_EXAMPLE_15_MO_WITH_LOGO));
			dcmWatermark = new DcmWatermark(imagePath);
			dcmUnit = DcmUtil.readDcm(dicomPath);
			dcmUnit = dcmWatermark.execute(dcmUnit);
			dcmUnitExpected = DcmUtil.readDcm(outFilePathExpected);
			byte[] expectedPixels = (byte[]) dcmUnitExpected.getDataset().getValue(Tag.PixelData);
			byte[] pixels = (byte[]) dcmUnit.getDataset().getValue(Tag.PixelData);
			
			// comparison between pixels just computed and expected pixels of a DICOM with logo
			assertTrue(Arrays.equals(expectedPixels, pixels));
		} catch (DcmException d) {
			fail();
		}
	}
	
	@Test
	public void testExecute_dcm_unit_null_KO() throws DcmException {
		DcmWatermark dcmWatermark = null;
		DcmUnit dcmUnit = null;
		Path imagePath = null;
		try {
			imagePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
			dcmWatermark = new DcmWatermark(imagePath);
			dcmUnit = dcmWatermark.execute(dcmUnit);
			fail();
		} catch (DcmException d) {
			// nothing
		}
		assertNull(dcmUnit);
	}
}
