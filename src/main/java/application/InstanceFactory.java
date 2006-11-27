package application;

import media.image.consumer.ImageConsumer;
import media.image.producer.FileImageProducer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import ui.ContrastChart;

public class InstanceFactory {
	private static ClassPathResource res;
	private static XmlBeanFactory factory;
	
	static {
		// load spring stuff
		res = new ClassPathResource("cloudwatch-main.xml");
		factory = new XmlBeanFactory(res);
	}
	
	public static BeanFactory getBeanFactory() {
		return factory;
	}
	
	public static CloudWatchApp getCloudWatchApp() {
		return (CloudWatchApp) factory.getBean("cloudwatchapp");
	}
	
	public static FileImageProducer getFileImageProducer() {
		return (FileImageProducer) factory.getBean("fileimageproducer");
	}
	
	public static ImageConsumer getImageConsumer() {
		return (ImageConsumer) factory.getBean("imageconsumer");
	}
	
	public static ContrastChart getContrastChart() {
		return (ContrastChart) factory.getBean("contrastchart");
	}
	
}
