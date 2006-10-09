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
public class ProcessControllerImpl implements ProcessController {

	BufferProcessor processor = null;

	public boolean hasProcessor() {
		return processor != null;
	}
	public void setProcessor(BufferProcessor processor) {
		this.processor = processor;
	}
	
	public void process(ImageContainer image) {
		// TODO Auto-generated method stub

	}
}
