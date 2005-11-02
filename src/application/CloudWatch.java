/*
 * Created on 2-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

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
	
	
	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("u", false, "Uses the User Interface");
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(cmd.hasOption("u")) {
		   usesUI = true;
		}
		
		if(usesUI) {
			JMFInit jmf = new JMFInit();
			jmf.init();
		}
		
	}

}
