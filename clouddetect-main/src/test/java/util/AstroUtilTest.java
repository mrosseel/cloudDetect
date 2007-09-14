package util;

import static org.testng.AssertJUnit.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.annotations.Test;

public class AstroUtilTest {

	@Test
	public void testRiseSet() {
		RiseSetPair pair = AstroUtil.getRiseSet(50, 4, new DateTime(2007, 9, 13, 0, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")));
		DateTime rise = new DateTime(2007,9,13,7,16,6,634,DateTimeZone.forID("Europe/Brussels"));
		DateTime set = new DateTime(2007,9,13,20,03,1,669,DateTimeZone.forID("Europe/Brussels"));
		assertEquals(pair.getRise(), rise);
		assertEquals(pair.getSet(), set);
		
	}
	
	@Test
	public void testLastNight() {
		// we're in the middle of the night
		RiseSetPair pair = AstroUtil.getLastNight(50, 4, new DateTime(2007, 9, 13, 0, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")), 0);
		DateTime rise = new DateTime(2007,9,12,20,03,1,669,DateTimeZone.forID("Europe/Brussels"));
		DateTime set = new DateTime(2007,9,13,7,16,6,634,DateTimeZone.forID("Europe/Brussels"));
		assertEquals(pair.getRise(), rise);
		assertEquals(pair.getSet(), set);
		
		// we're in broad daylight
		pair = AstroUtil.getLastNight(50, 4, new DateTime(2007, 9, 13, 12, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")), 0);
		assertEquals(pair.getRise(), rise);
		assertEquals(pair.getSet(), set);
		
		// we're in the late evening
		pair = AstroUtil.getLastNight(50, 4, new DateTime(2007, 9, 13, 23, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")), 0);
		rise = new DateTime(2007,9,13,20,03,1,669,DateTimeZone.forID("Europe/Brussels"));
		set = new DateTime(2007,9,14,7,16,6,634,DateTimeZone.forID("Europe/Brussels"));
		assertEquals(pair.getRise(), rise);
		assertEquals(pair.getSet(), set);
		
		// test dayoffset
		pair = AstroUtil.getLastNight(50, 4, new DateTime(2007, 9, 13, 23, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")), 1);
		rise = new DateTime(2007,9,12,20,03,1,669,DateTimeZone.forID("Europe/Brussels"));
		set = new DateTime(2007,9,13,7,16,6,634,DateTimeZone.forID("Europe/Brussels"));
		assertEquals(pair.getRise(), rise);
		assertEquals(pair.getSet(), set);

	}
	
	@Test
	public void testDateToJulian() {
		double result = AstroUtil.dateToJulian(new DateTime(2007, 9, 13, 0, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")));
		assertEquals(2454356.50000, result);
		
		// also test january for full coverage
		result = AstroUtil.dateToJulian(new DateTime(2007, 1, 13, 0, 0, 0, 0, DateTimeZone.forID("Europe/Brussels")));
		assertEquals(2454113.50000, result);
	
	}
}
