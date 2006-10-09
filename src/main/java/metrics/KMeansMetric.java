/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 24, 2003
 * Time: 8:18:01 PM
 * To change this template use Options | File Templates.
 */
package metrics;

import util.KMeans;

import java.util.Arrays;


public class KMeansMetric implements Metric {

    /**
     * Returns the difference between the average of the lightest group of
     * pixels and the darkest group of pixels.
     *
     * @param data
     * @return abs(avg cluster1 - avg cluster2)
     */
    public double compute(short[] data) {
        Arrays.sort(data);
        System.out.println("sorted!");
        KMeans kmeans = new KMeans(data, 2);
        double[] means = kmeans.getBinMeans();
        int[] clusterSizes = kmeans.getBinSizes();
        return Math.abs(clusterSizes[0] - clusterSizes[1]);
    }
    
    /**
     * TODO : IMPLEMENT USING DOUBLES
     */
	public double compute(double[] data) {
		return -1;
	}
}
