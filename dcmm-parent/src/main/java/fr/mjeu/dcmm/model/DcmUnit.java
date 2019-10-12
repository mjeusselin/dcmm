package fr.mjeu.dcmm.model;

import java.nio.file.Path;

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
	private Path inFilePath; // initial DICOM
	private Path outFilePath; // output DICOM
	
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
	 * @return the outFilePath
	 */
	public Path getOutFilePath() {
		return outFilePath;
	}
	/**
	 * @param outFilePath the outFilePath to set
	 */
	public void setOutFilePath(Path outFilePath) {
		this.outFilePath = outFilePath;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.inFilePath != null) {
			sb.append(this.inFilePath);
		}
		return sb.toString();
	}
}
