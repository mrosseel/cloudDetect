/**
 * Takes an image as input and calculates metrics.
 * 
 */
package media.processors;

import calculation.Metric;
import media.image.CloudImageResult;

/**
 * Calculates a certain metric on a CloudImage, and returns the result.
 * 
 * @author mike
 */
public interface CalculateMetricOnCloudImage {

    /**
     * 
     * @param data
     *            frame data
     */
    public double process(CloudImageResult data);

    public Metric getMetric();

    public void setMetric(Metric metric);
}
