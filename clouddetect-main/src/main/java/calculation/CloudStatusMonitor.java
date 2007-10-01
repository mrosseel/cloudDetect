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

	/**
	 * Standard ToStatus is set to CLEAR
	 * 
	 */
	public CloudStatusMonitor(CloudStatus toStatus) {
		this(toStatus, null);
	}

	/**
	 * 
	 * @param toStatus
	 *            the status we want to be notified of if it occurs
	 * @param currentStatus
	 *            the current status, if it's clear when we start monitoring, we
	 *            don't want to be notified of that
	 */
	public CloudStatusMonitor(CloudStatus toStatus, CloudStatus currentStatus) {
		setToStatus(toStatus);
		lastResult = currentStatus;
	}

	public boolean checkIfNotify(CloudStatus result, Date resultDate) {
		boolean shouldNotify = false;

		if (result == toStatus) {
			if (result != lastResult) {
				isWaitingForTransitionTimeout = true;
				recordTransition(resultDate);
			}
			
			if (isWaitingForTransitionTimeout && isTransitionWaitOver(resultDate)) {
				shouldNotify = true;
				this.isWaitingForTransitionTimeout = false;
			}
		}

		lastResult = result;

		return shouldNotify;
	}

	private boolean isTransitionWaitOver(Date date) {
		return (date.getTime() - transitionTime) >= this.transitionWaitInMinutes * 60000;
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

	public CloudStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(CloudStatus toStatus) {
		this.toStatus = toStatus;
	}
}
