/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 26, 2003
 * Time: 12:20:18 AM
 * To change this template use Options | File Templates.
 */
package calculation;

import java.util.Arrays;

import calculation.Metric;

import media.image.CloudImageResult;
import util.StandardDeviation;

public class StdDevClusteringMetric implements Metric {

    public double compute(CloudImageResult image) {
        double[] data = image.getData();
        double totalStdDev, firstStdDev, secondStdDev;
        double minStdDev = Double.POSITIVE_INFINITY;
        double metric = -1;

        System.out.println("beginning stdevclustering");
        Arrays.sort(data);
        System.out.println("after sort");

        for (int i = 2; i != data.length - 1; i++) {
            firstStdDev = StandardDeviation.sdFast(data, 0, i);
            secondStdDev = StandardDeviation.sdFast(data, i, data.length);
            totalStdDev = firstStdDev + secondStdDev;
            if (totalStdDev < minStdDev) {
                minStdDev = totalStdDev;
                metric = Math.abs(firstStdDev - secondStdDev);
            }
        }

        return metric;
    }
}
