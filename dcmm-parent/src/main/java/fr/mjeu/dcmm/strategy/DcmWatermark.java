package fr.mjeu.dcmm.strategy;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferUShort;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mjeu.dcmm.exception.CheckerException;
import fr.mjeu.dcmm.exception.DcmException;
import fr.mjeu.dcmm.exception.DcmExceptionMessage;
import fr.mjeu.dcmm.model.DcmUnit;
import fr.mjeu.dcmm.util.CheckerUtil;

/**
 * Watermark png image in DICOM
 * 
 * @author Maxime
 *
 */
public class DcmWatermark implements DcmStrategy {

	static Logger logger = LoggerFactory.getLogger(DcmWatermark.class);
	
	private static String DEBUG_EXECUTE = "watermarking Hera-MI logo";
	private static String DICOM_FORMAT_NAME = "DICOM";
	private static String TRACE_EX_BEGIN = "begin execute with params : ";
	private static String TRACE_EX_END = "end execute";
	
	private Path imagePath;
	
	public DcmWatermark(Path imagePath) throws CheckerException {
		CheckerUtil.checkFileExistsFromPath(imagePath);
		CheckerUtil.checkFilenamePngExtension(imagePath.getFileName().toString());
		this.imagePath = imagePath;
	}
	
	@Override
	public DcmUnit execute(DcmUnit unitToModify) throws DcmException {
		CheckerUtil.checkNotNull(unitToModify);
		
		logger.trace(TRACE_EX_BEGIN + unitToModify.toString());
		
		logger.debug(DEBUG_EXECUTE);
		try {
			Iterator<ImageReader> readersIterator = ImageIO.getImageReadersByFormatName(DICOM_FORMAT_NAME);
			ImageReader reader = (ImageReader) readersIterator.next();
			DicomImageReadParam defaultReadParam = (DicomImageReadParam) reader.getDefaultReadParam();
			ImageInputStream iis = ImageIO.createImageInputStream(unitToModify.getInFilePath().toFile());
			reader.setInput(iis, false);
			Raster raster = reader.readRaster(0, defaultReadParam);
			iis.close();
			short[] pixels = ((DataBufferUShort) raster.getDataBuffer()).getData();
			ColorModel colorModel = new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_GRAY),
				new int[]{16},
				false,
				false,
				Transparency.OPAQUE,
				DataBuffer.TYPE_USHORT
					);
			DataBufferUShort db = new DataBufferUShort(pixels, pixels.length);
			WritableRaster outRaster = Raster.createInterleavedRaster(
				db,
				raster.getWidth(),
				raster.getHeight(),
				raster.getWidth(),
				1,
				new int[1],
				null);
			BufferedImage bi = new BufferedImage(colorModel, outRaster, false, null);
			Graphics2D g = bi.createGraphics();
			BufferedImage bi2 = ImageIO.read(this.imagePath.toFile());
			int biWidth = bi.getWidth();
			int biHeight = bi.getHeight();
			int bi2Width = bi2.getWidth();
			int bi2Height = bi2.getHeight();
			int x = biWidth-bi2Width-1;
			int y = biHeight/2 - bi2Height/2;
			g.drawImage(bi2, x, y, null);
			g.dispose();
			DataBufferUShort dataBuffer = (DataBufferUShort) bi.getData().getDataBuffer();
			short[] shorts = dataBuffer.getData(0);
			byte[] bytes2 = new byte[shorts.length * 2];
			ByteBuffer.wrap(bytes2).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(shorts);
			unitToModify.getDataset().setValue(Tag.PixelData, VR.OW, bytes2);
		} catch (Exception e) {
			throw new DcmException(DcmExceptionMessage.ERROR_WATERMARK.getMessage() + unitToModify.toString(), e);
		}
		
		logger.trace(TRACE_EX_END);
		
		return unitToModify;
	}

	/**
	 * @return the imagePath
	 */
	public Path getImagePath() {
		return imagePath;
	}

	@Override
	public boolean getOverwriteOriginalFile() {
		return overwriteOriginalFile;
	}

}
