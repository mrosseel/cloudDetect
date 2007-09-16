package persistence;

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


public class XMLPersisterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  persistence.XMLPersister xmlpersister = null;
  // JUnitDoclet end class
  
  public XMLPersisterTest(String name) {
    // JUnitDoclet begin method XMLPersisterTest
    super(name);
    // JUnitDoclet end method XMLPersisterTest
  }
  
  public persistence.XMLPersister createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new persistence.XMLPersister();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    xmlpersister = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    xmlpersister = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testParseFile() throws Exception {
    // JUnitDoclet begin method parseFile
    // JUnitDoclet end method parseFile
  }
  
  public void testAddRecord() throws Exception {
    // JUnitDoclet begin method addRecord
    // JUnitDoclet end method addRecord
  }
  
  public void testGetData() throws Exception {
    // JUnitDoclet begin method getData
    // JUnitDoclet end method getData
  }
  
  public void testWriteFile() throws Exception {
    // JUnitDoclet begin method writeFile
    // JUnitDoclet end method writeFile
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
    junit.textui.TestRunner.run(XMLPersisterTest.class);
    // JUnitDoclet end method testcase.main
  }
}
