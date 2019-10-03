package fr.mjeu.dcmm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.util.DcmFileUtil;

public class DcmPrepareTest extends DcmTest {
	
	protected static String RELATIVE_TARGET_FOLDER_PATH = ".."+S+".."+S+".."+S+".."+S+".."+S;
	protected static String WORK_FOLDER_NAME = "workFolder";
	
	protected Path workFolderPath = null;
	
	@BeforeEach
	protected void initialize() throws DcmException, IOException {
		// builds work folder path
		String workFolderPathStr = getAbsolutePathStringFromTestClassLoader(RELATIVE_TARGET_FOLDER_PATH, WORK_FOLDER_NAME);
		Path workFolderPath = DcmFileUtil.getPath(workFolderPathStr);
		
		// deletes work folder and its content if already exists
		if(Files.exists(workFolderPath) && Files.isDirectory(workFolderPath)) {
			Files.walk(workFolderPath)
		      .sorted(Comparator.reverseOrder())
		      .map(Path::toFile)
		      .forEach(File::delete);
		}
		
		// create work folder
		Files.createDirectories(workFolderPath);
		
		// copy test files to work folder
		String file15PathStr = getAbsolutePathStringOfTestResource(PATH_STR_FOLDER_EXAMPLES_OK + FILENAME_EXAMPLE_15_MO);
		Path file15Path = DcmFileUtil.getPath(file15PathStr);
		Path copyPath = DcmFileUtil.getPath(workFolderPathStr, FILENAME_EXAMPLE_15_MO);
		Files.copy(file15Path, copyPath);
		
	}

}
