

package application;

/*
 * @(#)cloudFrame.java	1.5 01/03/13
 *
 * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

//import jmapps.jmstudio.CaptureDialog;
//import jmapps.util.JMAppsCfg;

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
 * Sample program to access individual video frames by using a
 * "pass-thru" codec.  The codec is inserted into the data flow
 * path.  As data pass through this codec, a callback is invoked
 * for each frame of video data.
 */
public class CloudFrame extends Frame {
	public static ContrastChart demo;
	Processor p;
	Object waitSync = new Object();
	boolean stateTransitionOK = true;
	public static ImageContainer imageContainer;

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
	public boolean open(MediaLocator ml) {

		//setVisible(true);
		CloudWatchFrame frame = new CloudWatchFrame();
		CloudFrame.demo = frame.getContrastChart();
		CloudFrame.demo.setVisible(true);
		frame.pack();
		frame.setVisible(true);

		return true;
	}


	/**
	 * Main program
	 */
	public static void main(String[] args) {

		ReflectionBus.start();
		if (args.length == 0) {
			prUsage();
			System.exit(0);
		}

		String url = args[0];

		if (url.indexOf(":") < 0) {
			prUsage();
			System.exit(0);
		}
		

		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));

		CloudFrame fa = new CloudFrame();
		CloudFrame.imageContainer = new ImageContainer(); 

		if (!fa.open(null))
			System.exit(0);

	}

	static void prUsage() {
		System.err.println("Usage: java cloudFrame <url>");
	}
}
