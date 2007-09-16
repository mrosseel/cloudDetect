/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;

import calculation.Metric;
import media.image.CloudImage;

/**
 * Calculates a certain metric on a CloudImage, and returns the result.
 * 
 * @author mike
 */
public interface CalculateMetricOnCloudImage{

    /**
     *
     * @param data frame data
     */
    public double process(CloudImage data);
    
    public Metric getMetric();

	public void setMetric(Metric metric);
}