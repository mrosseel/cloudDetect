/*
*
 */
package media.image;

import javax.media.format.RGBFormat;

import util.Median;
import util.TextProgressBar;


/**
 * Util to stack images.
 *  
 * @author Mike
 * 
 * TODO remove text progress bar (add listeners?)
 * TODO uses JMF RGBFormat, try to convert this to standard java if possible
 */
public class RGBImageStacker {
	public static int FRAMES_TO_STACK = 150;
	private short[][] stackedData;
	private int stackCounter = 0;
	private RGBFormat format = null;
	private TextProgressBar progress;

	/**
	 * 
	 */
	public RGBImageStacker(RGBFormat format) {
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

	/**
	 * No processing occured, the original data has only been median-stacked.
	 * 
	 * @return
	 */
	public short[] getOriginalStackData() {
		short[] resultData = new short[stackedData.length];
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

	/**
	 * Data is median stacked, converted to B/W and scaled so that the 
	 * smallest possible change is 1 unit
	 * TODO recalculate scaling and document
	 * @return
	 */
	public double[] getProcessedStackData() {
		double[] resultData = new double[stackedData.length];
		double y;
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
