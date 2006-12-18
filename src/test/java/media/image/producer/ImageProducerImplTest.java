package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import junit.framework.TestCase;

public class ImageProducerImplTest extends TestCase {
	
	public void testProduction() {
		CloudImageQueue queue = new CloudImageQueue();
		ImageProducerImpl impl = new TestImageProducerImpl("Test image producer");
		impl.setQueue(new CloudImageQueue());
		impl.start();
		assertEquals(0, queue.size());
		
		queue = new CloudImageQueue();
		impl = new TestImageProducerImpl("Test image producer");
		impl.setPolling(0.01);
		impl.setQueue(queue);
		impl.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			fail();
		}
		impl.stopProducer();
		assertTrue("Queue should not be empty", 0 < queue.size());
		
		
	}

	class TestImageProducerImpl extends ImageProducerImpl {
	    private double[] data = {0,1,2,3,4,5,6,7,8}; 
		public TestImageProducerImpl(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		protected CloudImage produceContent() {
			return new CloudImage(data, 2, 4);
		}
		
	}
}
