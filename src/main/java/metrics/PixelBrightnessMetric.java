/*
 * Created on 4-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package metrics;

import media.image.CloudImage;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PixelBrightnessMetric implements Metric {

	/**
	 * 
	 */
	public PixelBrightnessMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
		 * 
		 */
	public double compute(CloudImage image) {
		double[] data = image.getData();
		double result = 0;
		for (int counter = 0; counter != data.length; counter++) {
			result += data[counter];
		}

		return result / (double) data.length;
	}

	/* 
	 * Gives the average pixel brightness of the entire image.
	 * 
	 * @see metrics.Metric#compute(short[])
	 */
	public double compute(short[] data) {
		double result = 0;
		for (int counter = 0; counter != data.length; counter++) {
			result += data[counter];
		}

		return result / (double) data.length;
	}
}
