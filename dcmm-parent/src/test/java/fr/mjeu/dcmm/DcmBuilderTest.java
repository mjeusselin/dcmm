package fr.mjeu.dcmm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.strategy.DcmTagChange;
import fr.mjeu.dcmm.util.DcmFileUtil;

/**
 * Test class for DcmBuilder
 * 
 * @author Maxime
 *
 */
public class DcmBuilderTest extends DcmPrepareTest {

	private static final int INIT_STRATEGIES_NUMBER = 0;
	private static final int TEST_STRATEGIES_NUMBER = 1;
	private static final String TEST_CHANGE_PATIENT_ID_VALUE_TAG = "Hera-MI2";
	
	@Test
	public void testConstructor_OK() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testInFilePath = null;
		Path testOutFilePath = null;
		try {
			testInFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testInFilePath, testOutFilePath);
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmBuilder);
		assertNotNull(dcmBuilder.getDcmUnit());
		assertNotNull(dcmBuilder.getDcmUnit().getDataset());
		assertNotNull(dcmBuilder.getDcmUnit().getFmi());
		assertNotNull(dcmBuilder.getDcmUnit().getInFilePath());
		assertEquals(testInFilePath, dcmBuilder.getDcmUnit().getInFilePath());
		assertNotNull(dcmBuilder.getDcmUnit().getOutFilePath());
		assertEquals(testOutFilePath, dcmBuilder.getDcmUnit().getOutFilePath());
		assertNotNull(dcmBuilder.getStrategies());
		assertEquals(INIT_STRATEGIES_NUMBER, dcmBuilder.getStrategies().size());
		
	}
	
	@Test
	public void testConstructor_in_file_path_null_KO() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testInFilePath = null;
		Path testOutFilePath = null;
		try {
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testInFilePath, testOutFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmBuilder);
	}
	
	@Test
	public void testConstructor_in_file_not_exists_KO() throws DcmException {
		String folderPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_UTIL);
		DcmBuilder dcmBuilder = null;
		Path testInFilePath = null;
		Path testOutFilePath = null;
		try {
			testInFilePath = DcmFileUtil.getPath(folderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testInFilePath, testOutFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmBuilder);
	}
	
	@Test
	public void testConstructor_out_file_path_null_KO() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testInFilePath = null;
		Path testOutFilePath = null;
		try {
			testInFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testInFilePath, testOutFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmBuilder);
	}
	
	@Test
	public void testConstructor_out_file_parent_not_exists_KO() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testInFilePath = null;
		Path testOutFilePath = null;
		try {
			testInFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr + "ko", FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testInFilePath, testOutFilePath);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(dcmBuilder);
		
	}
	
	@Test
	public void testAddStrategy_OK() throws DcmException {
		DcmBuilder dcmBuilder = null;
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		Path testFilePath = null;
		Path testOutFilePath = null;
		try {
			testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testFilePath, testOutFilePath);
			dcmBuilder.addStrategy(dcmTagChange);
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmBuilder);
		assertNotNull(dcmBuilder.getStrategies());
		assertEquals(TEST_STRATEGIES_NUMBER, dcmBuilder.getStrategies().size());
		assertEquals(dcmTagChange, dcmBuilder.getStrategies().get(0));
	}
	
	@Test
	public void testAddStrategy_null_KO() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testFilePath = null;
		Path testOutFilePath = null;
		try {
			testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testFilePath, testOutFilePath);
			dcmBuilder.addStrategy(null);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNotNull(dcmBuilder);
		assertNotNull(dcmBuilder.getStrategies());
		assertEquals(INIT_STRATEGIES_NUMBER, dcmBuilder.getStrategies().size());
	}
	
	@Test
	public void testBuild_OK() throws DcmException {
		DcmBuilder dcmBuilder = null;
		DcmTagChange dcmTagChange = new DcmTagChange(TEST_CHANGE_PATIENT_ID_VALUE_TAG, TEST_OVERWRITE_TRUE);
		Path testFilePath = null;
		Path testOutFilePath = null;
		try {
			testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testFilePath, testOutFilePath);
			dcmBuilder.addStrategy(dcmTagChange);
			dcmBuilder.build();
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmBuilder);
		assertNotNull(dcmBuilder.getDcmUnit());
	}
	
	@Test
	public void testBuild_no_strategy_OK() throws DcmException {
		DcmBuilder dcmBuilder = null;
		Path testFilePath = null;
		Path testOutFilePath = null;
		try {
			testFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			testOutFilePath = DcmFileUtil.getPath(outFolderPathStr, FILENAME_EXAMPLE_15_MO);
			dcmBuilder = new DcmBuilder(testFilePath, testOutFilePath);
			dcmBuilder.build();
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(dcmBuilder);
		assertNotNull(dcmBuilder.getDcmUnit());
	}
	
}
