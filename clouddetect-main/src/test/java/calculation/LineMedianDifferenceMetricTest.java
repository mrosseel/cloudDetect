package calculation;

import junit.framework.TestCase;
import media.image.CloudImageResult;
import media.image.CloudImageResultImpl;
import media.image.producer.FileClasspathImageProducer;

// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class LineMedianDifferenceMetricTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    calculation.LineMedianDifferenceMetric linemediandifferencemetric = null;

    // JUnitDoclet end class

    public LineMedianDifferenceMetricTest(String name) {
        // JUnitDoclet begin method LineMedianDifferenceMetricTest
        super(name);
        // JUnitDoclet end method LineMedianDifferenceMetricTest
    }

    public calculation.LineMedianDifferenceMetric createInstance()
            throws Exception {
        // JUnitDoclet begin method testcase.createInstance
        return new calculation.LineMedianDifferenceMetric();
        // JUnitDoclet end method testcase.createInstance
    }

    protected void setUp() throws Exception {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        linemediandifferencemetric = createInstance();
        // JUnitDoclet end method testcase.setUp
    }

    protected void tearDown() throws Exception {
        // JUnitDoclet begin method testcase.tearDown
        linemediandifferencemetric = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testCompute() throws Exception {
        // JUnitDoclet begin method compute
        int[] data = { 0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 100, 100, 100,
                100, 100, 100, 100, 100 };
        Metric metric = new LineMedianDifferenceMetric();
        CloudImageResult image = new CloudImageResultImpl(data, 4, 5, false);
        assertEquals(100.0, metric.compute(image), 0.0);

        FileClasspathImageProducer producer = new FileClasspathImageProducer(
                "unit test", "/current_min_daylight.jpg");
        image = producer.produceContent();
        assertEquals(83.25098039215688, metric.compute(image), 0.0);

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
        junit.textui.TestRunner.run(LineMedianDifferenceMetricTest.class);
        // JUnitDoclet end method testcase.main
    }
}
