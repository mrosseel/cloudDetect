/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:23:07 AM
 * To change this template use Options | File Templates.
 */
package calculation.splitters;

import media.image.CloudImageResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract ImageSplitter, provides the framework for all ImageSplitters.
 * Provides pre and post data manipulation methods, and getters/setters for best
 * split and % best split.
 * 
 * @author mike
 * 
 */

public abstract class AbstractGenericImageSplitter implements ImageSplitter {
    public static int START_AND_END_AVOIDANCE = 2;

    private static Log log = LogFactory
            .getLog(AbstractGenericImageSplitter.class);

    private int bestSplitterLocation;

    private int pctSplitterLocation;

    private double result;

    protected static int NO_MORE_LOCATIONS = -1;

    /*
     * (non-Javadoc)
     * 
     * @see metrics.splitters.Splitter#compute(media.image.CloudImage)
     */
    public void split(CloudImageResult image) {
        double[] data = image.getData();
        preDataManipulation(data);

        // choose a begin and end-point for the splitter
        int length = data.length;
        int splitterBegin = START_AND_END_AVOIDANCE - 1;
        int splitterEnd = length - START_AND_END_AVOIDANCE;
        int bestSplitterLocation = -1;
        int splitterLocation;
        double biggestValueDifference = Double.MIN_VALUE;
        double valueDifference;
        double valueLeft;
        double valueRight;

        for (splitterLocation = splitterBegin; splitterLocation != splitterEnd; splitterLocation++) {
            valueLeft = calculateValue(data, 0, splitterLocation);
            valueRight = calculateValue(data, splitterLocation + 1, length - 1);
            valueDifference = Math.abs(valueRight - valueLeft);

            if (valueDifference > biggestValueDifference) {
                biggestValueDifference = valueDifference;
                bestSplitterLocation = splitterLocation;
            }

            if (log.isDebugEnabled()) {
                log.debug("diff = " + valueDifference + " at "
                        + splitterLocation + " left = " + valueLeft
                        + " right = " + valueRight + " bestSplitter = "
                        + bestSplitterLocation);
            }
        }

        setResult(biggestValueDifference);
        setBestSplitterLocation(bestSplitterLocation);
        setPctSplitterLocation((int) Math.round(((double) bestSplitterLocation
                / (double) length * 100)));
        postDataManipulation(data);
        log.info("Best splitter located at pos " + bestSplitterLocation + " ("
                + +((double) bestSplitterLocation / (double) length * 100)
                + " %)");
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    protected abstract void preDataManipulation(double[] data);

    protected abstract void postDataManipulation(double[] data);

    /**
     * calculates a value
     */
    protected abstract double calculateValue(double[] data, int from, int to);

    /*
     * (non-Javadoc)
     * 
     * @see metrics.splitters.Splitter#getBestSplitterLocation()
     */
    public int getBestSplitterLocation() {
        return bestSplitterLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see metrics.splitters.Splitter#setBestSplitterLocation(int)
     */
    public void setBestSplitterLocation(int i) {
        bestSplitterLocation = i;
    }

    /*
     * (non-Javadoc)
     * 
     * @see metrics.splitters.Splitter#getPctSplitterLocation()
     */
    public int getPctSplitterLocation() {
        return pctSplitterLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see metrics.splitters.Splitter#setPctSplitterLocation(int)
     */
    public void setPctSplitterLocation(int i) {
        pctSplitterLocation = i;
    }

}
