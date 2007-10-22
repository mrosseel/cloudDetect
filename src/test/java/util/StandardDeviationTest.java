package util;

import junit.framework.TestCase;

// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class StandardDeviationTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    util.StandardDeviation standarddeviation = null;

    // JUnitDoclet end class

    public StandardDeviationTest(String name) {
        // JUnitDoclet begin method StandardDeviationTest
        super(name);
        // JUnitDoclet end method StandardDeviationTest
    }

    public util.StandardDeviation createInstance() throws Exception {
        // JUnitDoclet begin method testcase.createInstance
        return new util.StandardDeviation();
        // JUnitDoclet end method testcase.createInstance
    }

    protected void setUp() throws Exception {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        standarddeviation = createInstance();
        // JUnitDoclet end method testcase.setUp
    }

    protected void tearDown() throws Exception {
        // JUnitDoclet begin method testcase.tearDown
        standarddeviation = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testSdFast() throws Exception {
        // JUnitDoclet begin method sdFast
        double[] data = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1 };
        short[] dataShort = new short[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1 };
        assertEquals(StandardDeviation.sdFast(data), StandardDeviation
                .sdFast(dataShort), 0.0);
        // JUnitDoclet end method sdFast
    }

    public void testSdKnuth() throws Exception {
    // JUnitDoclet begin method sdKnuth
    // JUnitDoclet end method sdKnuth
    }

    public void testMain() throws Exception {
    // JUnitDoclet begin method main
    // JUnitDoclet end method main
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
        junit.textui.TestRunner.run(StandardDeviationTest.class);
        // JUnitDoclet end method testcase.main
    }
}
