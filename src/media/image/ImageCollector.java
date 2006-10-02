/*
 * Created on 27-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;

/**
 * Collects Images from all sources
 * 
 * @author Mike
 *
 */
public interface ImageCollector {

	public void addImage(Image image);
	
	public void setImageSink(ImageSink processor);
	
}
