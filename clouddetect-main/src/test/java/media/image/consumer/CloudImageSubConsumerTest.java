package media.image.consumer;

import junit.framework.TestCase;
import media.image.CloudImage;
import media.image.CloudImageImpl;
import notification.UnitTestNotify;

import org.joda.time.DateTime;

import calculation.CloudJudge;

public class CloudImageSubConsumerTest extends TestCase {
	CloudJudgeSubConsumer cj;
	CloudJudge judge;
	CloudImage image;
	UnitTestNotify notify = new UnitTestNotify();
	
	public CloudImageSubConsumerTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		notify.reset();
		image = new CloudImageImpl(new double[5], 5, 1);
		judge = new CloudJudge();
		judge.setMaxClear(1);
		judge.setMaxPartialClear(2);
		cj = new CloudJudgeSubConsumer(CloudJudge.CloudStatus.CLEAR);
		cj.setCloudJudge(judge);
		cj.setNotifier(notify);
		cj.setTransitionWaitInMinutes(10);
		super.setUp();
	}

	public void testSteadyClear() {
		// 2 clears
		consume(1.0, "2007-01-01T19:00+00:00");
		consume(1.0, "2007-01-01T19:11+00:00");
		assertTrue(notify.isNotified());
	}
		
	public void testSteadyCloudy() {
		// 2 cloudies
		consume(4.0, "2007-01-01T19:00+00:00");
		consume(4.0, "2007-01-01T19:11+00:00");
		assertFalse(notify.isNotified());
	}
	
	public void testClearToCloudy() {
		// 
		consume(1.0, "2007-01-01T19:00+00:00");
		consume(4.0, "2007-01-01T19:11+00:00");
		assertFalse(notify.isNotified());
		
	}
	
	public void testCloudyToClear() {
		consume(4.0, "2007-01-01T19:00+00:00");
		consume(1.0, "2007-01-01T19:11+00:00");
		assertFalse(notify.isNotified());
		
	}
	
	private void consume(double result, String iso8601) {
		DateTime firstDate = new DateTime(iso8601);
		
		image.getMetaData().setContrastResult(result);
		image.getMetaData().setDate(firstDate.toDate());	
		cj.consume(image);
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	
	

}
