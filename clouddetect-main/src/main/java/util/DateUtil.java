package util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtil {
    
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    
    public static Calendar getCurrentCalendar() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    public static int getDistanceToMultipleOf15(DateTime hour) {
    	return 15 - hour.getMinuteOfHour()%15;
    }
    

	public static boolean withinTimeFrame(DateTime from, DateTime to, DateTime now) {
		if(from.getMinuteOfDay() <= now.getMinuteOfDay() || to.getMinuteOfDay() >= now.getMinuteOfDay()) {
			return true;
		}
		
		return false;
	}

}
