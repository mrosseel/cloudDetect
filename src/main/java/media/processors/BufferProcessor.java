/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;


public interface BufferProcessor extends Runnable {

    /**
     *
     * @param data frame data
     */
    public void process(double[] data);
}
