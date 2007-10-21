package media.image.consumer;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;
import calculation.CloudJudge.CloudStatus;

public class CloudJudgeSubConsumer implements ImageSubConsumer {
    private CloudStatus lastResult;

    private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);
    private boolean isWaitingForClear = false;
    private long transitionTime;
    private int transitionWaitInSeconds = 300;

    public void consume(CloudImage image) {
        CloudStatus result = InstanceFactory.getCloudJudge().judgeClouds(
                image.getMetaData().getContrastResult());
        if(isWaitingForClear && result == CloudStatus.CLEAR && isTransitionWaitOver(image)) {
            log.info("Mailing to Dodi!");
            this.isWaitingForClear = false;
        }
        
        if (result != lastResult) {
            if(result == CloudStatus.CLEAR) {
                isWaitingForClear = true;
                recordClearTransition(image);
            } else {
                isWaitingForClear = false;
            }
            log.info(result);
            lastResult = result;
        }
    }
    
    
    public int getTransitionWaitInSeconds() {
        return transitionWaitInSeconds;
    }


    public void setTransitionWaitInSeconds(int transitionWaitInSeconds) {
        this.transitionWaitInSeconds = transitionWaitInSeconds;
    }

    private boolean isTransitionWaitOver(CloudImage image) {
        return (image.getMetaData().getDate().getTime() - transitionTime) > this.transitionWaitInSeconds*1000;
    }


    private void recordClearTransition(CloudImage image) {
        transitionTime = image.getMetaData().getDate().getTime();
    }
}
