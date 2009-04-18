package media.image.producer;

import java.awt.Image;

import media.image.CloudImageResult;
import media.image.CloudImageResultImpl;
import media.web.WebLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class HTTPImageProvider
 * 
 */
public class HTTPImageProducer extends ImageProducerImpl {
    private String source;
    private int sourceId;
    private static Log log = LogFactory.getLog(HTTPImageProducer.class);

    public HTTPImageProducer(String name) {
        super(name);
    }

    public CloudImageResult produceContent() {
    	Image image = null;
    	try {
    		image = WebLoader.loadURLImage(getSource());
    	} catch(Throwable e) {
    		log.error("Error loading Image " + getSource() + " via http.");
    	}
        CloudImageResult result = new CloudImageResultImpl(image);
        result.setOriginComment(getProducerName());
        result.getMetaData().setFeedId(sourceId);
        getPlugin().insertDateInMetaData(result, null);
        return result;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String imageSource) {
        this.source = imageSource;
    }

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
}
