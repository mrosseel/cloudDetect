/*
 * Created on 2-nov-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Main class of The CloudWatch program (project name change pending).
 * 
 * UI references will be shot on sight.
 * Relience on JMF classes will be prosecuted to the fullest extent of law.
 * 
 * @author Mike
 */
public class CloudWatch {
	public static BeanFactory factory;
	
	
	
	public static void main(String[] args) {
		// load spring stuff
		ClassPathResource res = new ClassPathResource("cloudwatch-main.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		CloudWatch.factory = factory;
		
		CloudWatchApp application = (CloudWatchApp) factory.getBean("cloudwatchapp");
		
		application.start(args);
	}

}



