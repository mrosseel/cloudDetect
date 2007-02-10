package media.image.consumer;

import application.InstanceFactory;
import media.image.CloudImage;

public class UpdateContrastChartSubConsumer implements ImageSubConsumer {

    public void consume(CloudImage image) {
        if (image.getMetaData().getDate() == null) {
            InstanceFactory.getContrastChart().addValue(
                    image.getOriginComment(), image.getMetaData().getContrastResult());
        } else {
            InstanceFactory.getContrastChart().addValue(
                    image.getOriginComment(), image.getMetaData().getDate(),
                    image.getMetaData().getContrastResult());
        }

    }

}
