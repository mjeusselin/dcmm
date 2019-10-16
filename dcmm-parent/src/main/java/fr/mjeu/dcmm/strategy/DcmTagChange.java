package fr.mjeu.dcmm.strategy;

import java.time.LocalDateTime;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import fr.mjeu.dcmm.enumeration.TraceEventEnum;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.mongo.dao.DaoConfig;
import fr.mjeu.dcmm.mongo.dao.TraceDao;
import fr.mjeu.dcmm.mongo.dao.impl.TraceDaoImpl;
import fr.mjeu.dcmm.mongo.model.TraceDto;
import fr.mjeu.dcmm.util.CheckerUtil;

/**
 * Change patient id in DICOM
 * 
 * @author Maxime
 *
 */
public class DcmTagChange implements DcmStrategy {
	
	static Logger logger = LoggerFactory.getLogger(DcmTagChange.class);
	
	public static final int dataElementTag = Tag.PatientID;
	
	private static final String DEBUG_EXECUTE_1 = "change data element tag ";
	private static final String DEBUG_EXECUTE_2 = " with value ";
	private static final String TRACE_EX_BEGIN = "begin execute with params : ";
	private static final String TRACE_EX_END = "end execute";
	private static final String TRACE_DAO_IMPL = "traceDaoImpl";
	private static VR vr = VR.LO;
	
	private String dataValueField;
	private boolean overwriteOriginalFile;
	
	private AbstractApplicationContext context;
	private TraceDao traceDao;
	
	public DcmTagChange(String dataValueField, boolean overwriteOriginalFile) throws CheckerException {
		CheckerUtil.checkNotEmpty(dataValueField);
		this.dataValueField = dataValueField;
		this.overwriteOriginalFile = overwriteOriginalFile;
		
		this.context = new AnnotationConfigApplicationContext(DaoConfig.class);
		this.traceDao = (TraceDaoImpl) context.getBean(TRACE_DAO_IMPL);
	}
	
	@Override
	public DcmUnit execute(DcmUnit unitToModify) throws CheckerException {

		CheckerUtil.checkNotNull(unitToModify);
		CheckerUtil.checkNotNull(unitToModify.getDataset());
		
		logger.trace(TRACE_EX_BEGIN + unitToModify.toString());
		
		StringBuilder debugSb = new StringBuilder();
		debugSb.append(DEBUG_EXECUTE_1).append(dataElementTag)
			.append(DEBUG_EXECUTE_2).append(dataValueField);
		logger.debug(debugSb.toString());
		
		
		Attributes attributes = unitToModify.getDataset();
		attributes.setString(dataElementTag, vr, dataValueField);
		unitToModify.setDataset(attributes);
		
		createTrace(TraceEventEnum.PATIENT_ID_TAG_CHANGE, unitToModify);
		
		logger.trace(TRACE_EX_END);
		
		return unitToModify;
	}
	
	/**
	 * @return the dataValueField
	 */
	public String getDataValueField() {
		return dataValueField;
	}

	@Override
	public boolean getOverwriteOriginalFile() {
		return this.overwriteOriginalFile;
	}
	
	/**
	 * Create patient id tag change event trace
	 * @param unit
	 */
	private void createTrace(TraceEventEnum traceEvent, DcmUnit unit) {
		TraceDto trace = new TraceDto();
		trace.setTraceCreationDateTime(LocalDateTime.now());
		trace.setTraceEvent(traceEvent.getType());
		if(unit.getInFilePath() != null) {
			trace.setInputFilePathStr(unit.getInFilePath().toString());
		}
		if(unit.getOutFilePath() != null) {
			trace.setOutputFilePathStr(unit.getOutFilePath().toString());
		}
		trace.setValueTag(this.dataValueField);
		
		try {
			this.traceDao.createTrace(trace);
		} catch (Exception e) {
			// if any exception arrises, it logs trace not monitored in database
			// it's a choice to be able to use application even if no database is up
			logger.debug(trace.toString());
		}
	}

}
