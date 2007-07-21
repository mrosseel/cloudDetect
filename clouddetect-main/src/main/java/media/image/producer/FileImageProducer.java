package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import media.image.CloudImageImpl;
import media.image.producer.plugin.ImageDatePlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ImageToolkit;

/**
 * Class FileImageProvider
 * 
 */
public class FileImageProducer extends ImageProducerImpl {
    private static Log log = LogFactory.getLog(FileImageProducer.class);

    private String imageToLoad;

    public FileImageProducer(String name) {
        super(name);
    }

    public CloudImage produceContent() {
        Image image = getImage();
        if (image == null || image.getHeight(null) == -1) {
            log.warn("Could not load image " + getSource());
            throw new IllegalArgumentException("Could not load image "
                    + getSource());
        }
        CloudImage result = new CloudImageImpl(image);
        result.setOriginComment(getProducerName());
        if(getPlugin()!=null) {
        	getPlugin().insertDateInMetaData(result, getSource());
        }
        return result;
    }

    protected Image getImage() {
        return ImageToolkit.loadImage(getSource());
    }

    public String getSource() {
        return imageToLoad;
    }

    public void setSource(String imageToLoad) {
        this.imageToLoad = imageToLoad;
    }

	public void setSourceId(int sourceId) {
		// TODO Auto-generated method stub
		
	}

}
