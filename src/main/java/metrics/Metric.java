/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:23:59 PM
 * To change this template use Options | File Templates.
 */
package metrics;

import media.image.CloudImage;

public interface Metric {

    /**
     * Compute a certain metric on the given data.
     *
     * @param data
     * @return
     */
	public double compute(CloudImage data);
}
