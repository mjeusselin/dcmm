package fr.mjeu.dcmm.exception;

/**
 * Enumeration listing error messages.
 * 
 * @author Maxime
 *
 */
public enum DcmExceptionMessage {
	ERROR_FILE_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE("File doesn't exist or is not readable / writable : "),
	ERROR_FOLDER_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE("Folder doesn't exist or is not readable / writable : "),
	ERROR_GLOBAL("An error has happened."),
	ERROR_NULL("Object is null."),
	ERROR_NULL_OR_EMPTY("String is null or empty.");
	
	private String message;
	
	DcmExceptionMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
