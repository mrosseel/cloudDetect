/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:30:11 AM
 * To change this template use Options | File Templates.
 */
package metrics;

import util.StandardDeviation;
import util.Median;

import java.util.Arrays;

public class MaxStdDevMetric extends SplitterMetric {
    protected double calculateValue(short[] data, int from, int to) {
        return StandardDeviation.sdFast(data, from, to);
    }

    protected void preDataManipulation(short[] data) {
    }

    protected void postDataManipulation(short[] data) {
        int splitterLocation = getBestSplitterLocation();
        int length = data.length-1;
        Arrays.sort(data, 0, splitterLocation);
        Arrays.sort(data, splitterLocation+1, length);
        int leftMedian = Median.find(data, 0, splitterLocation);
        int rightMedian = Median.find(data, splitterLocation+1, length);
        System.out.println("left = " + leftMedian + " right = " + rightMedian);
        setResult(Math.abs(leftMedian - rightMedian));
    }
}
