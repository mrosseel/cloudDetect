package application;

import media.image.consumer.ImageConsumer;
import media.image.producer.FileImageProducer;
import media.image.producer.ImageProducer;
import media.processors.CalculateMetricOnCloudImage;
import notification.MailNotify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import ui.ContrastChart;
import calculation.CloudJudge;

public class InstanceFactory {
    private static ClassPathResource res;

    private static ApplicationContext appContext;

    static {
        // load spring stuff
        InstanceFactory.appContext = new ClassPathXmlApplicationContext(
                new String[] { "cloudwatch-main.xml" });
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
    
    /**
     * Convenience function to save some key strokes
     * 
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
    	return appContext.getBean(beanName);
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

    public static CloudJudge getCloudJudge() {
        return (CloudJudge) appContext.getBean("cloudjudge");
    }

    public static MailNotify getMailNotify() {
        return (MailNotify) appContext.getBean("mailnotify");
    }
}
