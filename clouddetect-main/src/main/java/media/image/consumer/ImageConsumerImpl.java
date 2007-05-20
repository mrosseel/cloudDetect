package media.image.consumer;

import java.util.ArrayList;

import media.image.CloudImage;
import media.image.producer.CloudImageQueue;
import media.image.producer.ImageProducerImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class ConsumerImpl
 * 
 */
public class ImageConsumerImpl implements ImageConsumer {

    private String producerName;

    ArrayList<ImageSubConsumer> consumers = new ArrayList<ImageSubConsumer>();

    private static Log log = LogFactory.getLog(ImageProducerImpl.class);

    public synchronized void addSubConsumer(ImageSubConsumer subConsumer) {
        consumers.add(subConsumer);
    }

    public void consume(CloudImage image) {
        try {
                // list can only grow, so assured to work.
                // TODO are dynamic lists needed? How to do it? Iterator?
                if (image != null) {
                    for (int i = 0; i < consumers.size(); i++) {
                        ((ImageSubConsumer) consumers.get(i)).consume(image);
                    }
                }
        } catch (Throwable e) {
            log.error("main Consumer loop has crashed: ", e);
        }
    }
}
