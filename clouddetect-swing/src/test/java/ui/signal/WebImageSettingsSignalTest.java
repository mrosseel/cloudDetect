package ui.signal;

import junit.framework.TestCase;

// JUnitDoclet begin import
// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class WebImageSettingsSignalTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    ui.signal.WebImageSettingsSignal webimagesettingssignal = null;

    // JUnitDoclet end class

    public WebImageSettingsSignalTest(String name) {
        // JUnitDoclet begin method WebImageSettingsSignalTest
        super(name);
        // JUnitDoclet end method WebImageSettingsSignalTest
    }

    public ui.signal.WebImageSettingsSignal createInstance() throws Exception {
        // JUnitDoclet begin method testcase.createInstance
        return new ui.signal.WebImageSettingsSignal(null);
        // JUnitDoclet end method testcase.createInstance
    }

    protected void setUp() throws Exception {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        webimagesettingssignal = createInstance();
        // JUnitDoclet end method testcase.setUp
    }

    protected void tearDown() throws Exception {
        // JUnitDoclet begin method testcase.tearDown
        webimagesettingssignal = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testGetImage() throws Exception {
    // JUnitDoclet begin method getImage
    // JUnitDoclet end method getImage
    }

    /**
     * JUnitDoclet moves marker to this method, if there is not match for them
     * in the regenerated code and if the marker is not empty. This way, no test
     * gets lost when regenerating after renaming. Method testVault is supposed
     * to be empty.
     */
    public void testVault() throws Exception {
    // JUnitDoclet begin method testcase.testVault
    // JUnitDoclet end method testcase.testVault
    }

    public static void main(String[] args) {
        // JUnitDoclet begin method testcase.main
        junit.textui.TestRunner.run(WebImageSettingsSignalTest.class);
        // JUnitDoclet end method testcase.main
    }
}
