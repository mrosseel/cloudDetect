package be.eonconsult.clouddetect.scheduling;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartupWatch implements ServletContextListener {
	Schedule schedule = new Schedule();;

	public void contextDestroyed(ServletContextEvent arg0) {
		schedule.stopScheduler();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		schedule.startScheduler();
	}
}
