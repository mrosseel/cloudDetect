package be.eonconsult.clouddetect.web.services;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import calculation.CloudJudge.CloudStatus;
import static org.testng.AssertJUnit.assertEquals;

public class ClearCloudyMonitorTest {

	private ClearCloudyMonitor monitor;
	private static final CloudStatus clear = CloudStatus.CLEAR;
	private static final CloudStatus cloudy = CloudStatus.CLOUDED;
	
	@BeforeMethod
	private void setup() {
		monitor = new ClearCloudyMonitor();
	}
	
	@Test
	public void testClearMonitor() {
		// first init
		monitor.setDelayClear(10);
		assertEquals(false, isClearNotify(clear, "2007-01-01T19:00+00:00"));
		monitor.setDelayClear(0);
		assertEquals(false, isClearNotify(cloudy, "2007-01-01T19:01+00:00"));
		assertEquals(true, isClearNotify(clear, "2007-01-01T19:02+00:00"));
		monitor.setDelayClear(10);
		assertEquals(false, isClearNotify(clear, "2007-01-01T19:13+00:00"));
		assertEquals(false, isClearNotify(cloudy, "2007-01-01T19:14+00:00"));
		assertEquals(false, isClearNotify(clear, "2007-01-01T19:15+00:00"));
		assertEquals(true, isClearNotify(clear, "2007-01-01T19:25+00:00"));
	}
	
	@Test
	public void testCloudyMonitor() {
		// first init
		monitor.setDelayCloudy(10);
		assertEquals(false, isCloudyNotify(cloudy, "2007-01-01T19:00+00:00"));
		monitor.setDelayCloudy(0);
		assertEquals(false, isCloudyNotify(clear, "2007-01-01T19:01+00:00"));
		assertEquals(true, isCloudyNotify(cloudy, "2007-01-01T19:02+00:00"));
	}
	
	@Test
	public void immediateZero() {
		monitor.setDelayClear(0);
		assertEquals(false, isClearNotify(clear, "2007-01-01T19:00+00:00"));
		assertEquals(false, isClearNotify(clear, "2007-01-01T19:01+00:00"));
	}
	
	private boolean isClearNotify(CloudStatus status, String iso8601) {
		return monitor.isClearNotify(status, new DateTime(iso8601).toDate());
	}
	
	private boolean isCloudyNotify(CloudStatus status, String iso8601) {
		return monitor.isCloudyNotify(status, new DateTime(iso8601).toDate());
	}
}
