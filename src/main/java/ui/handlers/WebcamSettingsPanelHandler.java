/*
 * Created on 23-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.handlers;

import org.werx.framework.bus.ReflectionBus;

import ui.WebcamSettingsPanel;
import ui.signal.ImageSignal;

/**
 * @author Mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WebcamSettingsPanelHandler {
    private WebcamSettingsPanel panel;

    /**
     * 
     */
    public WebcamSettingsPanelHandler(WebcamSettingsPanel panel) {
        this.panel = panel;
        ReflectionBus.plug(this);
    }

    public void channel(ImageSignal signal) {
        System.out.println("received webcamsettings signal");
        // panel.setImage(signal.getImage());
    }
}
