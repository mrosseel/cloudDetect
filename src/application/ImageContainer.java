/*
 * Created on 8-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package application;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImageContainer {
	private short[] originalImage;
	private double[] processedImage;
		
	/**
	 * 
	 */
	public ImageContainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public short[] getOriginalImage() {
		return originalImage;
	}

	/**
	 * @return
	 */
	public double[] getProcessedImage() {
		return processedImage;
	}

	/**
	 * @param ses
	 */
	public void setOriginalImage(short[] ses) {
		originalImage = ses;
	}

	/**
	 * @param ds
	 */
	public void setProcessedImage(double[] ds) {
		processedImage = ds;
	}
}
