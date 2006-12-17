package application;

import media.image.consumer.ImageConsumer;
import media.image.producer.FileImageProducer;
import media.image.producer.HTTPImageProducer;
import media.processors.BufferProcessor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import ui.ContrastChart;

public class InstanceFactory {
	private static ClassPathResource res;
	private static ApplicationContext appContext;
	
	static {
		// load spring stuff
        InstanceFactory.appContext = new ClassPathXmlApplicationContext(
                new String[] {"cloudwatch-main.xml"});
	}
	
	public static ApplicationContext getAppContext() {
		return appContext;
	}
	
	public static CloudWatchApp getCloudWatchApp() {
		return (CloudWatchApp) appContext.getBean("cloudwatchapp");
	}
	
	public static FileImageProducer getFileImageProducer() {
		return (FileImageProducer) appContext.getBean("fileimageproducer");
	}
    
    public static HTTPImageProducer getHTTPImageProducer() {
        return (HTTPImageProducer) appContext.getBean("httpimageproducer");
    }
	
	public static ImageConsumer getImageConsumer() {
		return (ImageConsumer) appContext.getBean("imageconsumer");
	}
	
	public static ContrastChart getContrastChart() {
		return (ContrastChart) appContext.getBean("contrastchart");
	}
	
	public static BufferProcessor getBufferProcessor() {
		return (BufferProcessor) appContext.getBean("bufferprocessor");
	}
	
}
