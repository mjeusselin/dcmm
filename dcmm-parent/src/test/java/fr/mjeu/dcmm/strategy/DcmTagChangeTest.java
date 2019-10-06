package fr.mjeu.dcmm.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.dcm4che3.data.Attributes;
import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmPrepareTest;
import fr.mjeu.dcmm.DcmUnit;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.DcmFileUtil;
import fr.mjeu.dcmm.util.DcmUtil;

/**
 * Test class for DcmTagChange
 * 
 * @author Maxime
 *
 */
public class DcmTagChangeTest extends DcmPrepareTest {

	private static final String TEST_DATA_VALUE_TAG = "Hera-MI";
	
	@Test
	public void testExecute_OK() throws DcmException {
		
		DcmUnit dcmUnit = null;
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_DATA_VALUE_TAG);
		
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
				TEST_DATA_VALUE_TAG,
				dataset.getString(DcmTagChange.dataElementTag));
		
	}
	
}
