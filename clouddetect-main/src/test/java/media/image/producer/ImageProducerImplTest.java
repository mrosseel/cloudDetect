package media.image.producer;

import junit.framework.TestCase;
import media.image.CloudImage;
import media.image.CloudImageImpl;

public class ImageProducerImplTest extends TestCase {

    public void testProduction() {
        CloudImageQueue queue = new CloudImageQueue();
        ImageProducerImpl impl = new TestImageProducerImpl(
                "Test image producer");
        assertEquals(0, queue.size());

        queue = new CloudImageQueue();
        impl = new TestImageProducerImpl("Test image producer");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            fail();
        }
        assertTrue("Queue should not be empty", 0 < queue.size());

    }

    class TestImageProducerImpl extends ImageProducerImpl {
        private double[] data = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

        public TestImageProducerImpl(String name) {
            super(name);
            // TODO Auto-generated constructor stub
        }

        public CloudImage produceContent() {
            return new CloudImageImpl(data, 2, 4);
        }

    }
}
