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
    
    // TODO
//    public static String getNightString(DateTime from, DateTime to) {
//    	StringBuffer result = new StringBuffer();
//    	
//    	result.append("Night of ").append(from.getDayOfMonth());
//    	
//    	if(from.getMonthOfYear() != to.getMonthOfYear()) {
//    		
//    	} else {
//    		result.append(" to ").append(to.getDayOfMonth()).append(" ");
//    	}
//    	
////    	result.append(to.property()month().getMonthOfYear() OfYear()OfYear())
////    	
////    	.getRise().toString("dd/MM/YY") + " to " + pair.getSet().toString("dd/MM/YY");
//    }

}
