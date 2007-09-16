/*
 * Created on 4-feb-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.signal;

import java.awt.Image;

import org.werx.framework.signals.BusSignal;

/**
 * @author Mike
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WebImageSettingsSignal extends BusSignal {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image image;

	/**
	 * 
	 */
	public WebImageSettingsSignal(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	

}
