/*
 * Created on 23-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package jmf;

import java.util.ArrayList;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Mike
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JMFSettings {

	private static Log log = LogFactory.getLog(JMFSettings.class);

	private Vector deviceListVector;

	private ArrayList videoDeviceList;

	private ArrayList videoFormatList;

	private static String defaultVideoDeviceName = "Logitech USB Video Camera";

	private static String defaultAudioDeviceName = "DirectSoundCapture";

	private static String defaultVideoFormatString = "size=176x144, encoding=yuv, maxdatalength=38016";

	private static String defaultAudioFormatString = "linear, 16000.0 hz, 8-bit, mono, unsigned";

	private static CaptureDeviceInfo captureVideoDevice = null;

	private static CaptureDeviceInfo captureAudioDevice = null;

	private static VideoFormat captureVideoFormat = null;

	private static AudioFormat captureAudioFormat = null;

	/**
	 *  
	 */
	public JMFSettings() {

	}

	/**
	 * Do we detect any Webcams ?
	 * 
	 * @return
	 */
	public boolean isWebcamDetected() {
		if (deviceListVector == null) {
			getDevices();
		}

		return deviceListVector != null;
	}

	/**
	 *  
	 */
	private void getDevices() {
		deviceListVector = CaptureDeviceManager.getDeviceList(null);
	}

	public void getWebcams() {
		getWebcamInfo();
	}

	/**
	 * TODO: code zoekt nog naar een specifieke device, debug, kijk wat er in de devicelist zit,
	 * check op instance en add alle interessante dingen.
	 * zie http://archives.java.sun.com/cgi-bin/wa?A2=ind0407&L=jmf-interest&F=&S=&P=5084
	 * voor info over frameratecontrol
	 *
	 */
	public void getWebcamInfo() {
		log.debug("Getting list of webcams");
		videoDeviceList = new ArrayList();
		videoFormatList = new ArrayList();

		java.util.Vector deviceListVector = CaptureDeviceManager
				.getDeviceList(null);

		if (!isWebcamDetected()) {
			log.error("no webcams detected");
		}

		CaptureDeviceInfo deviceInfo;

		for (int x = 0; x < deviceListVector.size(); x++) {
			// display device name
			deviceInfo = (CaptureDeviceInfo) deviceListVector.elementAt(x);
			String deviceInfoText = deviceInfo.getName();

			log.info("device " + x + ": " + deviceInfoText);

			// display device formats
			Format deviceFormat[] = deviceInfo.getFormats();
			for (int y = 0; y < deviceFormat.length; y++) {
				// serach for default video device
				if (deviceFormat[y] instanceof VideoFormat) {
					videoDeviceList.add(deviceInfo);
					log.info("adding device: " + deviceInfo.getName());
				}

				// search for default video format
				if (captureVideoDevice == deviceInfo) {
					if (captureVideoFormat == null) {
						videoFormatList.add(deviceFormat[y]);
						log.info("adding format = "
								+ DeviceInfo.formatToString(deviceFormat[y]));
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		JMFSettings settings = new JMFSettings();
		settings.getWebcams();
	}
}