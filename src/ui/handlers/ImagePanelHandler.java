/*
 * Created on 4-feb-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.handlers;

import org.werx.framework.bus.ReflectionBus;

import ui.ImagePanel;
import ui.signal.ImageSignal;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ImagePanelHandler {

	private ImagePanel panel;

	/**
	 * 
	 */
	public ImagePanelHandler(ImagePanel panel) {
		this.panel = panel;
		ReflectionBus.plug(this);
	}

	public void channel(ImageSignal signal) {
		System.out.println("received image signal");
		panel.setImage(signal.getImage());
	}
}
