package fr.mjeu.dcmm.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;

/**
 * DICOM util class
 * 
 * @author Maxime
 *
 */
public class DcmUtil {
	
	/**
	 * Get Dcm information from DICOM path
	 * @param p
	 * @return
	 */
	public static Attributes readDcmMetadata(Path p) throws DcmException {
		
		CheckerUtil.checkFileExistsFromPath(p);
		CheckerUtil.checkFilenameDcmExtension(p.getFileName().toString());
		
		File f = DcmFileUtil.getFileFromPath(p);
		Attributes attributes = null;
		DicomInputStream dis = null;
		
		if(f != null) {
			try {
			
				dis = new DicomInputStream(f);
				if(dis != null) {
					
					// read metadata except metadata information
					attributes = dis.readDataset(-1, -1);
				}
				
			} catch (IOException ie) {
				StringBuilder sb = new StringBuilder();
				sb.append(DcmExceptionMessage.ERROR_FILE_READ.getMessage())
					.append(p.toString());
				
				throw new DcmException(sb.toString(), ie);
			} finally {
				
				if(dis != null) {
					
					try {
						dis.close();
					} catch (IOException ie) {
						throw new DcmException(DcmExceptionMessage.ERROR_INPUT_STREAM_CLOSE.getMessage(), ie);
					}
					
				}
				
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_FILE_NULL.getMessage())
				.append(p.toString());
			
			throw new DcmException(sb.toString());
		}
		
		return attributes;
	}
	
	/**
	 * Write metadata to DICOM file by its Path object
	 * @param a attributes
	 * @param p Path object 
	 */
	public static boolean writeDcmMetadata(Attributes a, Path p) throws DcmException {
		
		if(p == null) {
			throw new DcmException(DcmExceptionMessage.ERROR_PATH_NULL.getMessage());
		}
		
		if(a == null) {
			throw new DcmException(DcmExceptionMessage.ERROR_ATTRIBUTES_NULL.getMessage());
		}
		
		boolean metadataWritten = false;
		File f = DcmFileUtil.getFileFromPath(p);
		DicomOutputStream dos = null;
		
		if(f != null) {
			try {
			
				dos = new DicomOutputStream(f);
				if(dos != null) {
					
					// read metadata except metadata information
					a.writeTo(dos);
					metadataWritten = true;
				}
				
			} catch (IOException ie) {
				StringBuilder sb = new StringBuilder();
				sb.append(DcmExceptionMessage.ERROR_FILE_WRITE.getMessage())
					.append(p.toString());
				
				throw new DcmException(sb.toString(), ie);
			} finally {
				
				if(dos != null) {
					
					try {
						dos.close();
					} catch (IOException ie) {
						throw new DcmException(DcmExceptionMessage.ERROR_INPUT_STREAM_CLOSE.getMessage(), ie);
					}
					
				}
				
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(DcmExceptionMessage.ERROR_FILE_NULL.getMessage())
				.append(p.toString());
			
			throw new DcmException(sb.toString());
		}
		
		
		return metadataWritten;
		
	}
	
}
