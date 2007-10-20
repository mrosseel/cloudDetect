/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 24, 2003
 * Time: 8:18:01 PM
 * To change this template use Options | File Templates.
 */
package calculation;

import util.KMeans;

import java.util.Arrays;

import media.image.CloudImage;


public class KMeansMetric implements Metric {

    /**
     * Returns the difference between the average of the lightest group of
     * pixels and the darkest group of pixels.
     *
     * @param data
     * @return abs(avg cluster1 - avg cluster2)
     */
    public double compute(CloudImage image) {
    	double[] data = image.getMonochromeData();
        Arrays.sort(data);
        KMeans kmeans = new KMeans(data, 2);
        double[] means = kmeans.getBinMeans();
        return Math.abs(means[0] - means[1]);
    }
}
