/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 1:18:09 AM
 * To change this template use Options | File Templates.
 */
package metrics;

import util.Median;

import java.util.Arrays;

public class MaxMedianMetric extends SplitterMetric {

    protected double calculateValue(short[] data, int from, int to) {
        return Median.find(data, from, to);
    }

    protected void postDataManipulation(short[] data) {
    }

    protected void preDataManipulation(short[] data) {
        // sort it, otherwise it won't work
        Arrays.sort(data);
    }
}
