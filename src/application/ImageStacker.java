/*
 * Created on 8-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package application;

import javax.media.format.RGBFormat;

import util.TextProgressBar;
import util.Median;

/**
 * Util to stack images.
 *  
 * @author Mike
 *
 */
public class ImageStacker {
	public static int FRAMES_TO_STACK = 150;
	private short[][] stackedData;
	private int stackCounter = 0;
	private RGBFormat format = null;
	private TextProgressBar progress;

	/**
	 * 
	 */
	public ImageStacker(RGBFormat format) {
		this.format = format;
	}

	public void stackData(short[] data) {
		if (stackedData == null || isStacked()) {
			stackedData = new short[data.length - 2][FRAMES_TO_STACK];
			stackCounter = 0;
			progress = new TextProgressBar(40, 0.0, (double) FRAMES_TO_STACK);
		}

		for (int counter = 0; counter != data.length - 2; counter++) {
			stackedData[counter][stackCounter] += data[counter];
		}

		stackCounter++;
		progress.update(stackCounter);
		//progress.print();
	}

	public short[] getOriginalStackData() {
		short[] resultData = new short[stackedData.length];
		double y, cb, cr;
		int r, g, b;
		int redmask, greenmask, bluemask;
		redmask = format.getRedMask();
		greenmask = format.getGreenMask();
		bluemask = format.getBlueMask();

		short tmp;
		for (int counter = 0; counter != stackedData.length; counter++) {
			tmp =
				(short) Median.find(
					stackedData[counter],
					0,
					FRAMES_TO_STACK - 1);
			
			resultData[counter] = tmp;
		}

		return resultData;
	}

	public double[] getProcessedStackData() {
		double[] resultData = new double[stackedData.length];
		double y, cb, cr;
		int r, g, b;
		int redmask, greenmask, bluemask;
		redmask = format.getRedMask();
		greenmask = format.getGreenMask();
		bluemask = format.getBlueMask();

		short tmp;
		for (int counter = 0; counter != stackedData.length; counter++) {
			tmp =
				(short) Median.find(
					stackedData[counter],
					0,
					FRAMES_TO_STACK - 1);

			r = (tmp & redmask) >> 10;
			g = (tmp & greenmask) >> 5;
			b = tmp & bluemask;

			y = 0.2989 * r + 0.5866 * g + 0.1145 * b;
			cb = -0.1687 * r - 0.3312 * g + 0.5000 * b;
			cr = 0.5000 * r - 0.4183 * g - 0.0816 * b;

			// smallest change = 1
			y = y * 3.125;//8.7336244541484716157205240174672;

			resultData[counter] = y;
		}

		return resultData;
	}

	public boolean isStacked() {
		return stackCounter == FRAMES_TO_STACK;
	}

}
