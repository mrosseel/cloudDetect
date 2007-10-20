package media.image.producer;

import java.awt.Image;

import util.ImageToolkit;


/**
 * Class FileImageProvider
 * 
 */
public class FileClasspathImageProducer extends FileImageProducer {
       
    public FileClasspathImageProducer(String name) {
        super(name);
    }
    
	public FileClasspathImageProducer(String name, String imageToLoad) {
		this(name);
        setImageToLoad(imageToLoad);
	}

    protected Image getImage() {
        return ImageToolkit.loadClasspathImage(getImageToLoad());
    }
    
    
}

