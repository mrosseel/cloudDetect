package ui;

import junit.framework.TestSuite;
import ui.commands.CommandsSuite;
import ui.handlers.HandlersSuite;
import ui.signal.SignalSuite;


// JUnitDoclet begin import
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class UiSuite
// JUnitDoclet begin extends_implements
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  // JUnitDoclet end class
  
  public static TestSuite suite() {
    
    TestSuite suite;
    
    suite = new TestSuite("ui");
    
    suite.addTestSuite(ui.WebcamSettingsPanelTest.class);
    suite.addTestSuite(ui.StatusPanelTest.class);
    suite.addTestSuite(ui.RightPanelTest.class);
    suite.addTestSuite(ui.ImagePanelTest.class);
    suite.addTestSuite(ui.ControlPanelTest.class);
    suite.addTestSuite(ui.ContrastChartTest.class);
    suite.addTest(ui.commands.CommandsSuite.suite());
    suite.addTest(ui.handlers.HandlersSuite.suite());
    suite.addTest(ui.signal.SignalSuite.suite());
    
    
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
