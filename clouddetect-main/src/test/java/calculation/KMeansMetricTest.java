package calculation;

import calculation.KMeansMetric;
import calculation.Metric;
import junit.framework.TestCase;
import media.image.CloudImageResult;
import media.image.CloudImageResultImpl;

// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class KMeansMetricTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    calculation.KMeansMetric kmeansmetric = null;

    // JUnitDoclet end class

    public KMeansMetricTest(String name) {
        // JUnitDoclet begin method KMeansMetricTest
        super(name);
        // JUnitDoclet end method KMeansMetricTest
    }

    public calculation.KMeansMetric createInstance() throws Exception {
        // JUnitDoclet begin method testcase.createInstance
        return new calculation.KMeansMetric();
        // JUnitDoclet end method testcase.createInstance
    }

    protected void setUp() throws Exception {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        kmeansmetric = createInstance();
        // JUnitDoclet end method testcase.setUp
    }

    protected void tearDown() throws Exception {
        // JUnitDoclet begin method testcase.tearDown
        kmeansmetric = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testCompute() throws Exception {
        // JUnitDoclet begin method compute
        Metric kmeans = new KMeansMetric();
        double[] data = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 };
        CloudImageResult image = new CloudImageResultImpl(data, 5, 2, false);
        double result = kmeans.compute(image);
        assertEquals(1.0, result, 0.0);

        // JUnitDoclet end method compute
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
        junit.textui.TestRunner.run(KMeansMetricTest.class);
        // JUnitDoclet end method testcase.main
    }
}
