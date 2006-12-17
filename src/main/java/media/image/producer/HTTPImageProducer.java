package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import media.web.WebImageLoader;


/**
 * Class HTTPImageProvider
 * 
 */
public class HTTPImageProducer extends ImageProducerImpl {
    private String url;
    
	public HTTPImageProducer(String name) {
		super(name);
	}

	protected CloudImage produceContent() {
        Image image = WebImageLoader.loadURLImage(getUrl());
        CloudImage result = new CloudImage(image);
        result.setOrigin(getProducerName());
		return result;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
