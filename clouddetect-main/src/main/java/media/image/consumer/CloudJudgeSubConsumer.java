package media.image.consumer;

import media.image.CloudImage;
import notification.Notifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.CloudJudge;
import calculation.CloudJudge.CloudStatus;

public class CloudJudgeSubConsumer implements ImageSubConsumer {
    private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);
    
    private CloudStatus toStatus = null;
    
    private CloudStatus lastResult = null;

    private boolean isWaitingForTransitionTimeout = false;

    private long transitionTime;

    private int transitionWaitInMinutes = 0
    ;
    
    private CloudJudge cloudJudge;
    
    private Notifier notifier;
    
    /**
     * Standard ToStatus is set to CLEAR
     *
     */
    public CloudJudgeSubConsumer() {
		this(CloudJudge.CloudStatus.CLEAR);
	}
    
    public CloudJudgeSubConsumer(CloudStatus toStatus) {
		super();
		setToStatus(toStatus);
	}

	public void consume(CloudImage image) {
        CloudStatus result = cloudJudge.judgeClouds(
                image.getMetaData().getContrastResult());
        if (isWaitingForTransitionTimeout && result == toStatus
                && isTransitionWaitOver(image)) {
            
            image.getMetaData().setNotify(true);
            notifier.notify(image);
            
            this.isWaitingForTransitionTimeout = false;
        }

        if (result != lastResult) {
            if (result == toStatus) {
                isWaitingForTransitionTimeout = true;
                recordTransition(image);
            } else {
                isWaitingForTransitionTimeout = false;
            }
            log.info(result);
            lastResult = result;
        }
    }

    private boolean isTransitionWaitOver(CloudImage image) {
        return (image.getMetaData().getDate().getTime() - transitionTime) > this.transitionWaitInMinutes * 60000;
    }

    private void recordTransition(CloudImage image) {
        transitionTime = image.getMetaData().getDate().getTime();
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
  
	public CloudStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(CloudStatus toStatus) {
		this.toStatus = toStatus;
	}

	public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }
}
