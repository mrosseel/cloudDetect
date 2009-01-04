package be.eonconsult.clouddetect.scheduling;

import media.image.consumer.Consumer;
import media.image.producer.Producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import persistence.model.Feed;

public class FeedJob implements Job {

	JobDataMap map;

	private static Log log = LogFactory.getLog(FeedJob.class);

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		map = context.getJobDetail().getJobDataMap();
		Feed feed = (Feed) map.get("feed");
		Producer<?> producer = (Producer<?>) map.get("producer");
		Consumer<?> consumer = (Consumer<?>) map.get("consumer");
		log.debug("Starting feed Job for feed " + feed.getId());
		consumer.consume(producer);
	}
}
