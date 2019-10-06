package fr.mjeu.dcmm.strategy;

import fr.mjeu.dcmm.DcmUnit;
import fr.mjeu.dcmm.exception.DcmException;

/**
 * Interface for strategies which modify Attributes of DICOM files
 * 
 * @author Maxime
 *
 */
public interface DcmStrategy {

	/*
	 * This boolean precises if original DICOM file must be overriden
	 * by this strategy's execution
	 */
	public boolean overrideOriginalFile = true;
	
	public DcmUnit execute(DcmUnit unitToModify) throws DcmException;
	
}
