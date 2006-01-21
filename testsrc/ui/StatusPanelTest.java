package ui;

import junit.framework.TestCase;
// JUnitDoclet begin import
import ui.StatusPanel;
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class StatusPanelTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  ui.StatusPanel statuspanel = null;
  // JUnitDoclet end class
  
  public StatusPanelTest(String name) {
    // JUnitDoclet begin method StatusPanelTest
    super(name);
    // JUnitDoclet end method StatusPanelTest
  }
  
  public ui.StatusPanel createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new ui.StatusPanel();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    statuspanel = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    statuspanel = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testSetGetStatusMessage() throws Exception {
    // JUnitDoclet begin method setStatusMessage getStatusMessage
    java.lang.String[] tests = {"", " ", "a", "A", "�", "�", "0123456789", "012345678901234567890", "\n", null};
    
    for (int i = 0; i < tests.length; i++) {
      statuspanel.setStatusMessage(tests[i]);
      assertEquals(tests[i], statuspanel.getStatusMessage());
    }
    // JUnitDoclet end method setStatusMessage getStatusMessage
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
    junit.textui.TestRunner.run(StatusPanelTest.class);
    // JUnitDoclet end method testcase.main
  }
}
