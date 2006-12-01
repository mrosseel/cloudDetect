package media.image.consumer;

import media.image.CloudImage;
import media.processors.BufferProcessor;
import application.InstanceFactory;


/**
 * Class ImageScoringSubConsumer
 * 
 */
public class ImageScoringSubConsumer implements ImageSubConsumer {

	public void consume(CloudImage image) {
		// TODO inject it or something
		BufferProcessor proc = InstanceFactory.getBufferProcessor();
		proc.process(image);
	}
}

