package media.web;

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


public class ProxyConfiguratorTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  media.web.ProxyConfigurator proxyconfigurator = null;
  // JUnitDoclet end class
  
  public ProxyConfiguratorTest(String name) {
    // JUnitDoclet begin method ProxyConfiguratorTest
    super(name);
    // JUnitDoclet end method ProxyConfiguratorTest
  }
  
  public media.web.ProxyConfigurator createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new media.web.ProxyConfigurator();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    proxyconfigurator = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    proxyconfigurator = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testConfigure() throws Exception {
    // JUnitDoclet begin method configure
    // JUnitDoclet end method configure
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
    junit.textui.TestRunner.run(ProxyConfiguratorTest.class);
    // JUnitDoclet end method testcase.main
  }
}
