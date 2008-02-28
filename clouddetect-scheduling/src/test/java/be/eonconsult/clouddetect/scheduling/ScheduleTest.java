package be.eonconsult.clouddetect.scheduling;

import org.quartz.Scheduler;
import org.testng.annotations.Test;

public class ScheduleTest {
	
	@Test
	public void test() {
		Schedule schedule = new Schedule();
		schedule.startScheduler();
	}

}
