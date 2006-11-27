/*
 * Created on 22-dec-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package metrics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import media.image.CloudImage;
import metrics.splitters.SlidingWindowSplitter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.Median;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LineMedianDifferenceMetric implements Metric {
	private int width;

	private static Log log = LogFactory
			.getLog(LineMedianDifferenceMetric.class);

	/**
	 * 
	 */
	public LineMedianDifferenceMetric() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see metrics.Metric#compute(short[])
	 */
	public double compute(CloudImage image) {
		double[] data = image.getData();
		width = image.getWidth();
		int heigth = data.length / width;
		double[] lineData = new double[heigth];
		double median;
		for (int i = 0; i != heigth; i++) {
			median = Median.find(data, i * width, (i + 1) * width - 1);

			lineData[i] = median;
		}

		// writeData(lineData);

		double result;
		SlidingWindowSplitter splitter = new SlidingWindowSplitter();
		int splitterLocation = splitter.split(lineData, 20);
		int length = lineData.length - 1;
		double leftMedian = Median.find(lineData, 0, splitterLocation);
		double rightMedian = Median
				.find(lineData, splitterLocation + 1, length);
		result = Math.abs(leftMedian - rightMedian);
		if (log.isDebugEnabled()) {
			log.debug("left = " + leftMedian + " right = " + rightMedian);
			log.debug("splitter = " + splitterLocation);
			log.debug("Contrast = " + result);
		}

		return result;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	// for debugging
	private void writeData(short[] data) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("linedata.txt"));

			for (int counter = 0; counter != data.length; counter++) {
				writer.write(String.valueOf(data[counter]));
				writer.write('\n');
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
