/**
 * One implementation of how to process a buffer 
 */
package media.processors;

import java.awt.Image;

import metrics.LineMedianDifferenceMetric;
import metrics.PixelBrightnessMetric;

public class BufferProcessorImpl implements BufferProcessor {
	private boolean isProcessing = false;
	private double[] data;
	private int width;
	private static BufferProcessorImpl instance;

	private BufferProcessorImpl() {
	}

	public static BufferProcessorImpl createInstance() {
		if (instance == null) {
			instance = new BufferProcessorImpl();
		}
		return instance;
	}

	public void process(double[] data) {

		System.out.println(
			"-------------------------------------------------------");

		// calculate extremes
		double smallest = Double.MAX_VALUE;
		double biggest = Double.MIN_VALUE;
		for (int i = 0; i != data.length; i++) {
			if (data[i] > biggest) {
				biggest = data[i];
			}
			if (data[i] < smallest) {
				smallest = data[i];
			}
		}

		System.out.println("length = " + data.length);
		System.out.println("biggest = " + biggest + " smallest = " + smallest);

		LineMedianDifferenceMetric metric = new LineMedianDifferenceMetric();
		//CutoffDifferenceMetric metric = new CutoffDifferenceMetric();
		metric.setWidth(this.width);
		//		MaxStdDevMetric stdev = new MaxStdDevMetric();
		double result = metric.compute(data);
		PixelBrightnessMetric brightness = new PixelBrightnessMetric();
		System.out.println("brightness = " + brightness.compute(data));

		
	}

	public synchronized void run() {
		process(data);
		setProcessing(false);

	}

	public double[] getData() {
		return data;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public synchronized boolean isProcessing() {
		return isProcessing;
	}

	public synchronized void setProcessing(boolean processing) {
		isProcessing = processing;
	}

	/**
	 * @param img
	 * @param data
	 */
	public void setImageData(Image img, double[] data) {
		this.data = data;
	}
}
