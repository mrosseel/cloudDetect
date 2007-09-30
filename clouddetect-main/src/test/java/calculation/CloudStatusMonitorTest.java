package calculation;

import static org.testng.AssertJUnit.assertEquals;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import calculation.CloudJudge.CloudStatus;

public class CloudStatusMonitorTest {
	CloudStatusMonitor monitor;


	@BeforeMethod
	protected void setUp() throws Exception {
		monitor = new CloudStatusMonitor(CloudJudge.CloudStatus.CLEAR);
		monitor.setTransitionWaitInMinutes(10);
	}

	@Test
	public void testSteadyClear() {
		// 2 clears
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:00+00:00"));
		assertEquals(true, check(CloudStatus.CLEAR, "2007-01-01T19:11+00:00"));
	}
		
	@Test
	public void testSteadyCloudy() {
		// 2 cloudies
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T19:00+00:00"));
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T19:11+00:00"));
	}
	
	@Test
	public void testClearToCloudy() {
		// 
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:00+00:00"));
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T19:11+00:00"));
	}
	
	@Test
	public void testCloudyToClear() {
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T19:00+00:00"));
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:11+00:00"));
	}
	
	@Test
	public void testPrimedState() {
		monitor = new CloudStatusMonitor(CloudJudge.CloudStatus.CLEAR, CloudJudge.CloudStatus.CLEAR);
		monitor.setTransitionWaitInMinutes(10);
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:00+00:00"));
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:11+00:00"));
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T19:12+00:00"));
		assertEquals(false, check(CloudStatus.CLEAR, "2007-01-01T19:23+00:00"));
		assertEquals(true, check(CloudStatus.CLEAR, "2007-01-01T19:34+00:00"));
		monitor.setTransitionWaitInMinutes(0);
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T20:00+00:00"));
		assertEquals(true, check(CloudStatus.CLEAR, "2007-01-01T20:01+00:00"));
		assertEquals(false, check(CloudStatus.CLOUDED, "2007-01-01T20:01+00:00"));
		assertEquals(true, check(CloudStatus.CLEAR, "2007-01-01T20:01+00:00"));
	}
	
	private boolean check(CloudStatus status, String iso8601) {
		return monitor.checkIfNotify(status, new DateTime(iso8601).toDate());
	}
}

