/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 1:18:09 AM
 * To change this template use Options | File Templates.
 */
package calculation.splitters;

import calculation.splitters.splittermetric.SplitterMetric;


/**
 * 
 * 
 * 
 * @author mike
 */
public class MaxAvgImageSplitter extends AbstractGenericImageSplitter {

    protected double calculateValue(double[] data, int from, int to) {
        double result = 0;
        for (int i = from; i < to; i++) {
            result+=data[i];
        }
        return result/(to-from+1);
    }

    protected void postDataManipulation(double[] data) {
    }

    protected void preDataManipulation(double[] data) {
        // sort it, otherwise it won't work
        //Arrays.sort(data);
    }

    public void setSplitterMetric(SplitterMetric metric) {
        // TODO Auto-generated method stub
        
    }

    public SplitterMetric getSplitterMetric() {
        // TODO Auto-generated method stub
        return null;
    }
}
