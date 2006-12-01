/*
 * Created on 2-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;


/**
 * Main class of The CloudWatch program (project name change pending).
 * 
 * UI references will be shot on sight.
 * Relience on JMF classes will be prosecuted to the fullest extent of law.
 * 
 * @author Mike
 */
public class CloudWatch {
	
	public static void main(String[] args) {
		
		CloudWatchApp application = InstanceFactory.getCloudWatchApp();
		
		application.start(args);
	}

}



