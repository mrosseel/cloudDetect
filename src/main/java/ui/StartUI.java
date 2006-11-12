package ui;

import java.awt.Frame;

import org.werx.framework.bus.ReflectionBus;


/**
* Just starts the reflectionbus and sets the main frame visible.
* Didn't want to clutter the main frame with this code.
 */
public class StartUI extends Frame {
	
	public boolean start() {
		ReflectionBus.start();
		CloudWatchFrame frame = new CloudWatchFrame();
		frame.pack();
		frame.setVisible(true);

		return true;
	}


}
