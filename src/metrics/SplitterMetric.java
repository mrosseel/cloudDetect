/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: May 2, 2003
 * Time: 2:23:07 AM
 * To change this template use Options | File Templates.
 */
package metrics;

import util.TextProgressBar;

public abstract class SplitterMetric implements Metric {
	private int bestSplitterLocation;
	private int pctSplitterLocation;
	private double result;

	/**
		 * TODO : IMPLEMENT USING DOUBLES
		 */
	public double compute(double[] data) {
		return -1;
	}

	public double compute(short[] data) {
		preDataManipulation(data);

		// choose a begin and end-point for the splitter
		int length = data.length;
		int splitterBegin = 5 - 1;
		int splitterEnd = length - 5;
		int bestSplitterLocation = -1;
		int splitterLocation;
		;
		double biggestValueDifference = Integer.MAX_VALUE;
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
			//			valueDifference = Math.abs(valueRight - valueLeft);
			valueDifference =
				valueRight * splitterLocation
					+ valueLeft * (length - splitterLocation - 1);

			//			System.out.println("diff = " + valueDifference + " at " + splitterLocation +
			//			" left = " + valueLeft + " right = " + valueRight);

			//if (valueDifference > biggestValueDifference) {
			if (valueDifference < biggestValueDifference) {
				biggestValueDifference = valueDifference;
				bestSplitterLocation = splitterLocation;
			}
			progress.update(splitterLocation);
			if (splitterLocation % 1000 == 0) {
				//System.out.print(getPctSplitterLocation() + " % - ");
				progress.print();
			}
		}

		setResult(biggestValueDifference);
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

	protected abstract void preDataManipulation(short[] data);
	protected abstract void postDataManipulation(short[] data);

	/**
	 * calculates a value
	 */
	protected abstract double calculateValue(short[] data, int from, int to);

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
