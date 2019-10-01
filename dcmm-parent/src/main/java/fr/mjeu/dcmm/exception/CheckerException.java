package fr.mjeu.dcmm.exception;

/**
 * Checker Exception class for the application
 * 
 * @author Maxime
 *
 */
public class CheckerException extends DcmException {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2912090123692086560L;

	public CheckerException(String message) {
		super(message);
	}
	
	public CheckerException(String message, Throwable cause) {
		super(message, cause);
	}
}
