package metrics;

import junit.framework.TestCase;
// JUnitDoclet begin import
import metrics.MaxStdDevMetric;
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/

public class MaxStdDevMetricTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	metrics.MaxStdDevMetric maxstddevmetric = null;
	// JUnitDoclet end class

	public MaxStdDevMetricTest(String name) {
		// JUnitDoclet begin method MaxStdDevMetricTest
		super(name);
		// JUnitDoclet end method MaxStdDevMetricTest
	}

	public metrics.MaxStdDevMetric createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new metrics.MaxStdDevMetric();
		// JUnitDoclet end method testcase.createInstance
	}

	protected void setUp() throws Exception {
		// JUnitDoclet begin method testcase.setUp
		super.setUp();
		maxstddevmetric = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		maxstddevmetric = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	public void testCompute() {
		short[] data = new short[]{
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		maxstddevmetric.compute(data);
		assertEquals(50, maxstddevmetric.getPctSplitterLocation());
		
		data = new short[]{
					0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		maxstddevmetric.compute(data);
		assertEquals(70, maxstddevmetric.getPctSplitterLocation());
		assertEquals(1.0, maxstddevmetric.getResult(), 0.0);
				
	}

	/**
	* JUnitDoclet moves marker to this method, if there is not match
	* for them in the regenerated code and if the marker is not empty.
	* This way, no test gets lost when regenerating after renaming.
	* Method testVault is supposed to be empty.
	*/
	public void testVault() throws Exception {
		// JUnitDoclet begin method testcase.testVault
		// JUnitDoclet end method testcase.testVault
	}

	public static void main(String[] args) {
		// JUnitDoclet begin method testcase.main
		junit.textui.TestRunner.run(MaxStdDevMetricTest.class);
		// JUnitDoclet end method testcase.main
	}
}
