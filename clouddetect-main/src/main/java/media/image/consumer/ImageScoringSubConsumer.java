package media.image.consumer;

import media.image.CloudImageResult;
import media.processors.CalculateMetricOnCloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;

/**
 * Class ImageScoringSubConsumer
 * 
 * Give a score to the image and store the result in the metadata.
 * 
 */
public class ImageScoringSubConsumer implements SubConsumer<CloudImageResult> {
    private static Log log = LogFactory.getLog(ImageScoringSubConsumer.class);

    private CalculateMetricOnCloudImage metricOnCloudImage;
    
    public void consume(CloudImageResult image) {
        double result = metricOnCloudImage.process(image);
        image.getMetaData().setResult(result);
    }

	public CalculateMetricOnCloudImage getMetricOnCloudImage() {
		return metricOnCloudImage;
	}

	public void setMetricOnCloudImage(CalculateMetricOnCloudImage metricOnCloudImage) {
		this.metricOnCloudImage = metricOnCloudImage;
	}
    
    
}
