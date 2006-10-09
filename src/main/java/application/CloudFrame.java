package application;

import java.awt.Frame;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Processor;
import javax.media.protocol.DataSource;

import jmapps.jmstudio.CaptureControlsDialog;

import org.werx.framework.bus.ReflectionBus;

import ui.CloudWatchFrame;
import ui.ContrastChart;

/**
* I tried to refactor this, it failed, is rubish...
 */
public class CloudFrame extends Frame {
	public static ContrastChart chart;
	Processor p;
	Object waitSync = new Object();
	boolean stateTransitionOK = true;
	//public static ImageContainer imageContainer;

	private CaptureControlsDialog dlgCaptureControls = null;
	DataSource dataSource;

	/**
	 * Given a media locator, create a processor and use that processor
	 * as a player to playback the media.
	 *
	 * During the processor's Configured state, two "pass-thru" codecs,
	 * PreAccessCodec and application.PostAccessCodec, are set on the video track.
	 * These codecs are used to get access to individual video frames
	 * of the media.
	 *
	 * Much of the code is just standard code to present media in JMF.
	 */
	public boolean start() {

		//setVisible(true);
		CloudWatchFrame frame = new CloudWatchFrame();
		CloudFrame.chart = frame.getContrastChart();
		CloudFrame.chart.setVisible(true);
		frame.pack();
		frame.setVisible(true);

		return true;
	}


}
