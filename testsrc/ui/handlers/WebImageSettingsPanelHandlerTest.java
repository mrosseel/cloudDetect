package ui.handlers;

import junit.framework.TestCase;
// JUnitDoclet begin import
import ui.handlers.WebImageSettingsPanelHandler;
// JUnitDoclet end import

/**
* Generated by JUnitDoclet, a tool provided by
* ObjectFab GmbH under LGPL.
* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class WebImageSettingsPanelHandlerTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  ui.handlers.WebImageSettingsPanelHandler webimagesettingspanelhandler = null;
  // JUnitDoclet end class
  
  public WebImageSettingsPanelHandlerTest(String name) {
    // JUnitDoclet begin method WebImageSettingsPanelHandlerTest
    super(name);
    // JUnitDoclet end method WebImageSettingsPanelHandlerTest
  }
  
  public ui.handlers.WebImageSettingsPanelHandler createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new ui.handlers.WebImageSettingsPanelHandler();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    webimagesettingspanelhandler = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    webimagesettingspanelhandler = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
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
    junit.textui.TestRunner.run(WebImageSettingsPanelHandlerTest.class);
    // JUnitDoclet end method testcase.main
  }
}
