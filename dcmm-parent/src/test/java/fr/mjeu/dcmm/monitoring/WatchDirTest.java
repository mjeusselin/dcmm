package fr.mjeu.dcmm.monitoring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fr.mjeu.dcmm.DcmManager;
import fr.mjeu.dcmm.DcmPrepareTest;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.DcmFileUtil;

/**
 * Test class for WatchDir
 * 
 * @author Maxime
 *
 */
public class WatchDirTest extends DcmPrepareTest {

	@Test
	public void testConstructor_OK() throws DcmException {
		WatchDir watchDir = null;
		Path dir = workFolderPath;
		boolean recursive = false;
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
			watchDir = new WatchDir(dir, recursive, dcmManager);
		} catch (DcmException de) {
			fail();
		}
		assertNotNull(watchDir);
	}
	
	@Test
	public void testConstructor_dir_not_exists_KO() throws DcmException {
		WatchDir watchDir = null;
		Path dir = DcmFileUtil.getPath(workFolderPathStr + "ko");
		boolean recursive = false;
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		dcmManager = new DcmManager(
				changePatientIdValue,
				changePatientIdOverwriteOriginalFile,
				inFilename,
				inFolderAbsolutePathStr,
				outFilenameSuffix,
				outFolderAbsolutePathStr);
		try {
			watchDir = new WatchDir(dir, recursive, dcmManager);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(watchDir);
	}
	
	@Test
	public void testConstructor_dir_null_KO() throws DcmException {
		WatchDir watchDir = null;
		Path dir = null;
		boolean recursive = false;
		DcmManager dcmManager = null;
		String changePatientIdValue = TEST_CHANGE_PATIENT_ID_VALUE_TAG;
		String changePatientIdOverwriteOriginalFile = "false";
		String inFilename = "";
		String inFolderAbsolutePathStr = workFolderPathStr;
		String outFilenameSuffix = OUT_FILENAME_SUFFIX;
		String outFolderAbsolutePathStr = outFolderPathStr;
		dcmManager = new DcmManager(
				changePatientIdValue,
				changePatientIdOverwriteOriginalFile,
				inFilename,
				inFolderAbsolutePathStr,
				outFilenameSuffix,
				outFolderAbsolutePathStr);
		try {
			watchDir = new WatchDir(dir, recursive, dcmManager);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(watchDir);
	}
	
	@Test
	public void testConstructor_dcm_manager_null_KO() throws DcmException {
		WatchDir watchDir = null;
		Path dir = workFolderPath;
		boolean recursive = false;
		DcmManager dcmManager = null;
		try {
			watchDir = new WatchDir(dir, recursive, dcmManager);
			fail();
		} catch (DcmException de) {
			// nothing
		}
		assertNull(watchDir);
	}
	
}
