package media.image.producer;

import java.awt.Image;

import util.ImageToolkit;


/**
 * Class FileImageProvider
 * 
 */
public class FileClasspathImageProducer extends FileImageProducer {
        
	public FileClasspathImageProducer(String name, String imageToLoad) {
		super(name, imageToLoad);
	}

    protected Image getImage() {
        return ImageToolkit.loadClasspathImage(getImageToLoad());
    }
    
    
}

