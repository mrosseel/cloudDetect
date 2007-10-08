package be.eonconsult.clouddetect.web.services;

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
		assertEquals(false, monitor.isClearNotify(clear));
		monitor.setDelayClear(0);
		assertEquals(false, monitor.isClearNotify(cloudy));
		assertEquals(true, monitor.isClearNotify(clear));
		monitor.setDelayClear(10);
	}
	
	@Test
	public void testCloudyMonitor() {
		// first init
		monitor.setDelayCloudy(10);
		assertEquals(false, monitor.isCloudyNotify(cloudy));
		monitor.setDelayCloudy(0);
		assertEquals(false, monitor.isCloudyNotify(clear));
		assertEquals(true, monitor.isCloudyNotify(cloudy));
	}
	
	@Test
	public void immediatZero() {
		monitor.setDelayClear(0);
		assertEquals(false, monitor.isClearNotify(clear));
		assertEquals(false, monitor.isClearNotify(clear));
	}
}
