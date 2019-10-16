package fr.mjeu.dcmm;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import fr.mjeu.dcmm.enumeration.TraceEventEnum;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.mongo.dao.DaoConfig;
import fr.mjeu.dcmm.mongo.dao.TraceDao;
import fr.mjeu.dcmm.mongo.dao.impl.TraceDaoImpl;
import fr.mjeu.dcmm.mongo.model.TraceDto;
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
	private static final String TRACE_DAO_IMPL = "traceDaoImpl";
	
	private DcmUnit dcmUnit;
	private ArrayList<DcmStrategy> strategies = new ArrayList<>();
	
	private AbstractApplicationContext context;
	private TraceDao traceDao;
	
	public DcmBuilder() {
		
	}
	
	public DcmBuilder(Path inFilePath, Path outFilePath) throws DcmException {
		CheckerUtil.checkFileExistsFromPath(inFilePath);
		CheckerUtil.checkNotNull(outFilePath);
		CheckerUtil.checkFolderExists(outFilePath.getParent().toString());
		
		this.dcmUnit = DcmUtil.readDcm(inFilePath);
		this.dcmUnit.setOutFilePath(outFilePath);
		
		this.context = new AnnotationConfigApplicationContext(DaoConfig.class);
		this.traceDao = (TraceDaoImpl) context.getBean(TRACE_DAO_IMPL);
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
				if(resultWrittenInput) {
					createWriteTrace(this.dcmUnit, TraceEventEnum.WRITE_INPUT_RESULT);
				}
			}
		}
		
		// write in output
		logger.debug(DEBUG_WRITE_RESULT_OUTPUT + this.dcmUnit.getOutFilePath().toString());
		boolean resultWrittenOutput = DcmUtil.writeDcm(this.dcmUnit, this.dcmUnit.getOutFilePath());
		logger.debug(DEBUG_RESULT_WRITTEN, resultWrittenOutput);
		
		if(resultWrittenOutput) {
			createWriteTrace(this.dcmUnit, TraceEventEnum.WRITE_OUTPUT_RESULT);
		}
		
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
	
	/**
	 * Create write input/output event trace
	 * @param unit
	 */
	private void createWriteTrace(DcmUnit unit, TraceEventEnum traceEvent) {
		TraceDto trace = new TraceDto();
		trace.setTraceCreationDateTime(LocalDateTime.now());
		trace.setTraceEvent(traceEvent.getType());
		trace.setInputFilePathStr(unit.getInFilePath().toString());
		trace.setOutputFilePathStr(unit.getOutFilePath().toString());
		try {
			this.traceDao.createTrace(trace);
		} catch (Exception e) {
			// if any exception arrises, it logs trace not monitored in database
			// it's a choice to be able to use application even if no database is up
			logger.debug(trace.toString());
		}
	}
	
}
