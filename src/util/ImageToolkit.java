package util;


import java.awt.Button;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.net.URL;
import java.net.URLConnection;

import sun.awt.image.InputStreamImageSource;

/**
 * Class used to load images from the web.
 * 
 * @author mikers
 *  
 */
public class ImageToolkit {
    
	/**
     * Full path is needed, otherwise a bad Image is returned.
     * @todo fix bad image return.
     * @param txtImage
     * @return
     */
    public static Image loadClasspathImage(String relativeName) {
    	return loadImage(ImageToolkit.class.getResource(relativeName));
    }
	
    /**
     * Full path is needed, otherwise a bad Image is returned.
     * @todo fix bad image return.
     * @param txtImage
     * @return
     */
    public static Image loadImage(String txtImage) {
        Image image = null;
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            image = toolkit.getImage(txtImage);
            // wait until the image is loaded before we return the image
            MediaTracker mediaTracker = new MediaTracker(new Button());
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            } catch (InterruptedException ie) {
                System.err.println(ie);
                System.exit(1);
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return image;
  }
    
    /**
     * Loads an image from a specified URL.
     * 
     * How to make an URL? 
     * 		URL url = new URL(txtUrl);
     * 
     * Note: make sure the ProxyConfigurator is run for proxied connections !
     * Note: this method blocks until the image is retrieved.
     * 
     * @param url	the url, e.g. http://acme.com/image.jpg
     * @return	the retrieved image
     */
    public static Image loadImage(URL url) {
        Image image = null;
        try {
            
            URLConnection uc = url.openConnection();
            uc.connect();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            image = (Image) toolkit.createImage((InputStreamImageSource) uc
                    .getContent());
            // wait until the image is loaded before we return the image
            MediaTracker mediaTracker = new MediaTracker(new Button());
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            } catch (InterruptedException ie) {
                System.err.println(ie);
                System.exit(1);
            }

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return image;
    }
    
    public static Image createImage(int[] pixels, int w, int h) {
        MemoryImageSource source = new MemoryImageSource(w, h, pixels, 0, w);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return (Image) toolkit.createImage(source);
    }


    public static Image createImage(int[] pixels, int w, int h, ColorModel colorModel) {
        MemoryImageSource source = new MemoryImageSource(w, h, colorModel, pixels, 0, w);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return (Image) toolkit.createImage(source);
    }
    
    /**
     * testing main.
     * 
     * @param args
     */
    public static void main(String[] args) {
//        ProxyConfigurator.configure("proxy", "8080", "rls03\\Mikers",
//                "Aqwpl;67");
//        ImageViewer.showImage(WebImageLoader.loadURLImage(args[0]),
//                "Loaded test image");
//        ImageViewer.showImage(ImageToolkit.loadImage("c:/temp/red.jpg"),
//              "Loaded test image");
    }
}

