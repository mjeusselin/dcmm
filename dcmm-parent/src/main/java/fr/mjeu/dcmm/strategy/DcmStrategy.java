package fr.mjeu.dcmm.strategy;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.model.DcmUnit;

/**
 * Interface for strategies which modify Attributes of DICOM files
 * 
 * @author Maxime
 *
 */
public interface DcmStrategy {

	/*
	 * This boolean precises if original DICOM file must be overwritten
	 * by this strategy's execution
	 */
	boolean overwriteOriginalFile = false;
	
	public DcmUnit execute(DcmUnit unitToModify) throws DcmException;
	
	public boolean getOverwriteOriginalFile();
	
}
