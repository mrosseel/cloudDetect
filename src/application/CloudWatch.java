/*
 * Created on 2-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;

import media.jmf.JMFInit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.werx.framework.bus.ReflectionBus;

/**
 * Main class of The CloudWatch program (project name change pending).
 * 
 * UI references will be shot on sight.
 * Relience on JMF classes will be prosecuted to the fullest extent of law.
 * 
 * @author Mike
 */
public class CloudWatch {
	private static boolean usesUI = false;
	
	static void prUsage() {
		System.err.println("Usage: java cloudFrame <url>");
	}
	
	public CloudWatchConfig processCommandLine(String[] args) {
		Options options = new Options();
		CloudWatchConfig config = new CloudWatchConfig();
		options.addOption("u", false, "Uses the User Interface");
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			prUsage();
			System.exit(1);
		}
		
		if(cmd.hasOption("u")) {
		   config.usesUI = true;
		}
		
		return config;
	}
	
	public void startApplication(CloudWatchConfig config) {

		if(config.usesUI) {
			CloudFrame frame = new CloudFrame();
			ReflectionBus.start();
			frame.start();
		} else {
			JMFInit jmf = new JMFInit();
			//jmf.init("vfw:Microsoft WDM Image Capture (Win32):0");	
			jmf.init("v4l:Philips 740 webcam:0");	
			
		}//
	}
	
	public static void main(String[] args) {
		CloudWatch app = new CloudWatch();
		CloudWatchConfig config = app.processCommandLine(args);
		app.startApplication(config);
	}

}



