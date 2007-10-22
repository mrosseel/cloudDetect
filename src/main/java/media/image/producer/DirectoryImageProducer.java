package media.image.producer;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import media.image.CloudImage;
import media.image.CloudImageImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ImageToolkit;

/**
 * Class FileImageProvider
 * 
 */
public class DirectoryImageProducer extends ImageProducerImpl {
    private static Log log = LogFactory.getLog(DirectoryImageProducer.class);

    private String dirToLoad;

    private String imgExtension;

    private File[] files;

    private int fileCounter = 0;

    private boolean isLooping = false;

    private FileImageProducer fileImageProducer;

    public DirectoryImageProducer(String name, String dirToLoad) {
        super(name);
        this.dirToLoad = dirToLoad;
        loadImagesInDir();
    }

    public CloudImage produceContent() {
        if (!isLooping && fileCounter >= files.length - 1) {
            return null;
        }

        fileImageProducer.setImageToLoad(getNextImageName());

        CloudImage result = fileImageProducer.produceContent();
        result.setOriginComment(getProducerName());
        fileCounter++;
        return result;
    }

    protected void loadImagesInDir() {
        File dir = new File(getDirectoryToLoad());

        // This filter only returns directories
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        };
        files = dir.listFiles(fileFilter);
        Arrays.sort(files);

    }

    protected String getDirectoryToLoad() {
        return dirToLoad;
    }

    protected void setDirectoryToLoad(String dirToLoad) {
        this.dirToLoad = dirToLoad;
    }

    protected String getNextImageName() {
        return (files[fileCounter % (files.length - 1)]).getAbsolutePath();
    }

    public String getImgExtension() {
        return imgExtension;
    }

    public void setImgExtension(String imgExtension) {
        this.imgExtension = imgExtension;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public void setLooping(boolean isLooping) {
        this.isLooping = isLooping;
    }

    public FileImageProducer getFileImageProducer() {
        return fileImageProducer;
    }

    public void setFileImageProducer(FileImageProducer fileImageProducer) {
        this.fileImageProducer = fileImageProducer;
    }

}
