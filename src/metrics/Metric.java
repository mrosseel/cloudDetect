/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:23:59 PM
 * To change this template use Options | File Templates.
 */
package metrics;

public interface Metric {

    /**
     * Compute a certain metric on the given data.
     *
     * @param data
     * @return
     */
    public double compute(short[] data);
}
