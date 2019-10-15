package fr.mjeu.dcmm.mongo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traces")
public class TraceDto {
	@Id
	private String id;
	private LocalDateTime traceCreationDateTime;
	private String traceEvent;
	private String imagePathStr;
	private String inputFilePathStr;
	private String outputFilePathStr;
	private String valueTag;
	
	
	public TraceDto() {
	}
	
	public TraceDto(
			String traceEvent,
			String inputFilePathStr,
			String outputFilePathStr) {
		this.traceEvent = traceEvent;
		this.inputFilePathStr = inputFilePathStr;
		this.outputFilePathStr = outputFilePathStr;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the traceCreationDateTime
	 */
	public LocalDateTime getTraceCreationDateTime() {
		return traceCreationDateTime;
	}

	/**
	 * @param traceCreationDateTime the traceCreationDateTime to set
	 */
	public void setTraceCreationDateTime(LocalDateTime traceCreationDateTime) {
		this.traceCreationDateTime = traceCreationDateTime;
	}

	/**
	 * @return the traceEvent
	 */
	public String getTraceEvent() {
		return traceEvent;
	}

	/**
	 * @param traceEvent the traceEvent to set
	 */
	public void setTraceEvent(String traceEvent) {
		this.traceEvent = traceEvent;
	}

	/**
	 * @return the imagePathStr
	 */
	public String getImagePathStr() {
		return imagePathStr;
	}

	/**
	 * @param imagePathStr the imagePathStr to set
	 */
	public void setImagePathStr(String imagePathStr) {
		this.imagePathStr = imagePathStr;
	}

	/**
	 * @return the inputFilePathStr
	 */
	public String getInputFilePathStr() {
		return inputFilePathStr;
	}

	/**
	 * @param inputFilePathStr the inputFilePathStr to set
	 */
	public void setInputFilePathStr(String inputFilePathStr) {
		this.inputFilePathStr = inputFilePathStr;
	}

	/**
	 * @return the outputFilePathStr
	 */
	public String getOutputFilePathStr() {
		return outputFilePathStr;
	}

	/**
	 * @param outputFilePathStr the outputFilePathStr to set
	 */
	public void setOutputFilePathStr(String outputFilePathStr) {
		this.outputFilePathStr = outputFilePathStr;
	}
	
	/**
	 * @return the valueTag
	 */
	public String getValueTag() {
		return valueTag;
	}

	/**
	 * @param valueTag the valueTag to set
	 */
	public void setValueTag(String valueTag) {
		this.valueTag = valueTag;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TraceDto{")
		.append("'id' : '")
		.append(this.id)
		.append("', ")
		.append("'traceCreationDateTime' : '")
		.append(this.traceCreationDateTime)
		.append("', ")
		.append("'traceEvent' : '")
		.append(this.traceEvent)
		.append("', ")
		.append("'inputFilePathStr' : '")
		.append(this.inputFilePathStr)
		.append("', ")
		.append("'outputFilePathStr' : '")
		.append(this.outputFilePathStr)
		.append("}");
		return sb.toString();
	}
}
