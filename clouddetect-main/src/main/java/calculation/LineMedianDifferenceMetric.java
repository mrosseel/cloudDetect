/*
 * Created on 22-dec-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import media.image.CloudImageResult;
import util.Median;
import calculation.splitters.SlidingWindowSplitter;

/**
 * Reduce each line of the image to 1 median value, so we get a small strip of
 * width=1 and heigth=heigth, and perform a split on this line.
 * 
 * @author Mike
 * 
 */
public class LineMedianDifferenceMetric implements Metric {
    private static Log log = LogFactory
            .getLog(LineMedianDifferenceMetric.class);

    /*
     * (non-Javadoc)
     * 
     * @see metrics.Metric#compute(short[])
     */
    public double compute(CloudImageResult image) {
        double[] data = image.getMonochromeData();
        double median;

        int width = image.getWidth();
        int heigth = image.getHeight();
        double[] lineData = new double[heigth];

        for (int i = 0; i != heigth; i++) {
            median = Median.find(data, i * width, (i + 1) * width - 1);
            lineData[i] = median;
        }

        double result;
        SlidingWindowSplitter splitter = new SlidingWindowSplitter();
        int splitterLocation = splitter.split(lineData);
        int length = lineData.length - 1;
        double leftMedian = Median.find(lineData, 0, splitterLocation);
        double rightMedian = Median
                .find(lineData, splitterLocation + 1, length);
        result = Math.abs(leftMedian - rightMedian);
        if (log.isDebugEnabled()) {
            log.debug("left = " + leftMedian + " right = " + rightMedian);
            log.debug("splitter = " + splitterLocation);
            log.debug("Contrast = " + result);
        }

        return result;
    }
}
