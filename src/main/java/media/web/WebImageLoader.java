package media.web;

import java.awt.Image;
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
public class WebImageLoader {
    /**
     * Loads an image from a specified URL.
     * 
     * Note: make sure the ProxyConfigurator is run for proxied connections !
     * Note: this method blocks until the image is retrieved.
     * 
     * @param txtUrl
     *            the url, e.g. http://acme.com/image.jpg
     * @return the retrieved image
     */
    public static Image loadURLImage(String txtUrl) {
        URL url;
        Image image = null;
        try {
            url = new URL(txtUrl);
            image = ImageIO.read(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return image;
    }

    public static void main(String[] args) {
        // ProxyConfigurator.configure("proxy", "8080", "rls03\\Mikers",
        // "Aqwpl;67");
        ProxyConfigurator.configure("proxy.pandora.be", "8080", "", "");
        ImageViewer.showImage(WebImageLoader.loadURLImage(args[0]),
                "Loaded test image");
    }
}
