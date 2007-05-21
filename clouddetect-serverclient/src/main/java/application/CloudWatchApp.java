package application;


import java.util.Date;

import media.image.consumer.ImageConsumer;
import media.image.consumer.ImageSubConsumer;
import media.image.consumer.UIPublishSubConsumer;
import media.image.producer.ImageProducer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.werx.framework.bus.ReflectionBus;

import ui.StartUI;
import application.InstanceFactory;
import application.schedulerjobs.ProducerConsumerJob;

public class CloudWatchApp {
    private CloudWatchConfig config;

    public void start(String[] args) {
        processCommandLine(args);
        startApplication();

    }

    private void processCommandLine(String[] args) {
        Options options = new Options();
        options.addOption("u", false, "Uses the User Interface");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            prUsage();
            System.exit(1);
        }

        if (cmd.hasOption("u")) {
            config.setCommandLine(false);
        }
    }

    private void startApplication() {

        // gets the producers, consumers
        ImageProducer producer = InstanceFactory.getImageProducer();
        ImageConsumer consumer = InstanceFactory.getImageConsumer();
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("imagescoringsubconsumer"));
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("cloudjudgesubconsumer"));
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("persistresulttodbsubconsumer"));
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getBean("savechartfromdbsubconsumer"));
        
        if (!config.isCommandLine()) {
            // starts the reflection bus
            ReflectionBus.start();

            StartUI frame = new StartUI();
            frame.start();
            consumer.addSubConsumer(new UIPublishSubConsumer());
        } else {
            // nothing to do
        }

        schedule(producer, consumer);
    }
    
    private void schedule(ImageProducer producer, ImageConsumer consumer) {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched;
        try {
            sched = schedFact.getScheduler();
            sched.start();

            JobDetail jobDetail = new JobDetail("producerConsumer", null, ProducerConsumerJob.class);
            jobDetail.getJobDataMap().put("producer", producer);
            jobDetail.getJobDataMap().put("consumer", consumer);

            Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);
            trigger.setStartTime(new Date());
            trigger.setName("myTrigger");

            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    private void prUsage() {
        System.err.println("Usage: java cloudFrame <url>");
    }

    public CloudWatchConfig getConfig() {
        return config;
    }

    public void setConfig(CloudWatchConfig config) {
        this.config = config;
    }

}
