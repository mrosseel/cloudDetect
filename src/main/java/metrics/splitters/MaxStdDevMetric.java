/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:30:11 AM
 * To change this template use Options | File Templates.
 */
package metrics.splitters;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.Median;
import util.StandardDeviation;

public class MaxStdDevMetric extends SplitterMetric {
	private static Log log = LogFactory.getLog(MaxStdDevMetric.class);
	
	protected double calculateValue(double[] data, int from, int to) {
		return StandardDeviation.sdFast(data, from, to);
	}

	protected void preDataManipulation(double[] data) {
	}

	protected void postDataManipulation(double[] data) {
		int splitterLocation = getBestSplitterLocation();
		
//		for (int i = 0; i != data.length; i++) {
//			System.out.println(data[i] + ",");
//		}
				
		int length = data.length - 1;
		double leftMedian = Median.find(data, 0, splitterLocation);
		double rightMedian = Median.find(data, splitterLocation + 1, length);
		log.info("left = " + leftMedian + " right = " + rightMedian);
		setResult(Math.abs(leftMedian - rightMedian));
	}
}
