package fr.mjeu.dcmm.exception;

/**
 * Global Exception class for the application.
 * 
 * @author Maxime
 *
 */
public class DcmException extends Exception {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6875247338465369035L;

	public DcmException(String message) {
		super(message);
	}
	
	public DcmException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
