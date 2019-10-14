package fr.mjeu.dcmm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.DcmFileUtil;

public class DcmPrepareTest extends DcmTest {
	
	protected static String RELATIVE_TARGET_FOLDER_PATH = ".."+S+".."+S+".."+S+".."+S+".."+S;
	protected static String OUT_FILENAME_SUFFIX = "2";
	protected static String OUT_FOLDER_NAME = "outFolder";
	protected static String TEST_CHANGE_PATIENT_ID_VALUE_TAG = "Hera-MI3";
	protected static String WORK_FOLDER_NAME = "workFolder";
	
	protected static Path outFolderPath = null;
	protected static String outFolderPathStr = null;
	protected static Path workFolderPath = null;
	protected static String workFolderPathStr = null;
	
	@BeforeEach
	protected void initializeTest() throws DcmException, IOException {
		
		// initialize work folder path
		initializeWorkFolderPath();
		
		// initialize out folder path
		initializeOutFolderPath();
		
		// delete work folder and its content if already exists
		deleteFolder(workFolderPath);
		
		// delete output folder and its content if already exists
		deleteFolder(outFolderPath);
		
		// create work folder
		createFolder(workFolderPath);
		
		// create output folder
		createFolder(outFolderPath);
		
		// copy test files to work folder
		copyTestFilesToWorkFolder();
		
	}
	
	@AfterAll
	protected static void cleanTests() throws IOException {
		
		// deletes work folder and its content
		deleteFolder(workFolderPath);
		
		// deletes output folder and its content
		deleteFolder(outFolderPath);
				
	}
	
	/**
	 * Initialize out folder path
	 * @throws DcmException 
	 */
	private static void initializeOutFolderPath() throws DcmException {
		outFolderPath = Paths.get(PATH_TARGET, OUT_FOLDER_NAME);
		outFolderPathStr = outFolderPath.toString();
	}
	
	/**
	 * Initialize work folder path
	 * @throws DcmException 
	 */
	private static void initializeWorkFolderPath() throws DcmException {
		workFolderPath = Paths.get(PATH_TARGET, WORK_FOLDER_NAME);
		workFolderPathStr = workFolderPath.toString();
	}
	
	/**
	 * Delete folder and its content
	 * @param folderPath folder path object
	 * @throws IOException 
	 */
	private static void deleteFolder(Path folderPath) throws IOException {
		if(Files.exists(folderPath) && Files.isDirectory(folderPath)) {
			Files.walk(folderPath)
		      .sorted(Comparator.reverseOrder())
		      .map(Path::toFile)
		      .forEach(File::delete);
		}
	}
	
	/**
	 * Create folder
	 * @throws IOException 
	 */
	private static void createFolder(Path folderPath) throws IOException {
		Files.createDirectories(folderPath);
	}
	
	/**
	 * Copy test files to work folder
	 * @throws DcmException 
	 * @throws IOException 
	 */
	private static void copyTestFilesToWorkFolder() throws DcmException, IOException {
		String file15PathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK + FILENAME_EXAMPLE_15_MO);
		Path file15Path = DcmFileUtil.getPath(file15PathStr);
		Path file15CopyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
		Files.copy(file15Path, file15CopyPath);
		
		String file21PathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK + FILENAME_EXAMPLE_21_MO);
		Path file21Path = DcmFileUtil.getPath(file21PathStr);
		Path file21CopyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_21_MO);
		Files.copy(file21Path, file21CopyPath);
		
		String fileLogoPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_IMAGES + FILENAME_EXAMPLE_LOGO);
		Path fileLogoPath = DcmFileUtil.getPath(fileLogoPathStr);
		Path fileLogoCopyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		Files.copy(fileLogoPath, fileLogoCopyPath);
	}

}
