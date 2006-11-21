/*
 * Created on 20-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image.producer.jmf;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Codec;
import javax.media.ConfigureCompleteEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.PrefetchCompleteEvent;
import javax.media.Processor;
import javax.media.RealizeCompleteEvent;
import javax.media.ResourceUnavailableEvent;
import javax.media.UnsupportedPlugInException;
import javax.media.control.FormatControl;
import javax.media.control.FrameRateControl;
import javax.media.control.TrackControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.CaptureDevice;
import javax.media.protocol.DataSource;

import jmapps.jmstudio.CaptureControlsDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Mike look at it !!! :
 *         http://swjscmail1.java.sun.com/cgi-bin/wa?A2=ind0109&L=jmf-interest&P=R2095
 */
public class JMFInit implements ControllerListener {

	Processor p;

	boolean stateTransitionOK = true;

	private CaptureControlsDialog dlgCaptureControls = null;

	DataSource dataSource;

	// thread sync object
	Object waitSync = new Object();

	private static Log log = LogFactory.getLog(JMFInit.class);

	public void setDataSource(MediaLocator ml) throws NoDataSourceException,
			IOException {
		dataSource = Manager.createDataSource(ml);
	}

	/**
	 * Given a media locator, create a processor and use that processor as a
	 * player to playback the media.
	 * 
	 * During the processor's Configured state, two "pass-thru" codecs,
	 * PreAccessCodec and application.PostAccessCodec, are set on the video
	 * track. These codecs are used to get access to individual video frames of
	 * the media.
	 * 
	 * Much of the code is just standard code to present media in JMF.
	 */
	public boolean open(MediaLocator ml) {

		try {
			p = Manager.createProcessor(dataSource);
		} catch (Exception e) {
			log.error("Failed to create a processor from the given url: " + e);
			return false;
		}

		// all controller events are sent to this, see controllerUpdate method.
		p.addControllerListener(this);

		// Put the Processor into configured state.
		p.configure();
		if (!waitForState(Processor.Configured)) {
			log.error("Failed to configure the processor.");
			return false;
		}

		// So I can use it as a player.
		p.setContentDescriptor(null);

		// Obtain the track controls.
		TrackControl tc[] = p.getTrackControls();

		if (tc == null) {
			log.error("Failed to obtain track controls from the processor.");
			return false;
		}

		// Search for the track control for the video track.
		TrackControl videoTrack = null;

		for (int i = 0; i < tc.length; i++) {
			if (tc[i].getFormat() instanceof VideoFormat) {
				videoTrack = tc[i];
			}
		}

		if (videoTrack == null) {
			log.error("The input media does not contain a video track.");
			return false;
		}

		log.info("Video format: " + videoTrack.getFormat());

		// Instantiate and set the frame access codec to the data flow path.
		try {
			Codec codec[] = { null, new PostAccessCodec() };
			videoTrack.setCodecChain(codec);
		} catch (UnsupportedPlugInException e) {
			log.error("The process does not support effects.");
		}

		// Realize the processor.
		p.prefetch();
		if (!waitForState(Processor.Prefetched)) {
			System.err.println("Failed to realize the processor.");
			return false;
		}

		setFrameRate(p, 5.0);

		p.start();

		return true;
	}

	/**
	 * @param processor
	 *  
	 */
	private void setFrameRate(Processor processor, double frameRate) {
		Object control = processor
				.getControl("javax.media.control.FrameRateControl");
		FrameRateControl frc = (javax.media.control.FrameRateControl) control;
		if (frc != null) {
			log.info("The frame rate is currently set to " + frc.getFrameRate()
					+ "fps. Setting to rate of " + frameRate + ", max rate is "
					+ frc.getMaxSupportedFrameRate() + "fps");

			frc.setFrameRate((float) Math.min(frameRate, frc
					.getMaxSupportedFrameRate()));
			log.info("Frame rate set at : " + frc.getFrameRate() + "fps");
		}
	}

	public VideoFormat getDefaultPreferredFormat() {
		String prefEncoding = null;
		float prefFPS = 5.0f;
		Dimension prefSize = new Dimension(160, 120);
		VideoFormat prefFormat = new VideoFormat(prefEncoding, prefSize,
				Format.NOT_SPECIFIED, //length
				null, // data type
				prefFPS);
		return prefFormat;
	}

	// TODO not used any more, framerate is controlled in another way
	public void setPreferredFormat(DataSource ds, CaptureDeviceInfo cdi) {
		// Preferred capture format parameters

		VideoFormat prefFormat = getDefaultPreferredFormat();

		if (ds instanceof CaptureDevice) {
			FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
			for (int i = 0; i < cdi.getFormats().length; i++) {
				VideoFormat vf = (VideoFormat) cdi.getFormats()[i];
				if (vf.matches(prefFormat)) {
					prefFormat = (VideoFormat) vf.intersects(prefFormat);
					fcs[0].setFormat(prefFormat);
					break;
				}
			}
		}
	}

	/**
	 * Block until the processor has transitioned to the given state. Return
	 * false if the transition failed.
	 */
	boolean waitForState(int state) {
		synchronized (waitSync) {
			try {
				while (p.getState() != state && stateTransitionOK)
					waitSync.wait();
			} catch (Exception e) {
			}
		}
		return stateTransitionOK;
	}

	/**
	 * Controller Listener (implements interface)
	 */
	public void controllerUpdate(ControllerEvent evt) {

		if (evt instanceof ConfigureCompleteEvent
				|| evt instanceof RealizeCompleteEvent
				|| evt instanceof PrefetchCompleteEvent) {
			synchronized (waitSync) {
				stateTransitionOK = true;
				waitSync.notifyAll();
			}
		} else if (evt instanceof ResourceUnavailableEvent) {
			synchronized (waitSync) {
				stateTransitionOK = false;
				waitSync.notifyAll();
			}
		} else if (evt instanceof EndOfMediaEvent) {
			p.close();
			System.exit(0);
		}
	}

	/**
	 * takes a string and makes a medialocator, then calls open.
	 */
	public void init(String url) {

		MediaLocator ml;

		if ((ml = new MediaLocator(url)) == null) {
			log.error("Cannot build media locator from: " + url);
			System.exit(0);
		}

		CaptureDeviceInfo info = CaptureDeviceManager.getDevice(url);

		// TODO doesn't do anything !
		Format[] fmts = info.getFormats();
		for (int i = 0; i != fmts.length; i++) {
			if (log.isDebugEnabled()) {
				log.debug("Format = " + fmts[i].toString());
			}
		}

		ml = info.getLocator();

		// TODO UI, might go ?
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));

		//		JMFInit fa = new JMFInit();
		//		JMFInit.imageContainer = new ImageContainer();

		try {
			setDataSource(ml);
		} catch (NoDataSourceException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		setPreferredFormat(dataSource, info);

		if (!open(ml))
			System.exit(0);

	}

	public Vector getDevices(VideoFormat prefFormat) {
		return CaptureDeviceManager.getDeviceList(prefFormat);
	}

	public static void main(String[] args) {
		String webcamURL = "vfw:Microsoft WDM Image Capture (Win32):0";

		JMFInit init = new JMFInit();
		init.init(webcamURL);
	}
}