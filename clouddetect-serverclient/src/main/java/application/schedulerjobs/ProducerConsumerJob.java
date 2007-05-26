package application.schedulerjobs;

import media.image.consumer.ImageConsumer;
import media.image.producer.ImageProducer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ProducerConsumerJob implements Job {

    JobDataMap map;
    private static Log log = LogFactory.getLog(ProducerConsumerJob.class);
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("Starting producer/consumer Job");
        map =  context.getJobDetail().getJobDataMap();
        ImageProducer producer = (ImageProducer) map.get("producer");
        ImageConsumer consumer = (ImageConsumer) map.get("consumer");
        consumer.consume(producer.produceContent());
        
    }

}
