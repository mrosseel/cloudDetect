package calculation.splitters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.splitters.splittermetric.HorizonMetric;
import calculation.splitters.splittermetric.SplitterMetric;

import media.image.CloudImage;

public class BisectionSplitter implements ImageSplitter {
    private static Log log = LogFactory.getLog(BisectionSplitter.class);
    private double bestResult;
    private int bestSplitterLocation;
    private SplitterMetric metric;
    
    public void split(CloudImage data) {
        double[] monoData = data.getMonochromeData();
        bestResult= Double.MAX_VALUE;
        bestSplitterLocation = -1;
        bisection(monoData, 0, monoData.length-1);
    }

    protected void bisection(double[] data, int start, int end) {
        int location;
        int currentStart = start;
        int currentEnd = end;
        int locationLeft;
        int locationRight;
        int locationMid;
        double resultLeft;
        double resultRight;
        double currentResult;
        while(currentEnd - currentStart > 2) {
            locationLeft = currentStart+getOneThird(currentStart, currentEnd);
            locationRight = currentStart+getTwoThirds(currentStart, currentEnd);
            locationMid = currentStart+(int)Math.round((currentEnd-currentStart)/2.0);
            resultLeft = metric.compute(data, locationLeft);
            resultRight = metric.compute(data, locationRight);
            if(log.isDebugEnabled()) {
                log.debug("currentstart = " + currentStart + " currentEnd = " + currentEnd);
                log.debug("locationLeft = " + locationLeft + " locationRight = " + locationRight);
                log.debug("resultLeft = " + resultLeft + " resultRight = " + resultRight);
            }
            if(resultLeft < resultRight) {
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
        if(isFirstBetterResult(currentResult, bestResult)) {
            bestResult = currentResult;
            bestSplitterLocation = currentLocation;
        }
    }
    
    protected int getOneThird(int start, int end) {
        return (int)Math.round((end-start)/3.0);
    }
    
    protected int getTwoThirds(int start, int end) {
        return (int)Math.round((end-start)*0.666667);
    }
    
    protected double calculateValue(double[] data, int location) {
        return 0;
    }
    
    protected boolean isFirstBetterResult(double first, double second) {
        return first < second;
    }

    public double getResult() {
        return bestResult;
    }

    public void setResult(double result) {
        this.bestResult = result;
    }
    
    public int getBestSplitterLocation() {
        return bestSplitterLocation;
    }

    public void setBestSplitterLocation(int i) {
        bestSplitterLocation = i;
    }

    public SplitterMetric getMetric() {
        return metric;
    }

    public void setMetric(SplitterMetric metric) {
        this.metric = metric;
    }
}
