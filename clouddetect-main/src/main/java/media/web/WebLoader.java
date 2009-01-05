package media.web;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ImageToolkit;

/**
 * Class used to load images from the web.
 * 
 * @author mikers
 * 
 */
public class WebLoader {
	
	private static Log log = LogFactory.getLog(WebLoader.class);
	
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
			image = ImageToolkit.loadImage(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return image;
	}

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
	public static String loadURLFile(String txtUrl) {
		File file = null;
		String line;
		StringBuffer fBuf = new StringBuffer();;
		try {
            // Create an URL instance
            URL url = new URL(txtUrl);

            InputStream in=url.openStream ();
            BufferedReader dis =
              new BufferedReader (new InputStreamReader (in));
            
            while ( (line = dis.readLine ()) != null) {
              fBuf.append (line + "\n");
            }

            in.close ();
		}
			 catch (MalformedURLException mue)
		        {
		            log.error("Invalid URL: " + txtUrl);
		        }
		        catch (IOException ioe)
		        {
		            log.error("I/O Error - " + ioe);
		        }

		return fBuf.toString();
	}

	public static void main(String[] args) {
		// ProxyConfigurator.configure("proxy", "8080", "rls03\\Mikers",
		// "Aqwpl;67");
		ProxyConfigurator.configure("proxy.pandora.be", "8080", "", "");
		ImageViewer.showImage(WebLoader.loadURLImage(args[0]),
				"Loaded test image");
	}
}
