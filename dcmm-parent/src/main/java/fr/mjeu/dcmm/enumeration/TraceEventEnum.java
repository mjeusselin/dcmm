package fr.mjeu.dcmm.enumeration;

/**
 * Enum with trace events tracked in database
 * 
 * @author Maxime
 *
 */
public enum TraceEventEnum {
	PATIENT_ID_TAG_CHANGE("PATIENT_ID_TAG_CHANGE"),
	WATERMARK_LOGO("WATERMARK_LOGO"),
	WRITE_INPUT_RESULT("WRITE_INPUT_RESULT"),
	WRITE_OUTPUT_RESULT("WRITE_OUTPUT_RESULT");
	
	private String type;
	
	TraceEventEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
