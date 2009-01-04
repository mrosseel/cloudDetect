package media.image.consumer;

import java.util.ArrayList;

import media.image.CloudImageResult;
import media.image.producer.ImageProducerImpl;
import media.image.producer.Producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class ConsumerImpl
 * 
 */
public class ImageConsumerImpl implements Consumer<CloudImageResult> {

	ArrayList<SubConsumer<CloudImageResult>> consumers = new ArrayList<SubConsumer<CloudImageResult>>();

	private static Log log = LogFactory.getLog(ImageProducerImpl.class);

	public void addSubConsumer(SubConsumer<CloudImageResult> subConsumer) {
		consumers.add(subConsumer);
	}

	@Override
	public void consume(Producer<?> producer) {
		try {
			// list can only grow, so assured to work.
			// TODO are dynamic lists needed? How to do it? Iterator?
			CloudImageResult image = (CloudImageResult) producer
					.produceContent();
			if (image != null) {
				for (int i = 0; i < consumers.size(); i++) {
					((SubConsumer<CloudImageResult>) consumers.get(i)).consume(image);
				}
			}
		} catch (Throwable e) {
			log.error("main Consumer loop has crashed: ", e);
		}
	}
}
