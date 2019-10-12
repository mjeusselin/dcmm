package fr.mjeu.dcmm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.DcmFileUtil;

public class DcmPrepareTest extends DcmTest {
	
	protected static String RELATIVE_TARGET_FOLDER_PATH = ".."+S+".."+S+".."+S+".."+S+".."+S;
	protected static String OUT_FILENAME_SUFFIX = "2";
	protected static String OUT_FOLDER_NAME = "outFolder";
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
		outFolderPathStr = getAbsolutePathStringFromTestClassLoader(RELATIVE_TARGET_FOLDER_PATH, OUT_FOLDER_NAME);
		outFolderPath = DcmFileUtil.getPath(outFolderPathStr);
	}
	
	/**
	 * Initialize work folder path
	 * @throws DcmException 
	 */
	private static void initializeWorkFolderPath() throws DcmException {
		workFolderPathStr = getAbsolutePathStringFromTestClassLoader(RELATIVE_TARGET_FOLDER_PATH, WORK_FOLDER_NAME);
		workFolderPath = DcmFileUtil.getPath(workFolderPathStr);
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
		
		String fileLogoPathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_IMAGES + FILENAME_EXAMPLE_LOGO);
		Path fileLogoPath = DcmFileUtil.getPath(fileLogoPathStr);
		Path fileLogoCopyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_LOGO);
		Files.copy(fileLogoPath, fileLogoCopyPath);
	}

}
