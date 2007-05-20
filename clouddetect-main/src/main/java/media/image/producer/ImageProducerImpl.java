package media.image.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import media.image.CloudImage;
import media.image.producer.plugin.ImageDatePlugin;

/**
 * Class ImageProviderImpl
 * 
 */
public abstract class ImageProducerImpl implements ImageProducer {

    private String producerName;

    private long timeBetweenPollsInMilliSeconds = -1;

    private static long minPollingTimeMilliseconds = 10;

    private boolean hasStopped = false;

    // TODO make the plugin stuff more generic, maybe add support in this class and not in all
    // children, ... .
    private ImageDatePlugin plugin;

    private static Log log = LogFactory.getLog(ImageProducerImpl.class);

    // Constructor
    public ImageProducerImpl(String name) {
        this.producerName = name;
    }

    public abstract CloudImage produceContent();

    public String getProducerName() {
        return producerName;
    }
    
    public ImageDatePlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(ImageDatePlugin plugin) {
        this.plugin = plugin;
    }
}
