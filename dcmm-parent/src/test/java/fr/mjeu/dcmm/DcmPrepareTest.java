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
	protected static String WORK_FOLDER_NAME = "workFolder";
	
	protected static Path workFolderPath = null;
	protected static String workFolderPathStr = null;
	
	@BeforeEach
	protected void initializeTest() throws DcmException, IOException {
		
		// initializes work folder path
		initializeWorkFolderPath();
		
		// deletes work folder and its content if already exists
		deleteWorkFolder();
		
		// create work folder
		createWorkFolder();
		
		// copy test files to work folder
		copyTestFilesToWorkFolder();
		
	}
	
	@AfterAll
	protected static void cleanTests() throws IOException {
		
		// deletes work folder and its content
		deleteWorkFolder();
				
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
	 * Delete work folder and its content
	 * @throws IOException 
	 */
	private static void deleteWorkFolder() throws IOException {
		if(Files.exists(workFolderPath) && Files.isDirectory(workFolderPath)) {
			Files.walk(workFolderPath)
		      .sorted(Comparator.reverseOrder())
		      .map(Path::toFile)
		      .forEach(File::delete);
		}
	}
	
	/**
	 * Create work folder
	 * @throws IOException 
	 */
	private static void createWorkFolder() throws IOException {
		Files.createDirectories(workFolderPath);
	}
	
	/**
	 * Copy test files to work folder
	 * @throws DcmException 
	 * @throws IOException 
	 */
	private static void copyTestFilesToWorkFolder() throws DcmException, IOException {
		String file15PathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK + FILENAME_EXAMPLE_15_MO);
		Path file15Path = DcmFileUtil.getPath(file15PathStr);
		Path copyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
		Files.copy(file15Path, copyPath);
	}

}
