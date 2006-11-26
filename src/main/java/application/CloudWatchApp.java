package application;

import media.image.producer.jmf.JMFInit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import ui.StartUI;

public class CloudWatchApp {
	private CloudWatchConfig config;

	public void start(String[] args) {
		processCommandLine(args);
		startApplication();
		
	}
	
	private void processCommandLine(String[] args) {
		Options options = new Options();
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
		   config.setCommandLine(false);
		}
	}
	
	private void startApplication() {

		if(!config.isCommandLine()) {
			StartUI frame = new StartUI();
			frame.start();
		} else {
			JMFInit jmf = new JMFInit();
			//jmf.init("vfw:Microsoft WDM Image Capture (Win32):0");	
			jmf.init("v4l:Philips 740 webcam:0");	
			
		}//
	}
	
	private void prUsage() {
		System.err.println("Usage: java cloudFrame <url>");
	}
	
	
	
	public CloudWatchConfig getConfig() {
		return config;
	}

	public void setConfig(CloudWatchConfig config) {
		this.config = config;
	}

}
