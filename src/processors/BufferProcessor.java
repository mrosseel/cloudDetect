/**
 * Takes an image as input and calculates metrics.
 * 
 */
package processors;


public interface BufferProcessor extends Runnable {

    /**
     *
     * @param data frame data
     */
    public void process(double[] data);
}
