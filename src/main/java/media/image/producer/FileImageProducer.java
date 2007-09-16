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

    public CloudImage produceContent() {
        Image image = getImage();
        CloudImage result = new CloudImageImpl(image);
        result.setOrigin(getProducerName());
        return result;
    }

    protected Image getImage() {
        return ImageToolkit.loadImage(imageToLoad);
    }

    protected String getImageToLoad() {
        return imageToLoad;
    }

    protected void setImageToLoad(String imageToLoad) {
        this.imageToLoad = imageToLoad;
    }

}
