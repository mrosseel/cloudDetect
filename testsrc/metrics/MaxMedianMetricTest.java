package metrics;

import junit.framework.TestCase;
// JUnitDoclet begin import
import metrics.MaxMedianMetric;
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class MaxMedianMetricTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  metrics.SplitterMetric maxmedianmetric = null;
  // JUnitDoclet end class
  
  public MaxMedianMetricTest(String name) {
    // JUnitDoclet begin method MaxMedianMetricTest
    super(name);
    // JUnitDoclet end method MaxMedianMetricTest
  }
  
  public metrics.SplitterMetric createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new metrics.MaxMedianMetric();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    maxmedianmetric = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    maxmedianmetric = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testCompute() throws Exception {
    // JUnitDoclet begin method compute
     short[] data = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 };
     SplitterMetric metric = new MaxMedianMetric();
     assertEquals(0.0, metric.compute(data), 0.0);




    // JUnitDoclet end method compute
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
    junit.textui.TestRunner.run(MaxMedianMetricTest.class);
    // JUnitDoclet end method testcase.main
  }
}
