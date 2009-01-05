package media.image.consumer;

import java.util.ArrayList;

import media.image.CloudFileResult;
import media.image.CloudImageResult;
import media.image.CloudResult;
import media.image.producer.ImageProducerImpl;
import media.image.producer.Producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class ConsumerImpl
 * 
 */
public class FileConsumerImpl implements Consumer<CloudFileResult> {

	ArrayList<SubConsumer<CloudFileResult>> subConsumers = new ArrayList<SubConsumer<CloudFileResult>>();

	private static Log log = LogFactory.getLog(ImageProducerImpl.class);


	@Override
	public void addSubConsumer(SubConsumer<CloudFileResult> subConsumer) {
		subConsumers.add(subConsumer);
	}

	@Override
	public void consume(Producer<?> producer) {
		try {
			// list can only grow, so assured to work.
			// TODO are dynamic lists needed? How to do it? Iterator?
			CloudFileResult file = (CloudFileResult) producer.produceContent();
			if (file != null) {
				for (int i = 0; i < subConsumers.size(); i++) {
					((SubConsumer<CloudFileResult>) subConsumers.get(i)).consume(file);
				}
			}
		} catch (Throwable e) {
			log.error("main Consumer loop has crashed: ", e);
		}
	}

}
