package media.image.producer;

/**
 * Interface ImageProvider
 * 
 */
public interface ImageProducer extends Runnable {

	public void stopProducer();
	
	public void setQueue(CloudImageQueue queue);
	
	public void setPolling(double timeBetweenPolls);
	
	public String getProducerName();
}
