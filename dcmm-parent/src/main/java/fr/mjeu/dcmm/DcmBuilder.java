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
	private static final String DEBUG_WRITE_RESULT = "trying to write result in DICOM file : ";
	private static final String TRACE_BUILD_BEGIN = "begin build";
	private static final String TRACE_BUILD_END = "end build";

	private String changePatientIDValue;
	private DcmUnit dcmUnit;
	private Path inFilePath;
	private ArrayList<DcmStrategy> strategies = new ArrayList<>();
	
	public DcmBuilder(Path inFilePath, String changePatientIDValue) throws DcmException {
		CheckerUtil.checkFileExistsFromPath(inFilePath);
		CheckerUtil.checkNotEmpty(changePatientIDValue);
		
		this.inFilePath = inFilePath;
		this.changePatientIDValue = changePatientIDValue;
		
		this.dcmUnit = DcmUtil.readDcm(this.inFilePath);
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
		}
		
		// write
		logger.debug(DEBUG_WRITE_RESULT + this.inFilePath.toString());
		DcmUtil.writeDcm(this.dcmUnit, this.inFilePath);
		logger.trace(TRACE_BUILD_END);
	}

	
	
	/**
	 * @return the changePatientIDValue
	 */
	public String getChangePatientIDValue() {
		return changePatientIDValue;
	}

	/**
	 * @param changePatientIDValue the changePatientIDValue to set
	 */
	public void setChangePatientIDValue(String changePatientIDValue) {
		this.changePatientIDValue = changePatientIDValue;
	}

	/**
	 * @return the inFilePath
	 */
	public Path getInFilePath() {
		return inFilePath;
	}

	/**
	 * @param inFilePath the inFilePath to set
	 */
	public void setInFilePath(Path inFilePath) {
		this.inFilePath = inFilePath;
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
