package media.image.producer;

import media.image.CloudImage;

/**
 * Interface ImageProvider
 * 
 */
public interface ImageProducer {

    public CloudImage produceContent();

    public String getProducerName();
}
