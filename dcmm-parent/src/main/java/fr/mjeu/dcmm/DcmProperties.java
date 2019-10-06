package fr.mjeu.dcmm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dcm")
public class DcmProperties {

	private String dataValueField;

	/**
	 * @return the dataValueField
	 */
	public String getDataValueField() {
		return dataValueField;
	}

	/**
	 * @param dataValueField the dataValueField to set
	 */
	public void setDataValueField(String dataValueField) {
		this.dataValueField = dataValueField;
	}
	
	
}
