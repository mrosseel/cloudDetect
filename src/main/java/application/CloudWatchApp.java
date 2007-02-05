package application;

import media.image.consumer.ImageConsumerImpl;
import media.image.consumer.ImageScoringSubConsumer;
import media.image.consumer.UIPublishSubConsumer;
import media.image.producer.HTTPImageProducer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.werx.framework.bus.ReflectionBus;

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
		// starts the reflection bus
		ReflectionBus.start();
		// gets the producers
		//FileImageProducer producer = InstanceFactory.getFileImageProducer();
//       gets the producers
        HTTPImageProducer producer = InstanceFactory.getHTTPImageProducer();
        
		// TODO get rid of this ugly cast !!!
		ImageConsumerImpl consumer = (ImageConsumerImpl) InstanceFactory.getImageConsumer();
		consumer.addSubConsumer(new ImageScoringSubConsumer());

		if(!config.isCommandLine()) {
			StartUI frame = new StartUI();
			frame.start();
			consumer.addSubConsumer(new UIPublishSubConsumer());
		} else {
			//JMFInit jmf = new JMFInit();
			//jmf.init("vfw:Microsoft WDM Image Capture (Win32):0");	
			//jmf.init("v4l:Philips 740 webcam:0");	
			
		}//
		producer.start();
		consumer.start();
		
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
