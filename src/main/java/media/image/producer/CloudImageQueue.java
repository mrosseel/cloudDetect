package media.image.producer;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.Queue;

public class CloudImageQueue {
	private Queue queue = new Queue();
	private int maxElements = 500;
	private static Log log = LogFactory.getLog(CloudImageQueue.class);
	
	 public synchronized CloudImage get( ) {
	        // Don't do the wait if we can do the get
	        if ( queue.empty() ) {
	        		return null;
	        }
	        CloudImage image = null;
	        try {
				image = (CloudImage) queue.dequeue();
			} catch (Exception e) {
				log.warn("Problem getting something from the CloudImageQueue, even though it's not empty.", e);
			}
			return image;
	    }

	    public synchronized void put( CloudImage value ) {
	        // Don't do the wait if we can put.
	        if ( queue.size() > getMaxElements() ) {
	        	log.warn("Queue had to be cleared, max elements was reached: " + getMaxElements());
	          queue.clear();
	        }

	        if(log.isDebugEnabled()) {
	        	log.debug("Adding element, queue size = " + queue.size());
	        }
	        queue.enqueue(value);
	    }

	  
		public int getMaxElements() {
			return maxElements;
		}

		public void setMaxElements(int maxElements) {
			this.maxElements = maxElements;
		}
		
		public int size() {
			return queue.size();
		}
}
