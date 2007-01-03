package calculation.splitters;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BisectionSplitter extends AbstractImageSplitter {
    private static Log log = LogFactory.getLog(BisectionSplitter.class);

    private double bestResult;

    public void split(CloudImage data) {
        bestResult = Double.MAX_VALUE;
        bestSplitterLocation = -1;
        bisection(data, 0, data.getHeight() * data.getWidth() - 1);
    }

    protected void bisection(CloudImage data, int start, int end) {
        int currentStart = start;
        int currentEnd = end;
        int locationLeft;
        int locationRight;
        double resultLeft;
        double resultRight;
        while (currentEnd - currentStart > 2) {
            locationLeft = currentStart + getOneThird(currentStart, currentEnd);
            locationRight = currentStart
                    + getTwoThirds(currentStart, currentEnd);
            resultLeft = metric.compute(data, locationLeft);
            resultRight = metric.compute(data, locationRight);
            if (log.isDebugEnabled()) {
                log.debug("currentstart = " + currentStart + " currentEnd = "
                        + currentEnd);
                log.debug("locationLeft = " + locationLeft
                        + " locationRight = " + locationRight);
                log.debug("resultLeft = " + resultLeft + " resultRight = "
                        + resultRight);
            }
            if (resultLeft > resultRight) {
                recordResult(resultLeft, locationLeft);
                currentEnd = locationRight;

            } else {
                recordResult(resultRight, locationRight);
                currentStart = locationLeft;
            }
        }
        setResult(bestResult);
        setBestSplitterLocation(bestSplitterLocation);
    }

    protected void recordResult(double currentResult, int currentLocation) {
        bestResult = currentResult;
        bestSplitterLocation = currentLocation;
    }

    protected int getOneThird(int start, int end) {
        return (int) Math.round((end - start) / 3.0);
    }

    protected int getTwoThirds(int start, int end) {
        return (int) Math.round((end - start) * 0.666667);
    }

    protected double calculateValue(double[] data, int location) {
        return 0;
    }
}
