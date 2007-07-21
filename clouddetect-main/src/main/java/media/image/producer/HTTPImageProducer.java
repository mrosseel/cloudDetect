package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import media.image.CloudImageImpl;
import media.web.WebImageLoader;

/**
 * Class HTTPImageProvider
 * 
 */
public class HTTPImageProducer extends ImageProducerImpl {
    private String source;
    private int sourceId;

    public HTTPImageProducer(String name) {
        super(name);
    }

    public CloudImage produceContent() {
        Image image = WebImageLoader.loadURLImage(getSource());
        CloudImage result = new CloudImageImpl(image);
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
