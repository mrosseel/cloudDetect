package calculation.splitters;

import junit.framework.TestCase;
import media.image.CloudImage;
import media.image.CloudImageImpl;
import media.image.producer.FileClasspathImageProducer;
import util.VisualiseSplitterLocation;
import calculation.splitters.splittermetric.HorizonMetric;

// JUnitDoclet end import

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */

public class BisectionSplitterTest
// JUnitDoclet begin extends_implements
        extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    BisectionSplitter bisectionSplitter = null;

    // JUnitDoclet end class

    public BisectionSplitterTest(String name) {
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
        bisectionSplitter = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    public void testCompute() {
        double[] data = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1 };
        CloudImage image = new CloudImageImpl(data, 10, 2);

        // FileClasspathImageProducer producer = new FileClasspathImageProducer(
        // "unit test", "/01-20061009223010-snapshot.jpg");
        FileClasspathImageProducer producer = new FileClasspathImageProducer(
                "unit test", "/01-20061013203310-snapshot.jpg");

        image = producer.produceContent();
        bisectionSplitter = new BisectionSplitter();
        bisectionSplitter.setSplitterMetric(new HorizonMetric());
        bisectionSplitter.split(image);
        // assertEquals(9, bisectionSplitter.getBestSplitterLocation());x
        System.out.println("best splittetr location "
                + bisectionSplitter.getBestSplitterLocation());
        VisualiseSplitterLocation.showImage(image, bisectionSplitter
                .getBestSplitterLocation());

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
        junit.textui.TestRunner.run(BisectionSplitterTest.class);
        // JUnitDoclet end method testcase.main
    }
}
