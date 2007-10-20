package media.image.consumer;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;
import calculation.CloudJudge.CloudStatus;

public class CloudJudgeSubConsumer implements ImageSubConsumer {
    private CloudStatus lastResult;
    private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);

    public void consume(CloudImage image) {
        CloudStatus result = InstanceFactory.getCloudJudge().judgeClouds(image.getMetaData().getContrastResult());
        if(result != lastResult) {
        log.info(result);
        lastResult = result;
        }
    }
}
