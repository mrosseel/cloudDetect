/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:34:16 PM
 * To change this template use Options | File Templates.
 */
package calculation;

import media.image.CloudImage;
import util.StandardDeviation;

public class StdDevMetric implements Metric {
	/**
	 * Computes standard deviation on the input data
	 *
	 * @param data
	 * @return
	 */
	public double compute(CloudImage image) {
		double[] data = image.getData();
		return StandardDeviation.sdFast(data);
	}
}
