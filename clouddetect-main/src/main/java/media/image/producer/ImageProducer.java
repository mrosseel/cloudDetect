package media.image.producer;

import media.image.CloudImage;

/**
 * Interface ImageProvider
 * 
 */
public interface ImageProducer {

    public CloudImage produceContent();

    public String getProducerName();
    
    public void setSource(String imageLocation);
    
    public String getSource();
    
    public void setSourceId(int sourceId);
}    
