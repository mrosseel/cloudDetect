package media.image.consumer;

import media.image.CloudImage;
import media.processors.CalculateMetricOnCloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;

/**
 * Class ImageScoringSubConsumer
 * 
 */
public class ImageScoringSubConsumer implements ImageSubConsumer {
    private static Log log = LogFactory.getLog(ImageScoringSubConsumer.class);

    public void consume(CloudImage image) {
        // TODO inject it or something
        CalculateMetricOnCloudImage proc = InstanceFactory
                .getCalculateMetricOnCloudImage();
        double result = proc.process(image);
        image.getMetaData().setContrastResult(result);

        // TODO naughty naughty very naughty
        if (image.getMetaData().getDate() == null) {
            InstanceFactory.getContrastChart().addValue(
                    image.getOriginComment(), result);
        } else {
            InstanceFactory.getContrastChart().addValue(
                    image.getOriginComment(), image.getMetaData().getDate(),
                    result);
        }
    }
}
