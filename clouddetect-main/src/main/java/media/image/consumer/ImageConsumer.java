package media.image.consumer;

import media.image.CloudImage;

/**
 * Interface ImageConsumer
 * 
 */
public interface ImageConsumer {

    public void consume(CloudImage image);
    
    /**
     * 
     * @param subConsumer
     * @return void
     */
    public void addSubConsumer(ImageSubConsumer subConsumer);
}
