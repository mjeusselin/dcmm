package fr.mjeu.dcmm;
import java.nio.file.Path;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.strategy.DcmStrategy;
import fr.mjeu.dcmm.util.CheckerUtil;
import fr.mjeu.dcmm.util.DcmUtil;

/**
 * Class in charge of DICOM manipulations
 * 
 * @author Maxime
 *
 */
public class DcmBuilder {
	
	static Logger logger = LoggerFactory.getLogger(DcmBuilder.class);
	
	private static final String DEBUG_APPLY_STRATEGIES = "applying strategies";
	private static final String DEBUG_OVERWRITE_INPUT_FILE = "input file overwrite is configured";
	private static final String DEBUG_WRITE_RESULT_INPUT = "trying to write result in input DICOM file : ";
	private static final String DEBUG_WRITE_RESULT_OUTPUT = "trying to write result in output DICOM file : ";
	private static final String DEBUG_RESULT_WRITTEN = "result written with success : {}";
	private static final String TRACE_BUILD_BEGIN = "begin build";
	private static final String TRACE_BUILD_END = "end build";

	private DcmUnit dcmUnit;
	private ArrayList<DcmStrategy> strategies = new ArrayList<>();
	
	public DcmBuilder(Path inFilePath, Path outFilePath) throws DcmException {
		CheckerUtil.checkFileExistsFromPath(inFilePath);
		CheckerUtil.checkNotNull(outFilePath);
		CheckerUtil.checkFolderExists(outFilePath.getParent().toString());
		
		this.dcmUnit = DcmUtil.readDcm(inFilePath);
		this.dcmUnit.setOutFilePath(outFilePath);
		
	}
	
	/**
	 * Apply build strategies to DICOM and write final DICOM
	 */
	public void build() throws DcmException {
		logger.trace(TRACE_BUILD_BEGIN);
		
		// apply build strategies
		logger.debug(DEBUG_APPLY_STRATEGIES);
		for(DcmStrategy s : this.strategies) {
			this.dcmUnit = s.execute(this.dcmUnit);
			
			// overwrite input file if configured
			if(s.getOverwriteOriginalFile()) {
				logger.debug(DEBUG_OVERWRITE_INPUT_FILE);
				// write in input
				logger.debug(DEBUG_WRITE_RESULT_INPUT + this.dcmUnit.getInFilePath().toString());
				boolean resultWrittenInput = DcmUtil.writeDcm(this.dcmUnit, this.dcmUnit.getInFilePath());
				logger.debug(DEBUG_RESULT_WRITTEN, resultWrittenInput);
			}
		}
		
		// write in output
		logger.debug(DEBUG_WRITE_RESULT_OUTPUT + this.dcmUnit.getOutFilePath().toString());
		boolean resultWrittenOutput = DcmUtil.writeDcm(this.dcmUnit, this.dcmUnit.getOutFilePath());
		logger.debug(DEBUG_RESULT_WRITTEN, resultWrittenOutput);
		logger.trace(TRACE_BUILD_END);
	}

	/**
	 * @return the dcmUnit
	 */
	public DcmUnit getDcmUnit() {
		return dcmUnit;
	}
	
	/**
	 * Add a strategy to builder's strategies
	 * @param strategy strategy to add
	 * @throws DcmException
	 */
	public void addStrategy(DcmStrategy strategy) throws DcmException {
		if(strategy != null) {
			this.strategies.add(strategy);
		} else {
			throw new DcmException(DcmExceptionMessage.ERROR_ADD_NULL_STRATEGY.getMessage());
		}
	}

	/**
	 * @return the strategies
	 */
	public ArrayList<DcmStrategy> getStrategies() {
		return strategies;
	}

	/**
	 * @param strategies the strategies to set
	 */
	public void setStrategies(ArrayList<DcmStrategy> strategies) {
		this.strategies = strategies;
	}
	
}
