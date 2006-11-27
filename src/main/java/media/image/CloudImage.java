/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;
import java.awt.image.PixelGrabber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Mike
 * 
 * This is a wrapper for an Image, with convenience methods to convert it to an
 * array of primitives if that's more convenient for our calculations.
 * 
 * TODO check for null conditions!
 */
public class CloudImage {
	private static Log log = LogFactory.getLog(CloudImage.class);

	/** where does this image come from? */
	private String origin;
	
	private Image image;

	private double[] data;

	public CloudImage(Image image) {
		setImage(image);
	}

	public CloudImage(double[] data) {
		setData(data);
	}

	private void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		if(image == null) {
			this.image = createImage(data);
		}
		
		return this.image;
	}
	
	public double[] getData() {
		if(data == null) {
			this.data = createData(image);
		}
		
		return this.data;
	}

	private void setData(double[] data) {
		this.data = data;
	}

	public int getWidth() {
		return image.getWidth(null);
	}
	
	public int getHeight() {
		return image.getHeight(null);
	}
	
	private double[] createData(Image image) {
		return intToDoubleArray(getIntArrayFromImage(image));
	}

	private Image createImage(double[] data) {
		log.warn("Not implemented yet!");
		return null;
	}

	
	
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	private double[] intToDoubleArray(int[] data) {
		double[] result = new double[data.length];
		for (int dataCounter = 0; dataCounter < data.length; dataCounter++) {
			result[dataCounter] = data[dataCounter];
		}
		return result;
	}
	
	//////////// example code for pixelgrabber and memoryimagesource
	


	private int[] getIntArrayFromImage(Image image) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		//start.getGraphics().getColor().getColorSpace().
		int [] pixstart = new int[width * height];
		grab(image, pixstart, width, height);
		return pixstart;
//		System.arraycopy(pixstart, 0, pixbuf, 0, width * height);
//		memImg = new MemoryImageSource(width, height, pixbuf, 0, width);
//		memImg.setAnimated(true);
//		Image actual = Toolkit.getDefaultToolkit().createImage(memImg);
	}

	protected void grab(Image img, int pix[], int width, int height) {
		// Kopiert die Bilddaten des ?bergebenen
		// Bildes in das ?bergebene Array
		PixelGrabber grabber = new PixelGrabber(img, 0, 0, width, height, pix,
				0, width);
		try {
			grabber.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
