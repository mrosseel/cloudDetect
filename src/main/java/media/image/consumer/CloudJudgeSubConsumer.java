package media.image.consumer;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;
import calculation.CloudJudge;
import calculation.CloudJudge.CloudStatus;

public class CloudJudgeSubConsumer implements ImageSubConsumer {
    private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);
    
    private CloudStatus lastResult;

    private boolean isWaitingForClear = false;

    private long transitionTime;

    private int transitionWaitInMinutes = 60;
    
    private CloudJudge cloudJudge;

    public void consume(CloudImage image) {
        CloudStatus result = cloudJudge.judgeClouds(
                image.getMetaData().getContrastResult());
        if (isWaitingForClear && result == CloudStatus.CLEAR
                && isTransitionWaitOver(image)) {
            log.info("Mailing to Dodi!");
            InstanceFactory.getMailNotify().sendMail(transitionWaitInMinutes);
            this.isWaitingForClear = false;
        }

        if (result != lastResult) {
            if (result == CloudStatus.CLEAR) {
                isWaitingForClear = true;
                recordClearTransition(image);
            } else {
                isWaitingForClear = false;
            }
            log.info(result);
            lastResult = result;
        }
    }

    public int getTransitionWaitInMinutes() {
        return transitionWaitInMinutes;
    }

    public void setTransitionWaitInMinutes(int transitionWaitInMinutes) {
        this.transitionWaitInMinutes = transitionWaitInMinutes;
    }
    
    

    public CloudJudge getCloudJudge() {
        return cloudJudge;
    }

    public void setCloudJudge(CloudJudge judge) {
        this.cloudJudge = judge;
    }

    private boolean isTransitionWaitOver(CloudImage image) {
        return (image.getMetaData().getDate().getTime() - transitionTime) > this.transitionWaitInMinutes * 60000;
    }

    private void recordClearTransition(CloudImage image) {
        transitionTime = image.getMetaData().getDate().getTime();
    }
}
