package util;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

import com.mhuss.AstroLib.Latitude;
import com.mhuss.AstroLib.Longitude;
import com.mhuss.AstroLib.ObsInfo;
import com.mhuss.AstroLib.RiseSet;
import com.mhuss.AstroLib.TimePair;

public class AstroUtil {
	
	public static RiseSetPair getRiseSet(double lat, double lon, DateTime date) {
		TimePair pair = RiseSet.getTimes(RiseSet.SUN, dateToJulian(date), 
				new ObsInfo(new Latitude(lat), new Longitude(lon), 0)); 
		return assembleRiseSetPair(pair.a, pair.b, date);
	}

	public static RiseSetPair getLastNight(double lat, double lon, DateTime date, int dayOffset) {
	
		RiseSetPair pair = getRiseSet(lat, lon, date);
		// save original rise value
		DateTime temp = pair.getRise();
		if(date.isAfter(pair.getSet()) && date.isBefore(pair.getRise().plusDays(1))) {
			pair.setRise(pair.getSet().minusDays(dayOffset));
			pair.setSet(temp.plusDays(1-dayOffset));
		} else {
			
			pair.setRise(pair.getSet().minusDays(1+dayOffset));
			pair.setSet(temp.minusDays(dayOffset));
		}
		
		return pair;
	}
	
	
	
	/**
	 * Joda date to julian day
	 * 
	 * see meeus p61
	 * ignores all time info, is effectively the date at midnight (of the current day)
	 * @param date
	 * @return
	 */
	public static double dateToJulian(DateTime date) {
			   int year=date.getYear();
			   int month=date.getMonthOfYear(); // jan=1, feb=2,...
			   int day=date.getDayOfMonth();    
			   if (month <= 2) {
			     year--;
			     month += 12;
			   }
			   double a = Math.floor(year/100.0);
			   double b = 2 - a + Math.floor(a/4.0);
			   
			   
			   double julian = Math.floor(365.25*(year + 4716)) + Math.floor(30.6001*(month+1)) + day +b -1524.5;
			   return julian;
	}

	private static RiseSetPair assembleRiseSetPair(double a, double b, DateTime date) {
		DateTime rise = convertToDateTime(a, date);
		DateTime set = convertToDateTime(b, date);
		return new RiseSetPair(rise, set);
		
	}
	private static DateTime convertToDateTime(double fraction, DateTime date) {
		double milliSecs = fraction*24*3600*1000 + date.getZone().getOffset(date);

		MutableDateTime convertedDate = new MutableDateTime(date);
		convertedDate.setMillisOfDay((int) Math.round(milliSecs));

		return convertedDate.toDateTime();
	}
}
