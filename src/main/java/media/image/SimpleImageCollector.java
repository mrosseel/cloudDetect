/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;

/**
 * @author Mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SimpleImageCollector implements ImageCollector {

	private ImageSink sink;
	/* (non-Javadoc)
	 * @see media.image.ImageCollector#addImage(java.awt.Image)
	 */
	public void addImage(Image image) {
		// TODO Auto-generated method stub
		this.sink.addImage(image);
		
	}

	/* (non-Javadoc)
	 * @see media.image.ImageCollector#setImageSink(media.image.ImageSink)
	 */
	public void setImageSink(ImageSink imageSink) {
		this.sink = imageSink;
	}
	

}
