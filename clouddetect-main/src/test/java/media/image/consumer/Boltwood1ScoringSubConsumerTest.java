package media.image.consumer;

import java.io.File;

import junit.framework.TestCase;
import media.image.CloudFileResult;
import media.image.CloudFileResultImpl;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Boltwood1ScoringSubConsumerTest extends TestCase {
	Boltwood1ScoringSubConsumer bs = new Boltwood1ScoringSubConsumer();

	CloudFileResult result;
	
	public Boltwood1ScoringSubConsumerTest(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		result = new CloudFileResultImpl();
		result.setOriginComment("2008-12-26 07:56:28 C  -30,1  -3,0  -3,0   0 0 00003 039808,33088 1");
		
		super.setUp();
	}
	
	public void testConsume() {
		bs.consume(result);
		assertEquals(1.0, result.getMetaData().getResult());
		assertEquals("Fri Dec 26 07:56:28 CET 2008", result.getMetaData().getDate().toString());
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
