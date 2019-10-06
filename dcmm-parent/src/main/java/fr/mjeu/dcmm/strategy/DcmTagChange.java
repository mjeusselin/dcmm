package fr.mjeu.dcmm.strategy;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.DcmUnit;
import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.util.CheckerUtil;

public class DcmTagChange implements DcmStrategy {
	
	static Logger logger = LoggerFactory.getLogger(DcmTagChange.class);
	
	public static int dataElementTag = Tag.PatientID;
	
	private static String DEBUG_EXECUTE_1 = "change data element tag ";
	private static String DEBUG_EXECUTE_2 = " with value ";
	private static String TRACE_EX_BEGIN = "begin execute with params : ";
	private static String TRACE_EX_END = "end execute";
	private static VR vr = VR.LO;
	
	private String dataValueField;
	
	public DcmTagChange(String dataValueField) throws CheckerException {
		CheckerUtil.checkNotEmpty(dataValueField);
		this.dataValueField = dataValueField;
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
		
		logger.trace(TRACE_EX_END);
		
		return unitToModify;
	}

}
