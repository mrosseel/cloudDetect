package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    
    public static Calendar getCurrentCalendar() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

}
