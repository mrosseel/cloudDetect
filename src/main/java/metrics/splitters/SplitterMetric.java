/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:23:07 AM
 * To change this template use Options | File Templates.
 */
package metrics.splitters;

import media.image.CloudImage;
import metrics.Metric;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.TextProgressBar;

public abstract class SplitterMetric implements Metric {
    public static int START_AND_END_AVOIDANCE = 2;
	private static Log log = LogFactory.getLog(SplitterMetric.class);
    private int bestSplitterLocation;
    private int pctSplitterLocation;
    private double result;
	
	public double compute(CloudImage image) {
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
		TextProgressBar progress =
			new TextProgressBar(
				40,
				(double) splitterBegin,
				(double) splitterEnd);

		for (splitterLocation = splitterBegin;
			splitterLocation != splitterEnd;
			splitterLocation++) {
			valueLeft = calculateValue(data, 0, splitterLocation);
			valueRight = calculateValue(data, splitterLocation + 1, length - 1);
			valueDifference = Math.abs(valueRight - valueLeft);
//			valueDifference =
//				valueRight * splitterLocation
//					+ valueLeft * (length - splitterLocation - 1);

			

			if (valueDifference > biggestValueDifference) {
				biggestValueDifference = valueDifference;
				bestSplitterLocation = splitterLocation;
			}
            
            if(log.isDebugEnabled()) {
                log.debug("diff = " + valueDifference + " at " + splitterLocation +
                            " left = " + valueLeft + " right = " + valueRight + " bestSplitter = " + bestSplitterLocation);
                }
            
			progress.update(splitterLocation);
			if (splitterLocation % 1000 == 0) {
				progress.print();
			}
		}

		setResult(biggestValueDifference);
        setBestSplitterLocation(bestSplitterLocation);
		setPctSplitterLocation(
			(int) Math.round(
				((double) bestSplitterLocation / (double) length * 100)));
		postDataManipulation(data);
		System.out.println(
			"Best splitter located at pos "
				+ bestSplitterLocation
				+ " ("
				+ 
				+ ((double) bestSplitterLocation / (double) length * 100)
				+ " %)");

		return result;
	}

	protected double getResult() {
		return result;
	}

	protected void setResult(double result) {
		this.result = result;
	}

	protected abstract void preDataManipulation(double[] data);
	protected abstract void postDataManipulation(double[] data);

	/**
	 * calculates a value
	 */
	protected abstract double calculateValue(double[] data, int from, int to);

	public int getBestSplitterLocation() {
		return bestSplitterLocation;
	}

	/**
	 * @param i
	 */
	public void setBestSplitterLocation(int i) {
		bestSplitterLocation = i;
	}

	/**
	 * @return
	 */
	public int getPctSplitterLocation() {
		return pctSplitterLocation;
	}

	/**
	 * @param i
	 */
	public void setPctSplitterLocation(int i) {
		pctSplitterLocation = i;
	}

}
