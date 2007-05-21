package application;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import application.schedulerjobs.DumbJob;

public class CloudWatchScheduler {

    public void startScheduler() {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched;
        try {
            sched = schedFact.getScheduler();

            sched.start();

            JobDetail jobDetail = new JobDetail("myJob", null, DumbJob.class);

            Trigger trigger = TriggerUtils.makeSecondlyTrigger(10); // fire every
                                                                // hour
            trigger.setStartTime(new Date()); // start
                                                                            // on
                                                                            // the
                                                                            // next
                                                                            // even
                                                                            // hour
            trigger.setName("myTrigger");

            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        CloudWatchScheduler sched = new CloudWatchScheduler();
        sched.startScheduler();
    }
}
