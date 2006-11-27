package media.image.consumer;

import media.image.CloudImage;
import media.processors.BufferProcessor;
import media.processors.BufferProcessorImpl;


/**
 * Class ImageScoringSubConsumer
 * 
 */
public class ImageScoringSubConsumer implements ImageSubConsumer {

	public void consume(CloudImage image) {
		// TODO inject it or something
		BufferProcessor proc = BufferProcessorImpl.createInstance();
		proc.process(image);
	}
}

