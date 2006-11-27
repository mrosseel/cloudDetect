/*
 * Created on 5-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package metrics.splitters;

import util.MathMethods;

/**
 * @author Mike
 *
 *

 */
public class SlidingWindowSplitter extends Splitter {
	public static final int DEF_WINDOW = 20;

	/* (non-Javadoc)
		 * @see metrics.Splitter#split(double[])
		 */
	public int split(double[] data) {
		return bestWindow(data, DEF_WINDOW);
	}

	/* (non-Javadoc)
	 * @see metrics.Splitter#split(double[])
	 */
	public int split(double[] data, int windowSize) {
		return bestWindow(data, windowSize);
	}

	private int bestWindow(double[] data, int window) {
		int end = data.length;
		int bestWindowStart = 0;
		double  bestWindowValue = Double.MIN_VALUE;
		double  tmpDifference;

		// TODO : last element is not processed by differences method !!!
		for (int counter = 0; counter + window != end; counter++) {
			tmpDifference = difference(data, counter, counter + window);
			if (tmpDifference > bestWindowValue) {
				bestWindowStart = counter;
				bestWindowValue = tmpDifference;
			}
		}

		return (int) (bestWindowStart + window / 2);
	}

	private double difference(double[] data, int begin, int end) {
		double smallest = MathMethods.min(data, begin, end);
		double biggest = MathMethods.max(data, begin, end);

		return biggest - smallest;
	}

}
