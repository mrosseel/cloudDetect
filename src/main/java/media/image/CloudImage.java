/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;


/**
 * @author Mike
 * 
 * This is a wrapper for an Image, with convenience methods to convert it to an
 * array of primitives if that's more convenient for our calculations.
 */
public class CloudImage {

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

	public void setImage(Image image) {
		this.image = image;
		setData(image);
	}

	public void setData(double[] data) {
		this.data = data;
		setImage(data);
	}

	private void setData(Image image) {

	}

	private void setImage(double[] data) {

	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	//////////// example code for pixelgrabber and memoryimagesource
	


	private void Transformer(Image start) {
		int width = start.getWidth(null);
		int height = start.getHeight(null);
		//start.getGraphics().getColor().getColorSpace().
		int [] pixstart = new int[width * height];
		grab(start, pixstart, width, height);
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
