package be.eonconsult.clouddetect.scheduling;

import java.util.Date;
import java.util.List;

import media.image.consumer.ImageConsumer;
import media.image.consumer.ImageSubConsumer;
import media.image.producer.ImageProducer;
import media.image.producer.ProducerFactory;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import persistence.dao.FeedDao;
import persistence.model.Feed;
import application.InstanceFactory;

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
		producer.setSourceId(feed.getId());
		ImageConsumer consumer = InstanceFactory.getImageConsumer();
		consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("imagescoringsubconsumer"));
		consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("cloudjudgesubconsumer"));
		consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("persistresulttodbsubconsumer"));

		JobDetail jobDetail = new JobDetail("producerConsumerFeed" + feed.getId(), null, FeedJob.class);
		jobDetail.getJobDataMap().put("feed", feed);
		jobDetail.getJobDataMap().put("producer", producer);
		jobDetail.getJobDataMap().put("consumer", consumer);

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
}
