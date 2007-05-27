package calculation.splitters;

import junit.framework.TestCase;
import media.image.CloudImage;
import media.image.CloudImageImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.splitters.splittermetric.HorizonMetric;

// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class StupidSplitterTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    StupidImageSplitter stupidSplitter = null;

    private static Log log = LogFactory.getLog(StupidSplitterTest.class);

    // JUnitDoclet end class

    public StupidSplitterTest(String name) {
        // JUnitDoclet begin method MaxStdDevMetricTest
        super(name);
        // JUnitDoclet end method MaxStdDevMetricTest
    }

    protected void setUp() throws Exception {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        // JUnitDoclet end method testcase.setUp
    }

    protected void tearDown() throws Exception {
        // JUnitDoclet begin method testcase.tearDown
        stupidSplitter = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testCompute() {
        double[] data = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1 };
        CloudImage image = new CloudImageImpl(data, 10, 2, false);
        split(image);
        log.info("best splitter location = "
                + stupidSplitter.getBestSplitterLocation());
        assertEquals(9, stupidSplitter.getBestSplitterLocation());

        // FileClasspathImageProducer producer = new FileClasspathImageProducer(
        // "unit test", "/current_min.jpg");
        // image = producer.produceContent();
        // split(image);
        // VisualiseSplitterLocation.showImage(image,
        // stupidSplitter.getBestSplitterLocation());

    }

    private void split(CloudImage image) {
        stupidSplitter = new StupidImageSplitter();
        stupidSplitter.setSplitterMetric(new HorizonMetric());
        stupidSplitter.split(image);
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
        junit.textui.TestRunner.run(StupidSplitterTest.class);
        // JUnitDoclet end method testcase.main
    }
}
