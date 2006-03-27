/*
 * Created on 23-jan-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.controller;

import media.ImageContainer;
import media.processors.BufferProcessor;

/**
 * @author Mike
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ProcessController {

	public void setProcessor(BufferProcessor processor);
	
	public boolean hasProcessor();
	
	public void process(ImageContainer image);
	
}
