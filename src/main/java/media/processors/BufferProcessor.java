/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;

import media.image.CloudImage;


public interface BufferProcessor{

    /**
     *
     * @param data frame data
     */
    public void process(CloudImage data);
}
