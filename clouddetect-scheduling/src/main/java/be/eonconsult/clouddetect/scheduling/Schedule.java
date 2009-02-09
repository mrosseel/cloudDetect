package be.eonconsult.clouddetect.scheduling;

import java.util.Date;
import java.util.List;

import media.image.consumer.Consumer;
import media.image.producer.Producer;
import media.image.producer.ProducerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import persistence.dao.FeedDao;
import persistence.model.Feed;
import persistence.model.ProducerType;
import application.InstanceFactory;

public class Schedule {
	SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
	private static Log log = (Log) LogFactory.getLog(Schedule.class);

	Scheduler sched;

	public void startScheduler() {

		try {
			sched = schedFact.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
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
		Producer<?> producer = ProducerFactory.getProducer(feed.getProducerType());
		producer.setSource(feed.getSource());
		log.debug("feed is " + feed.getId() + " has source " + producer.getSource() +".");
		producer.setSourceId((int)feed.getId());
		Consumer<?> consumer = getConsumer(feed, feed.getProducerType());

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

	private Consumer<?> getConsumer(Feed feed, ProducerType producerType) {
		Consumer<?> consumer = InstanceFactory.getConsumer(producerType, feed);
		
		return consumer;
	}
	
}
