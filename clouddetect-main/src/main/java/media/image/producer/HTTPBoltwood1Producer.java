package media.image.producer;

import java.util.Date;

import media.image.CloudFileResultImpl;
import media.image.CloudResult;
import media.image.CloudResultMetaData;
import media.web.WebLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class HTTPImageProvider
 * 
 */
public class HTTPBoltwood1Producer implements Producer<CloudResult> {
    private String source;
    private int sourceId;
    private String name;
    private static Log log = LogFactory.getLog(HTTPBoltwood1Producer.class);
    
    public HTTPBoltwood1Producer(String name) {
        this.name = name;
    }

    public CloudResult produceContent() {
    	log.debug("Producing boltwood content from " + getSource());
        return produceContent(WebLoader.loadURLFile(getSource()));
    }
    
    CloudResult produceContent(String fileContent) {
        CloudFileResultImpl result = new CloudFileResultImpl();
        log.debug("Setting boltwood content with " + fileContent);
        result.getMetaData().setFeedId(sourceId);
        result.setFileData(fileContent);
        result.setOriginComment(getProducerName());
        
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

	@Override
	public String getProducerName() {
		return "boltwoodI";
	}
}
