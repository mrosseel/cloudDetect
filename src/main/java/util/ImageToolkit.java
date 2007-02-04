package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Class used to load images from the web.
 * 
 * @author mikers
 * 
 */
public class ImageToolkit {
    private static ColorModel defaultModel;

    static {
        defaultModel = ColorModel.getRGBdefault();
    }

    /**
     * Full path is needed, otherwise a bad Image is returned.
     * 
     * @todo fix bad image return.
     * @param txtImage
     * @return
     */
    public static Image loadClasspathImage(String relativeName) {
        return loadImage(ImageToolkit.class.getResource(relativeName));
    }

    /**
     * Full path is needed, otherwise a bad Image is returned.
     * 
     * @todo fix bad image return.
     * @param fileName
     * @return
     */
    public static Image loadImage(String fileName) {
        File file;
        Image image = null;
        try {
            file = new File(fileName);
            image = ImageIO.read(file);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return image;
    }
    

    /**
     * Loads an image from a specified URL.
     * 
     * How to make an URL? URL url = new URL(txtUrl);
     * 
     * Note: make sure the ProxyConfigurator is run for proxied connections !
     * Note: this method blocks until the image is retrieved.
     * 
     * @param url
     *            the url, e.g. http://acme.com/image.jpg
     * @return the retrieved image
     */
    public static Image loadImage(URL url) {
        Image image = null;
        try {
            image = ImageIO.read(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return image;
    }

    public static Image createImage(int[] pixels, int w, int h) {
        MemoryImageSource source = new MemoryImageSource(w, h, pixels, 0, w);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return (Image) toolkit.createImage(source);
    }

    public static Image createImage(int[] pixels, int w, int h,
            ColorModel colorModel) {
        MemoryImageSource source = new MemoryImageSource(w, h, colorModel,
                pixels, 0, w);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return (Image) toolkit.createImage(source);
    }

    public static int getRed(int pixel) {
        return defaultModel.getRed(pixel);
    }

    public static int getGreen(int pixel) {
        return defaultModel.getGreen(pixel);
    }

    public static int getBlue(int pixel) {
        return defaultModel.getBlue(pixel);
    }

    public static double getMonochrome(int pixel) {
        return getRed(pixel) * 0.3 + getGreen(pixel) * 0.59 + getBlue(pixel)
                * 0.11;
    }

    /**
     * testing main.
     * 
     * @param args
     */
    public static void main(String[] args) {
    // ProxyConfigurator.configure("proxy", "8080", "rls03\\Mikers",
    // "Aqwpl;67");
    // ImageViewer.showImage(WebImageLoader.loadURLImage(args[0]),
    // "Loaded test image");
    // ImageViewer.showImage(ImageToolkit.loadImage("c:/temp/red.jpg"),
    // "Loaded test image");
    }
}
