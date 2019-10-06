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
	private Path path;
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
	
	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.path != null) {
			sb.append(this.path);
		}
		return sb.toString();
	}
}
