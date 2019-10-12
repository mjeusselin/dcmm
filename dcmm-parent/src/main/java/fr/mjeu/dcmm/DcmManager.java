package fr.mjeu.dcmm;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.strategy.DcmStrategy;
import fr.mjeu.dcmm.strategy.DcmTagChange;
import fr.mjeu.dcmm.util.CheckerUtil;

public class DcmManager {
	
	static Logger logger = LoggerFactory.getLogger(DcmManager.class);
	
	private static final String DEBUG_BUILD = "building from file ";
	private static final String DEBUG_MANUAL_MODE = "entering manual mode";
	private static final String DEBUG_OUT_FILENAME_SUFFIX_NULL = "no filename suffix, output file(s) will have the same filename than input one(s)";
	private static final String DEBUG_OUT_FOLDER_NULL = "out folder path string null, output folder to use will be the same as input folder one : ";
	private static final String TRACE_EXECUTE_BEGIN = "begin execute";
	private static final String TRACE_EXECUTE_END = "end execute";
	
	private String changePatientIdValue;
	private boolean changePatientIdOverwriteOriginalFile = false;
	private Path inFilePath;
	private Path inFolderPath;
	private boolean manualMode = false;
	private String outFilenameSuffix;
	private Path outFolderPath;
	
	public DcmManager(
			String changePatientIdValue,
			String changePatientIdOverwriteOriginalFile,
			String inFilename,
			String inFolderAbsolutePathStr,
			String outFilenameSuffix,
			String outFolderAbsolutePathStr) throws DcmException {
		
		this.detectManualMode(inFilename); // must be done before setting file path
		
		this.detectChangePatientIdValue(changePatientIdValue);
		
		this.detectChangePatientIdOverwriteOriginalFile(changePatientIdOverwriteOriginalFile);
		
		this.detectInFilePath(inFolderAbsolutePathStr, inFilename);
		
		this.detectInFolderPath(inFolderAbsolutePathStr);
		
		this.detectOutFilenameSuffix(outFilenameSuffix);
		
		this.detectOutFolderPath(outFolderAbsolutePathStr);
		
		
	}
	
	/**
	 * Call builder in order to modify DICOM file(s)
	 */
	public void execute() throws DcmException {
		logger.trace(TRACE_EXECUTE_BEGIN);
		if(this.inFilePath != null) {
			// manual mode
			logger.debug(DEBUG_MANUAL_MODE);
			DcmBuilder db = new DcmBuilder(this.inFilePath);
			DcmStrategy tagChangeStrategy = new DcmTagChange(this.changePatientIdValue);
			db.addStrategy(tagChangeStrategy);
			logger.debug(DEBUG_BUILD + inFilePath.toString());
			db.build();
		}
		logger.trace(TRACE_EXECUTE_END);
	}
	
	/**
	 * If filename is present then manual mode else monitoring mode
	 */
	private void detectManualMode(String inFilename) throws DcmException {
		try {
			CheckerUtil.checkNotEmpty(inFilename);
			// manual mode
			this.manualMode = true;
		} catch(CheckerException ce) {
			// monitoring mode not yet implemented
			throw new DcmException(DcmExceptionMessage.ERROR_MONITORING_NOT_IMPLEMENTED.getMessage());
		}
	}
	
	/**
	 * Check changePatientIdValue and add it to DcmManager attributes
	 * @param changePatientIdValue
	 * @throws CheckerException
	 */
	private void detectChangePatientIdValue(String changePatientIdValue) throws CheckerException {
		CheckerUtil.checkNotEmpty(changePatientIdValue);
		this.changePatientIdValue = changePatientIdValue;
	}
	
	/**
	 * Check changePatientIdOverwriteOriginalFile and add it to DcmManager attributes
	 * @param changePatientIdOverwriteOriginalFile
	 */
	private void detectChangePatientIdOverwriteOriginalFile(String changePatientIdValueOverwriteOriginalFile) throws CheckerException {
		this.changePatientIdOverwriteOriginalFile = CheckerUtil.checkBoolean(changePatientIdValueOverwriteOriginalFile);
	}
	
	/**
	 * Check inFolderAbsolutePathStr and add inFolderPath to DcmManager attributes
	 * @param inFolderAbsolutePathStr
	 * @throws CheckerException
	 */
	private void detectInFolderPath(String inFolderAbsolutePathStr) throws CheckerException {
		this.inFolderPath = CheckerUtil.checkFolderExists(inFolderAbsolutePathStr);
	}
	
	/**
	 * If manual mode
	 * Check inFolderAbsolutePathStr and inFilename and add inFilePath to DcmManager attributes
	 * @param inFolderAbsolutePathStr
	 * @param inFilename
	 * @throws CheckerException
	 */
	private void detectInFilePath(String inFolderAbsolutePathStr, String inFilename) throws CheckerException {
		if(this.manualMode) {
			this.inFilePath = CheckerUtil.checkFileExists(inFolderAbsolutePathStr, inFilename);
		}
	}
	
	/**
	 * Check outFolderAbsolutePathStr and add outFolderPath to DcmManager attributes
	 * If no outFolderAbsolutePathStr, output folder will be the same than input one
	 * @param outFolderAbsolutePathStr
	 * @throws CheckerException
	 */
	private void detectOutFolderPath(String outFolderAbsolutePathStr) throws CheckerException {
		try {
			this.outFolderPath = CheckerUtil.checkFolderExists(outFolderAbsolutePathStr);
		} catch(CheckerException ce) {
			logger.debug(DEBUG_OUT_FOLDER_NULL + this.inFolderPath.toString());
			this.outFolderPath = this.inFolderPath;
		}
	}
	
	/**
	 * Check outFilenameSuffix and add it to DcmManager attributes
	 * If no outFilenameSuffix, output file(s) will have the same name than input ones
	 * @param outFilenameSuffix
	 */
	private void detectOutFilenameSuffix(String outFilenameSuffix) {
		try {
			CheckerUtil.checkNotEmpty(outFilenameSuffix);
			this.outFilenameSuffix = outFilenameSuffix;
		} catch(CheckerException ce) {
			logger.debug(DEBUG_OUT_FILENAME_SUFFIX_NULL);
			this.outFilenameSuffix = "";
		}
	}

	/**
	 * @return the changePatientIdValue
	 */
	public String getChangePatientIDValue() {
		return changePatientIdValue;
	}

	/**
	 * @return the changePatientIdOverwriteOriginalFile
	 */
	public boolean isChangePatientIdOverwriteOriginalFile() {
		return changePatientIdOverwriteOriginalFile;
	}

	/**
	 * @return the inFilePath
	 */
	public Path getInFilePath() {
		return inFilePath;
	}

	/**
	 * @return the inFolderPath
	 */
	public Path getInFolderPath() {
		return inFolderPath;
	}

	/**
	 * @return the manualMode
	 */
	public boolean isManualMode() {
		return manualMode;
	}

	/**
	 * @return the outFilenameSuffix
	 */
	public String getOutFilenameSuffix() {
		return outFilenameSuffix;
	}

	/**
	 * @return the outFolderPath
	 */
	public Path getOutFolderPath() {
		return outFolderPath;
	}
	
	

}
