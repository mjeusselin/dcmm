package fr.mjeu.dcmm;

import org.dcm4che3.data.Attributes;

/**
 * Class with Dicom data
 * 
 * @author Maxime
 *
 */
public class DcmUnit {
	
	private Attributes dataset;
	private Attributes fmi;
	
	/**
	 * @return the dataset
	 */
	public Attributes getDataset() {
		return dataset;
	}
	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(Attributes dataset) {
		this.dataset = dataset;
	}
	/**
	 * @return the fmi
	 */
	public Attributes getFmi() {
		return fmi;
	}
	/**
	 * @param fmi the fmi to set
	 */
	public void setFmi(Attributes fmi) {
		this.fmi = fmi;
	}
	

}
