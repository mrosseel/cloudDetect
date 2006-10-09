package util;

import junit.framework.TestSuite;


// JUnitDoclet begin import
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class UtilSuite
// JUnitDoclet begin extends_implements
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  // JUnitDoclet end class
  
  public static TestSuite suite() {
    
    TestSuite suite;
    
    suite = new TestSuite("util");
    
    suite.addTestSuite(util.TextProgressBarTest.class);
    suite.addTestSuite(util.StringToolkitTest.class);
    suite.addTestSuite(util.StandardDeviationTest.class);
    suite.addTestSuite(util.ParametersTest.class);
    suite.addTestSuite(util.MedianTest.class);
    suite.addTestSuite(util.MathMethodsTest.class);
    suite.addTestSuite(util.Kmeans_oldTest.class);
    suite.addTestSuite(util.KMeansTest.class);
    suite.addTestSuite(util.IntVectorTest.class);
    suite.addTestSuite(util.ImageToolkitTest.class);
    suite.addTestSuite(util.ImageStackerTest.class);
    
    // JUnitDoclet begin method suite
    // JUnitDoclet end method suite
    
    return suite;
  }
  
  public static void main(String[] args) {
    // JUnitDoclet begin method testsuite.main
    junit.textui.TestRunner.run(suite());
    // JUnitDoclet end method testsuite.main
  }
}
