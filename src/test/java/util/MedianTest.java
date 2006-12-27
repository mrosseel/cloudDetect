package util;

import junit.framework.TestCase;
// JUnitDoclet begin import
import util.Median;
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class MedianTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  util.Median median = null;
  // JUnitDoclet end class
  
  public MedianTest(String name) {
    // JUnitDoclet begin method MedianTest
    super(name);
    // JUnitDoclet end method MedianTest
  }
  
  public util.Median createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return null;
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    median = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    median = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testSwap() throws Exception {
    // JUnitDoclet begin method swap
    // JUnitDoclet end method swap
  }
  
  public void testFind() throws Exception {
    // JUnitDoclet begin method find
      double[] testArray = { 1, 2 , 3, 4, 5, 6, 7, 8, 9, 10};
      double[] testArray2 = { 1, 2 , 3, 4, 5, 6, 7, 8, 9, 10};
      Median.find(testArray, 0, testArray.length-1);
      assertEquals(1, (long) testArray[0]);
      
    // JUnitDoclet end method find
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
    junit.textui.TestRunner.run(MedianTest.class);
    // JUnitDoclet end method testcase.main
  }
}
