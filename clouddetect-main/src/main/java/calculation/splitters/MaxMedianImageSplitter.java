/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 1:18:09 AM
 * To change this template use Options | File Templates.
 */
package calculation.splitters;

import util.Median;

import java.util.Arrays;

import calculation.splitters.splittermetric.SplitterMetric;

/**
 * 
 * 
 * 
 * @author mike
 */
public class MaxMedianImageSplitter extends AbstractGenericImageSplitter {

    protected double calculateValue(double[] data, int from, int to) {
        return Median.find(data, from, to);
    }

    protected void postDataManipulation(double[] data) {}

    protected void preDataManipulation(double[] data) {
        // sort it, otherwise it won't work
        Arrays.sort(data);
    }

    public void setSplitterMetric(SplitterMetric metric) {
    // TODO Auto-generated method stub

    }

    public SplitterMetric getSplitterMetric() {
        // TODO Auto-generated method stub
        return null;
    }
}
