package media.image.producer;

import media.image.CloudImage;

/**
 * Interface ImageProvider
 * 
 */
public interface ImageProducer extends Runnable {

	public void stopProducer();
	
	public void setQueue(CloudImageQueue queue);
	
	public void setPolling(double timeBetweenPolls);
    
    public CloudImage produceContent();
	
	public String getProducerName();
    
    public void start();
}

