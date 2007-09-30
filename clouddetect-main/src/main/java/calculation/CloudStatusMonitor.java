package calculation;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.CloudJudge.CloudStatus;

import media.image.consumer.CloudJudgeSubConsumer;

public class CloudStatusMonitor {
	    private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);
	    
	    private CloudStatus toStatus = null;
	    
	    private CloudStatus lastResult = null;

	    private boolean isWaitingForTransitionTimeout = false;

	    private long transitionTime;

	    private int transitionWaitInMinutes = 0;
	    
	    private CloudJudge cloudJudge;
	    
	    /**
	     * Standard ToStatus is set to CLEAR
	     *
	     */
	    public CloudStatusMonitor() {
			this(CloudJudge.CloudStatus.CLEAR);
		}
	    
	    public CloudStatusMonitor(CloudStatus toStatus) {
			setToStatus(toStatus);
		}


	    public boolean checkIfNotify(CloudStatus result, Date resultDate) {
			boolean shouldNotify = false;
			  if (isWaitingForTransitionTimeout && result == toStatus
		                && isTransitionWaitOver(resultDate)) {
		            shouldNotify = true;
		            this.isWaitingForTransitionTimeout = false;
		        }

		        if (result != lastResult) {
		            if (result == toStatus) {
		                isWaitingForTransitionTimeout = true;
		                recordTransition(resultDate);
		            } else {
		                isWaitingForTransitionTimeout = false;
		            }
		            log.info(result);
		            lastResult = result;
		        }
		      return shouldNotify;
		}
	    
	    private boolean isTransitionWaitOver(Date date) {
	        return (date.getTime() - transitionTime) > this.transitionWaitInMinutes * 60000;
	    }

	    private void recordTransition(Date date) {
	        transitionTime = date.getTime();
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
	    
	    
	}
