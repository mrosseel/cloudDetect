package media.image.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import media.image.CloudImage;

/**
 * Class ImageProviderImpl
 * 
 */
public abstract class ImageProducerImpl extends Thread implements ImageProducer  {

	private String producerName;

	private long timeBetweenPollsInMilliSeconds = -1;

	private boolean hasStopped = false;

	private CloudImageQueue queue;
	
	private static Log log = LogFactory.getLog(ImageProducerImpl.class);

	// Constructor
	public ImageProducerImpl(String name) {
		this.producerName = name;
	}

	public void run() {
		if(timeBetweenPollsInMilliSeconds == -1 || queue == null) {
			log.error("Producer '" + producerName + "' not initialized properly: queue = " 
					+queue+", timeBetweenPollsInMilliSeconds = "+timeBetweenPollsInMilliSeconds);
			return;
		}
		while(!hasStopped) {
			queue.put(produceContent());
			try { sleep(timeBetweenPollsInMilliSeconds); } catch
			 (InterruptedException e) { }
		}
	}

	protected abstract CloudImage produceContent();
	
	public void stopProducer() {
		hasStopped = true;
	}

	public void setQueue(CloudImageQueue queue) {
		this.queue = queue;

	}

	public void setPolling(double timeBetweenPollsInSeconds) {
		this.timeBetweenPollsInMilliSeconds = Math.round(timeBetweenPollsInSeconds*1000);
	}

    public String getProducerName() {
        return producerName;
    }
}
