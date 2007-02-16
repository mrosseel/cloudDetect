package media.image.consumer;

import java.util.ArrayList;

import media.image.CloudImage;
import media.image.producer.CloudImageQueue;
import media.image.producer.ImageProducerImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class ConsumerImpl
 * 
 */
public class ImageConsumerImpl extends Thread implements ImageConsumer {

    private String producerName;

    private long timeBetweenPollsInMilliSeconds = -1;

    private static long minPollingTimeMilliseconds = 10;

    private boolean hasStopped = false;

    private CloudImageQueue queue;

    ArrayList<ImageSubConsumer> consumers = new ArrayList<ImageSubConsumer>();

    /** keep track of start and total time */
    private long startTime;

    private long totalTime;

    private static Log log = LogFactory.getLog(ImageProducerImpl.class);

    public synchronized void addSubConsumer(ImageSubConsumer subConsumer) {
        consumers.add(subConsumer);
    }

    public void run() {
        if (timeBetweenPollsInMilliSeconds == -1 || queue == null
                || consumers.size() == 0) {
            log
                    .error("Consumer '" + producerName
                            + "'not initialized properly");
            return;
        }

        try {
            while (!hasStopped) {
                CloudImage image = queue.get();

                startTime = System.currentTimeMillis();

                // list can only grow, so assured to work.
                // TODO are dynamic lists needed? How to do it? Iterator?
                if (image != null) {
                    for (int i = 0; i < consumers.size(); i++) {
                        ((ImageSubConsumer) consumers.get(i)).consume(image);
                    }
                }
                totalTime = System.currentTimeMillis() - startTime;
                if (totalTime < timeBetweenPollsInMilliSeconds) {
                    try {
                        sleep(timeBetweenPollsInMilliSeconds - totalTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Throwable e) {
            log.error("main Consumer loop has crashed: ", e);
        }
    }

    public void stopProducer() {
        hasStopped = true;
    }

    public void setQueue(CloudImageQueue queue) {
        this.queue = queue;
    }

    public void setPolling(double timeBetweenPollsInSeconds) {
        this.timeBetweenPollsInMilliSeconds = Math.max(
                minPollingTimeMilliseconds, Math
                        .round(timeBetweenPollsInSeconds * 1000));
    }
}
