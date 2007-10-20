package application;

import media.image.consumer.ImageConsumer;
import media.image.producer.FileImageProducer;
import media.image.producer.ImageProducer;
import media.processors.CalculateMetricOnCloudImage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import calculation.CloudJudge;

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
    
    public static ImageProducer getImageProducer() {
        return (ImageProducer) appContext.getBean("imageproducer");
    }
	
	public static ImageConsumer getImageConsumer() {
		return (ImageConsumer) appContext.getBean("imageconsumer");
	}
	
	public static ContrastChart getContrastChart() {
		return (ContrastChart) appContext.getBean("contrastchart");
	}
	
	public static CalculateMetricOnCloudImage getCalculateMetricOnCloudImage() {
		return (CalculateMetricOnCloudImage) appContext.getBean("calculatemetriconcloudimage");
	}
    
    public static CloudJudge getCloudJudge() {
        return (CloudJudge) appContext.getBean("cloudjudge");
    }
	
}
