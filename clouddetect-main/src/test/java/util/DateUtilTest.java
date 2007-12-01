package util;

import static org.testng.AssertJUnit.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.testng.annotations.Test;

public class DateUtilTest {

	@Test
	public void testGetDistanceToNextMultipleOfFifteen() {
		MutableDateTime time = new MutableDateTime();
		time.setHourOfDay(23);
		time.setMinuteOfHour(23);
		assertEquals(7, DateUtil.getDistanceToMultipleOf15(time.toDateTime()));
		time.setMinuteOfHour(48);
		assertEquals(12, DateUtil.getDistanceToMultipleOf15(time.toDateTime()));
		time.setMinuteOfHour(59);
		assertEquals(1, DateUtil.getDistanceToMultipleOf15(time.toDateTime()));
		time.setMinuteOfHour(1);
		assertEquals(14, DateUtil.getDistanceToMultipleOf15(time.toDateTime()));		
	}
	
	@Test
	public void testWithinTimeFrame() {
		MutableDateTime from = new MutableDateTime();
		MutableDateTime to = new MutableDateTime();
		MutableDateTime now = new MutableDateTime();
		
		from.setHourOfDay(16);
	 	to.setHourOfDay(8);
	 	now.setHourOfDay(19);
	 	assertEquals(true, DateUtil.withinTimeFrame(from.toDateTime(), to.toDateTime(), now.toDateTime()));
	 	now.setHourOfDay(7);
	 	assertEquals(true, DateUtil.withinTimeFrame(from.toDateTime(), to.toDateTime(), now.toDateTime()));
	 	now.setHourOfDay(9);
	 	assertEquals(false, DateUtil.withinTimeFrame(from.toDateTime(), to.toDateTime(), now.toDateTime()));
	}
}
