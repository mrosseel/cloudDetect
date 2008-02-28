package be.eonconsult.clouddetect.scheduling;

import java.util.Date;
import java.util.List;

import media.image.consumer.CloudJudgeSubConsumer;
import media.image.consumer.ImageConsumer;
import media.image.consumer.ImageScoringSubConsumer;
import media.image.consumer.ImageSubConsumer;
import media.image.producer.ImageProducer;
import media.image.producer.ProducerFactory;
import media.processors.CalculateMetricOnCloudImage;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import persistence.dao.FeedDao;
import persistence.model.Feed;
import application.InstanceFactory;
import calculation.CloudJudge;
import calculation.ManualMetric;

public class Schedule {
	SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

	Scheduler sched;

	public void startScheduler() {

		try {
			sched = schedFact.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FeedDao dao = (FeedDao) InstanceFactory.getBean("feeddao");
		List<Feed> feed = dao.getAllActiveFeeds();
		for (Feed currentFeed : feed) {
			schedule(currentFeed);
		}
	}

	public void stopScheduler() {
		try {
			sched = schedFact.getScheduler();
			sched.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void schedule(Feed feed) {

		// gets the producers, consumers
		ImageProducer producer = ProducerFactory.getImageProducer(feed.getProducerType());
		producer.setSource(feed.getSource());
		producer.setSourceId((int)feed.getId());
		ImageConsumer consumer = InstanceFactory.getImageConsumer();
		
		// construct the cloudjudge
		CloudJudge cloudJudge = new CloudJudge();
		cloudJudge.setCloudJudgeLimits(feed.getCloudJudgeLimits());
		CloudJudgeSubConsumer cloudJudgeSubConsumer = new CloudJudgeSubConsumer();
		cloudJudgeSubConsumer.setCloudJudge(cloudJudge);
		
		consumer.addSubConsumer(getScoringSubConsumer(feed));
		consumer.addSubConsumer(cloudJudgeSubConsumer);
		consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("persistresulttodbsubconsumer"));

		// make jobdetail + map
		JobDetail jobDetail = new JobDetail("producerConsumerFeed" + feed.getId(), null, FeedJob.class);
		jobDetail.getJobDataMap().put("feed", feed);
		jobDetail.getJobDataMap().put("producer", producer);
		jobDetail.getJobDataMap().put("consumer", consumer);

		// make trigger
		Trigger trigger = TriggerUtils.makeSecondlyTrigger((int) feed.getSecondsBetweenUpdates());
		trigger.setStartTime(new Date());
		trigger.setName("FeedTrigger" + feed.getId());

		try {
			sched.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ImageScoringSubConsumer getScoringSubConsumer(Feed feed) {
		ImageScoringSubConsumer scoring = (ImageScoringSubConsumer) InstanceFactory.getBean("imagescoringsubconsumer");
		CalculateMetricOnCloudImage calcMetric = (CalculateMetricOnCloudImage) InstanceFactory.getBean("calculatemetriconcloudimage");
		ManualMetric manualMetric = new ManualMetric();
		manualMetric.setManualSplitterLocation(feed.getDivision());
		calcMetric.setMetric(manualMetric);
		scoring.setMetricOnCloudImage(calcMetric);
		return scoring;
	}
}
