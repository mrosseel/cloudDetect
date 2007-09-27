/**
 * One implementation of how to process a buffer 
 */
package media.processors;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.Metric;

public class CalculateMetricOnCloudImageImpl implements
        CalculateMetricOnCloudImage {

    private static Log log = LogFactory
            .getLog(CalculateMetricOnCloudImageImpl.class);

    private Metric metric;

    public double process(CloudImage image) {

        if (log.isDebugEnabled()) {
            log.debug("Processing data.");
        }

        double result = metric.compute(image);
        if (log.isDebugEnabled()) {
            log.debug("result = " + result);
        }

        return result;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        if (log.isDebugEnabled()) {
            log.debug("Setting metric to " + metric.toString());
        }
        this.metric = metric;
    }
}
