package fr.mjeu.dcmm.strategy;

import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.util.CheckerUtil;

/**
 * Watermark png image in DICOM
 * 
 * @author Maxime
 *
 */
public class DcmWatermark implements DcmStrategy {

	static Logger logger = LoggerFactory.getLogger(DcmStrategy.class);
	
	private static String DICOM_FORMAT_NAME = "DICOM";
	
	private Path dicomPath;
	private Path imagePath;
	
	public DcmWatermark(Path dicomPath, Path imagePath) throws CheckerException {
		CheckerUtil.checkFileExistsFromPath(dicomPath);
		CheckerUtil.checkFilenameDcmExtension(dicomPath.getFileName().toString());
		CheckerUtil.checkFileExistsFromPath(imagePath);
		CheckerUtil.checkFilenamePngExtension(imagePath.getFileName().toString());
		this.dicomPath = dicomPath;
		this.imagePath = imagePath;
	}
	
	@Override
	public DcmUnit execute(DcmUnit unitToModify) throws DcmException {
		return null;
	}

}
