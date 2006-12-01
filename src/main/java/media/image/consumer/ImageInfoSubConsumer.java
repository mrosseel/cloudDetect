package media.image.consumer;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.MathMethods;

public class ImageInfoSubConsumer implements ImageSubConsumer {

	private static Log log = LogFactory.getLog(ImageInfoSubConsumer.class);
	
	public void consume(CloudImage image) {
		double[] data = image.getData();

		double biggest = MathMethods.max(data);
		double smallest = MathMethods.min(data);

		log.info("length = " + data.length);
		log.info("biggest = " + biggest + " smallest = " + smallest);
		
	}

}
