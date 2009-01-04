package application;

import media.image.CloudFileResult;
import media.image.CloudImageResult;
import media.image.consumer.Boltwood1ScoringSubConsumer;
import media.image.consumer.Consumer;
import media.image.consumer.ImageScoringSubConsumer;
import media.image.consumer.JudgeFileSubConsumer;
import media.image.consumer.JudgeImageSubConsumer;
import media.image.consumer.SubConsumer;
import media.image.producer.FileImageProducer;
import media.processors.CalculateMetricOnCloudImage;
import notification.MailNotify;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import persistence.model.Feed;
import persistence.model.ProducerType;
import calculation.CloudJudge;
import calculation.ManualMetric;

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


    @SuppressWarnings("unchecked")
	public static Consumer<?> getConsumer(ProducerType producerType, Feed feed) {
    	CloudJudge cloudJudge;
    	
    	switch(producerType) {
    	
    	case httpboltwood1:
    		Consumer<CloudFileResult> consumer = (Consumer<CloudFileResult>) appContext.getBean("fileconsumer");
    		// construct the cloudjudge
    		cloudJudge = new CloudJudge();
    		cloudJudge.setCloudJudgeLimits(feed.getCloudJudgeLimits());
    		JudgeFileSubConsumer cloudJudgeSubConsumer = (JudgeFileSubConsumer) InstanceFactory.getBean("judgefilesubconsumer");
    		cloudJudgeSubConsumer.setCloudJudge(cloudJudge);

    		consumer.addSubConsumer(new Boltwood1ScoringSubConsumer());
    		consumer.addSubConsumer(cloudJudgeSubConsumer);
    		consumer.addSubConsumer((SubConsumer<CloudFileResult>) InstanceFactory.getBean("persistresulttodbsubconsumer"));

    		return consumer;

    	case httpimageproducer:
    		Consumer<CloudImageResult> consumer2 = (Consumer<CloudImageResult>) appContext.getBean("imageconsumer");
    		// construct the cloudjudge
    		cloudJudge = new CloudJudge();
    		cloudJudge.setCloudJudgeLimits(feed.getCloudJudgeLimits());
    		JudgeImageSubConsumer cloudJudgeSubConsumer2 = (JudgeImageSubConsumer) InstanceFactory.getBean("judgeimagesubconsumer");
    		cloudJudgeSubConsumer2.setCloudJudge(cloudJudge);

    		consumer2.addSubConsumer(getScoringSubConsumer(feed));
    		consumer2.addSubConsumer(cloudJudgeSubConsumer2);
    		consumer2.addSubConsumer((SubConsumer<CloudImageResult>) InstanceFactory.getBean("persistresulttodbsubconsumer"));

    		return consumer2;
    	}
    		
        return (Consumer<CloudImageResult>) appContext.getBean("imageconsumer");
    }
    
	@SuppressWarnings("unchecked")
	private static SubConsumer<CloudImageResult> getScoringSubConsumer(Feed feed) {
		SubConsumer<CloudImageResult> scoring = (SubConsumer<CloudImageResult>) InstanceFactory.getBean("imagescoringsubconsumer");
		CalculateMetricOnCloudImage calcMetric = (CalculateMetricOnCloudImage) InstanceFactory.getBean("calculatemetriconcloudimage");
		ManualMetric manualMetric = new ManualMetric();
		manualMetric.setManualSplitterLocation(feed.getDivision());
		calcMetric.setMetric(manualMetric);
		((ImageScoringSubConsumer) scoring).setMetricOnCloudImage(calcMetric);
		return scoring;
	}


    public static CloudJudge getCloudJudge() {
        return (CloudJudge) appContext.getBean("cloudjudge");
    }

    public static MailNotify getMailNotify() {
        return (MailNotify) appContext.getBean("mailnotify");
    }
}
