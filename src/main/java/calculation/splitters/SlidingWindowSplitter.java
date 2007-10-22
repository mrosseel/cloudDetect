/*
 * Created on 5-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation.splitters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.MathMethods;

/**
 * Splits data based on a sliding window.
 * 
 * Takes the min and max of each window, and presumes the biggest difference
 * will be the cutoff point. Very bad with noisy images, hotpixels, ... .
 * 
 * @author Mike
 * 
 */
public class SlidingWindowSplitter implements DoubleSplitter {
    private static Log log = LogFactory.getLog(SlidingWindowSplitter.class);

    /*
     * (non-Javadoc)
     * 
     * @see metrics.Splitter#split(double[])
     */
    public int split(double[] data) {
        return bestWindow(data, determineWindowSize(data.length));
    }

    /*
     * (non-Javadoc)
     * 
     * @see metrics.Splitter#split(double[])
     */
    public int split(double[] data, int windowSize) {
        return bestWindow(data, determineWindowSize(data.length, windowSize));
    }

    // min window is 2, otherwise differences don't work
    private int determineWindowSize(int dataLength) {
        int hint = (int) Math.max(Math.round(dataLength / 10.0), 2);
        return determineWindowSize(dataLength, hint);
    }

    private int determineWindowSize(int dataLength, int hint) {
        return Math.min(dataLength, hint);
    }

    private int bestWindow(double[] data, int window) {
        int end = data.length;
        int bestWindowStart = 0;
        double bestWindowValue = Double.MIN_VALUE;
        double tmpDifference;

        if (data.length < window) {
            String error = "Splitter window bigger than data! window = "
                    + window + " data length = " + data.length;
            log.error(error);
            throw new RuntimeException(error);
        }

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

    /**
     * Returns the difference between the smallest and biggest member of data.
     * 
     * @param data
     * @param begin
     * @param end
     * @return difference between max(data) and min(data)
     */
    private double difference(double[] data, int begin, int end) {
        double smallest = MathMethods.min(data, begin, end);
        double biggest = MathMethods.max(data, begin, end);

        return biggest - smallest;
    }

}
