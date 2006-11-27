/*
 * Created on 4-feb-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.commands;

import java.awt.Image;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.werx.framework.bus.ReflectionBus;
import org.werx.framework.commands.ICommand;

import ui.signal.ImageSignal;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImageCommand implements ICommand {
	private Image image;
	private ImageSignal signal;
	private static Log log = LogFactory.getLog(ImageCommand.class);

	/**
	 * 
	 */
	public ImageCommand(Image image) {
		this.image = image;
	}

	/* (non-Javadoc)
	 * @see org.werx.framework.commands.ICommand#execute()
	 */
	public void execute() {
		this.signal = new ImageSignal(image);
		log.info("sending new image signal");
		ReflectionBus.broadcast(signal);
	}

}
