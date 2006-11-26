package media.image.producer;

import java.awt.Image;

import media.image.CloudImage;
import util.ImageToolkit;


/**
 * Class FileImageProvider
 * 
 */
public class FileImageProducer extends ImageProducerImpl {

	public FileImageProducer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	protected CloudImage produceContent() {
		Image image = ImageToolkit.loadImage("/home/mike/quasar_blue.png");
		CloudImage result = new CloudImage(image);
		//result.setImage(image);
		return result;
	}

}

