package metrics;

import calculation.PixelBrightnessMetric;
import junit.framework.TestCase;
// JUnitDoclet begin import
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class PixelBrightnessMetricTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  calculation.PixelBrightnessMetric pixelbrightnessmetric = null;
  // JUnitDoclet end class
  
  public PixelBrightnessMetricTest(String name) {
    // JUnitDoclet begin method PixelBrightnessMetricTest
    super(name);
    // JUnitDoclet end method PixelBrightnessMetricTest
  }
  
  public calculation.PixelBrightnessMetric createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new calculation.PixelBrightnessMetric();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    pixelbrightnessmetric = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    pixelbrightnessmetric = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testCompute() throws Exception {
    // JUnitDoclet begin method compute
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
    junit.textui.TestRunner.run(PixelBrightnessMetricTest.class);
    // JUnitDoclet end method testcase.main
  }
}
