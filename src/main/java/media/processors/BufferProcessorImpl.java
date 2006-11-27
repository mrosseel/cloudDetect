/**
 * One implementation of how to process a buffer 
 */
package media.processors;

import media.image.CloudImage;
import metrics.LineMedianDifferenceMetric;
import metrics.PixelBrightnessMetric;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;

import util.MathMethods;

public class BufferProcessorImpl implements BufferProcessor {
	private boolean isProcessing = false;

	private double[] data;

	private int width;

	private static BufferProcessorImpl instance;

	private static Log log = LogFactory.getLog(BufferProcessorImpl.class);

	private BufferProcessorImpl() {
	}

	public static BufferProcessorImpl createInstance() {
		if (instance == null) {
			instance = new BufferProcessorImpl();
		}
		return instance;
	}

	public void process(CloudImage image) {

		if (log.isDebugEnabled()) {
			log.debug("Processing data.");
		}
		
		double[] data = image.getData();

		double biggest = MathMethods.max(data);
		double smallest = MathMethods.min(data);

		log.info("length = " + data.length);
		log.info("biggest = " + biggest + " smallest = " + smallest);

		LineMedianDifferenceMetric metric = new LineMedianDifferenceMetric();
		// CutoffDifferenceMetric metric = new CutoffDifferenceMetric();
		metric.setWidth(this.width);
		// MaxStdDevMetric stdev = new MaxStdDevMetric();
		double result = metric.compute(image);
		PixelBrightnessMetric brightness = new PixelBrightnessMetric();
		log.info("brightness = " + brightness.compute(image));
		InstanceFactory.getContrastChart().addValue(Math.random()*100);

	}

	public double[] getData() {
		return data;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
