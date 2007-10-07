/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:30:11 AM
 * To change this template use Options | File Templates.
 */
package calculation.splitters;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.splitters.splittermetric.SplitterMetric;

import util.Median;
import util.StandardDeviation;

public class MaxStdDevImageSplitter extends AbstractGenericImageSplitter {
	private static Log log = LogFactory.getLog(MaxStdDevImageSplitter.class);
	
	protected double calculateValue(double[] data, int from, int to) {
		return StandardDeviation.sdFast(data, from, to)/(to-from+1);
	}

	protected void preDataManipulation(double[] data) {
	}

	protected void postDataManipulation(double[] data) {
		int splitterLocation = getBestSplitterLocation();
		
		int length = data.length - 1;
		double leftMedian = Median.find(data, 0, splitterLocation);
		double rightMedian = Median.find(data, splitterLocation + 1, length);
		log.info("left = " + leftMedian + " right = " + rightMedian);
		setResult(Math.abs(leftMedian - rightMedian));
	}

    public void setSplitterMetric(SplitterMetric metric) {
        // TODO Auto-generated method stub
        
    }

    public SplitterMetric getSplitterMetric() {
        // TODO Auto-generated method stub
        return null;
    }
}
