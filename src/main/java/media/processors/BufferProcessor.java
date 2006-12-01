/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;

import media.image.CloudImage;
import metrics.Metric;


public interface BufferProcessor{

    /**
     *
     * @param data frame data
     */
    public void process(CloudImage data);
    
    public Metric getMetric();

	public void setMetric(Metric metric);
}
