/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:23:07 AM
 * To change this template use Options | File Templates.
 */
package metrics;

import util.TextProgressBar;

import java.util.Arrays;

public abstract class SplitterMetric implements Metric {
    private int bestSplitterLocation;
    private double result;


    public double compute(short[] data) {

        preDataManipulation(data);


        // choose a begin and end-point for the splitter
        int length = data.length;
        int splitterBegin = 5-1;
        int splitterEnd = length - 5;
        int splitterLocation = splitterBegin;
        int bestSplitterLocation=-1;
        double biggestValueDifference = Integer.MIN_VALUE;
        double valueDifference;
        double valueLeft;
        double valueRight;
        TextProgressBar progress = new TextProgressBar(40, (double)splitterBegin, (double)splitterEnd);


        for(splitterLocation = splitterBegin; splitterLocation != splitterEnd; splitterLocation++) {
            valueLeft = calculateValue(data, 0, splitterLocation);
            valueRight = calculateValue(data, splitterLocation+1, length-1);
            valueDifference = Math.abs(valueRight - valueLeft);

            if(valueDifference > biggestValueDifference) {
                biggestValueDifference = valueDifference;
                bestSplitterLocation = splitterLocation;
            }
            progress.update(splitterLocation);
            if(splitterLocation%1000 == 0) {
                System.out.print(Math.round(((double)splitterLocation / (double)length * 100)) + " % - ");
//                progress.print();
            }

        }

        setResult(biggestValueDifference);
        postDataManipulation(data);
        System.out.println("Best splitter located at " + ((double)bestSplitterLocation / (double)length * 100) + " %");

        return result;
    }

    public int getBestSplitterLocation() {
        return bestSplitterLocation;
    }

    protected double getResult() {
        return result;
    }

    protected void setResult(double result) {
        this.result = result;
    }

    protected abstract void preDataManipulation(short[] data);
    protected abstract void postDataManipulation(short[] data);

    /**
     * calculates a value
     */
    protected abstract double calculateValue(short[] data, int from, int to);


}
