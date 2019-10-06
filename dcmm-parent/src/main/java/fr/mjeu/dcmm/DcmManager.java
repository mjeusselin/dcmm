package fr.mjeu.dcmm;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.strategy.DcmStrategy;
import fr.mjeu.dcmm.strategy.DcmTagChange;
import fr.mjeu.dcmm.util.CheckerUtil;

public class DcmManager {
	
	static Logger logger = LoggerFactory.getLogger(DcmManager.class);
	
	private static final String DEBUG_BUILD = "building from file ";
	private static final String DEBUG_MANUAL_MODE = "entering manual mode";
	private static final String TRACE_EXECUTE_BEGIN = "begin execute";
	private static final String TRACE_EXECUTE_END = "end execute";
	
	private String changePatientIDValue;	
	private Path inFilePath;
	
	public DcmManager(String inFolderAbsolutePathStr,
			String inFilename,
			String changePatientIDValue) throws DcmException {
		if(inFilename != null) {
			// manual mode
			this.inFilePath = CheckerUtil.checkFileExists(inFolderAbsolutePathStr, inFilename);
			CheckerUtil.checkNotEmpty(changePatientIDValue);
			this.changePatientIDValue = changePatientIDValue;
		} else {
			// monitoring mode not yet implemented
			throw new DcmException(DcmExceptionMessage.ERROR_MONITORING_NOT_IMPLEMENTED.getMessage());
		}
	}
	
	/**
	 * Call builder in order to modify DICOM file(s)
	 */
	public void execute() throws DcmException {
		logger.trace(TRACE_EXECUTE_BEGIN);
		if(this.inFilePath != null) {
			// manual mode
			logger.debug(DEBUG_MANUAL_MODE);
			DcmBuilder db = new DcmBuilder(this.inFilePath, this.changePatientIDValue);
			DcmStrategy tagChangeStrategy = new DcmTagChange(this.changePatientIDValue);
			db.addStrategy(tagChangeStrategy);
			logger.debug(DEBUG_BUILD + inFilePath.toString());
			db.build();
		}
		logger.trace(TRACE_EXECUTE_END);
	}

}
