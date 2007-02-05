package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import media.image.CloudImageImpl;
import util.ImageToolkit;


/**
 * Class FileImageProvider
 * 
 */
public class FileImageProducer extends ImageProducerImpl {
    private String imageToLoad;
    
	public FileImageProducer(String name, String imageToLoad) {
		super(name);
	   this.imageToLoad = imageToLoad;
	}

	protected CloudImage produceContent() {
		Image image = ImageToolkit.loadImage(imageToLoad);
		CloudImage result = new CloudImageImpl(image);
        result.setOrigin(getProducerName());
		return result;
	}

}

