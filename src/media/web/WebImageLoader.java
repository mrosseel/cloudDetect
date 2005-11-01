package media.web;


import java.awt.Button;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.net.URLConnection;

import sun.awt.image.InputStreamImageSource;

/**
 * Class used to load images from the web.
 * 
 * @author mikers
 *  
 */
public class WebImageLoader {
    /**
     * Loads an image from a specified URL.
     * 
     * Note: make sure the ProxyConfigurator is run for proxied connections !
     * Note: this method blocks until the image is retrieved.
     * 
     * @param txtUrl	the url, e.g. http://acme.com/image.jpg
     * @return	the retrieved image
     */
    public static Image loadURLImage(String txtUrl) {
        Image image = null;
        try {
            URL url = new URL(txtUrl);
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

    public static void main(String[] args) {
        //ProxyConfigurator.configure("proxy", "8080", "rls03\\Mikers",
        //        "Aqwpl;67");
        ProxyConfigurator.configure("proxy.pandora.be", "8080", "", "");
        ImageViewer.showImage(WebImageLoader.loadURLImage(args[0]),
                "Loaded test image");
    }
}

