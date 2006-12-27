/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;

import media.image.CloudImage;
import metrics.Metric;


public interface CalculateMetricOnCloudImage{

    /**
     *
     * @param data frame data
     */
    public double process(CloudImage data);
    
    public Metric getMetric();

	public void setMetric(Metric metric);
}
