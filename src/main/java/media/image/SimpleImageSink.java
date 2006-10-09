/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;

import media.processors.BufferProcessor;
import media.processors.BufferProcessorImpl;

import ui.commands.ImageCommand;

/**
 * @author Mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SimpleImageSink implements ImageSink {

	/* (non-Javadoc)
	 * @see media.image.ImageSink#addImage(java.awt.Image)
	 */
	public void addImage(Image image) {
		BufferProcessor proc = BufferProcessorImpl.createInstance();
		//proc.process(image);
		ImageCommand command = new ImageCommand(image);
		command.execute();

	}

}
