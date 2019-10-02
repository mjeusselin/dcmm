package fr.mjeu.dcmm.exception;

/**
 * Enumeration listing error messages.
 * 
 * @author Maxime
 *
 */
public enum DcmExceptionMessage {
	ERROR_ATTRIBUTES_NULL("Attributes are null."),
	ERROR_FILENAME_EXTENSION_DCM("File format of this filename is not recognized : "),
	ERROR_FILE_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE("File doesn't exist or is not readable / writable : "),
	ERROR_FILE_NULL("File is null : "),
	ERROR_FILE_READ("Error reading stream for file : "),
	ERROR_FILE_WRITE("Error writing stream for file : "),
	ERROR_FOLDER_NOT_EXISTS_OR_NOT_READABLE_OR_WTRITABLE("Folder doesn't exist or is not readable / writable : "),
	ERROR_GLOBAL("An error has happened."),
	ERROR_INPUT_STREAM_CLOSE("Error closing input stream."),
	ERROR_NULL("Object is null."),
	ERROR_NULL_OR_EMPTY("String is null or empty."),
	ERROR_OUTPUT_STREAM_CLOSE("Error closing output stream."),
	ERROR_PATH_NULL("Path null");
	
	private String message;
	
	DcmExceptionMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
