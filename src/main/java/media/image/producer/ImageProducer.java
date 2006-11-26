package media.image.producer;

/**
 * Interface ImageProvider
 * 
 */
public interface ImageProducer {

	public void stopProducer();
	
	public void setQueue();
	
	public void setPolling(double timeBetweenPolls);
}

