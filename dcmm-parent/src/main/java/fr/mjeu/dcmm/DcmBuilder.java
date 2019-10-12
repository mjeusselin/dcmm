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

	private String changePatientIdValue;
	private DcmUnit dcmUnit;
	private ArrayList<DcmStrategy> strategies = new ArrayList<>();
	
	public DcmBuilder(Path inFilePath, String changePatientIdValue) throws DcmException {
		CheckerUtil.checkFileExistsFromPath(inFilePath);
		CheckerUtil.checkNotEmpty(changePatientIdValue);
		
		this.changePatientIdValue = changePatientIdValue;
		
		this.dcmUnit = DcmUtil.readDcm(inFilePath);
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
		logger.debug(DEBUG_WRITE_RESULT + this.dcmUnit.getPath().toString());
		DcmUtil.writeDcm(this.dcmUnit, this.dcmUnit.getPath());
		logger.trace(TRACE_BUILD_END);
	}

	
	
	/**
	 * @return the changePatientIdValue
	 */
	public String getChangePatientIDValue() {
		return changePatientIdValue;
	}

	/**
	 * @param changePatientIdValue the changePatientIdValue to set
	 */
	public void setChangePatientIDValue(String changePatientIdValue) {
		this.changePatientIdValue = changePatientIdValue;
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
