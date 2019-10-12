package fr.mjeu.dcmm.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmPrepareTest;
import fr.mjeu.dcmm.exception.DcmException;

/**
 * Test class for DcmOutFilePathUtil
 * 
 * @author Maxime
 *
 */
public class DcmOutFilePathUtilTest extends DcmPrepareTest {

	private static String EXPECTED_FILENAME = "ge-0001-0000-000000002.dcm";
	
	@Test
	public void testGetOutFilePath_OK() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		Path outFolderPathTest = outFolderPath;
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
		} catch (DcmException c) {
			fail();
		}
		assertNotNull(outFilePath);
		assertEquals(0, outFolderPath.compareTo(outFilePath.getParent()));
		assertEquals(EXPECTED_FILENAME, outFilePath.getFileName().toString());
	}
	
	@Test
	public void testGetOutFilePath_input_file_not_exists_KO() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		Path outFolderPathTest = outFolderPath;
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_FILE_DOES_NOT_EXIST_KO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(outFilePath);
	}
	
	@Test
	public void testGetOutFilePath_input_file_null_KO() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		Path outFolderPathTest = outFolderPath;
		Path outFilePath = null;
		try {
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(outFilePath);
	}
	
	@Test
	public void testGetOutFilePath_outFilenameSuffix_empty_OK() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = "";
		Path outFolderPathTest = outFolderPath;
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
		} catch (DcmException c) {
			fail();
		}
		assertNotNull(outFilePath);
		assertEquals(0, outFolderPath.compareTo(outFilePath.getParent()));
		assertEquals(FILENAME_EXAMPLE_15_MO, outFilePath.getFileName().toString());
	}
	
	@Test
	public void testGetOutFilePath_outFilenameSuffix_null_KO() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = null;
		Path outFolderPathTest = outFolderPath;
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(outFilePath);
	}
	
	@Test
	public void testGetOutFilePath_folder_not_exists_KO() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		Path outFolderPathTest = DcmFileUtil.getPath(outFolderPathStr + "ko");
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(outFilePath);
	}
	

	
	@Test
	public void testGetOutFilePath_folder_null_KO() throws DcmException {
		Path inFilePath = null;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		Path outFolderPathTest = null;
		Path outFilePath = null;
		try {
			inFilePath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
			
			outFilePath = DcmOutFilePathUtil.getOutFilePath(
					inFilePath,
					outFilenameSuffix,
					outFolderPathTest);
			fail();
		} catch (DcmException c) {
			// nothing
		}
		assertNull(outFilePath);
	}
	
	
}
