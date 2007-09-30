package media.image.consumer;

import media.image.CloudImage;
import notification.Notifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.CloudJudge;
import calculation.CloudStatusMonitor;
import calculation.CloudJudge.CloudStatus;

/**
 * Thin facade around the cloudstatusmonitor, which does the heavy lifting.
 * This class knows about images and notifiers, the monitor only about dates and results.
 * 
 * @author mike
 *
 */
public class CloudJudgeSubConsumer implements ImageSubConsumer {
	private static Log log = LogFactory.getLog(CloudJudgeSubConsumer.class);

	private Notifier notifier;

	private CloudStatusMonitor monitor;

	/**
	 * Standard ToStatus is set to CLEAR
	 * 
	 */
	public CloudJudgeSubConsumer() {
		this(CloudJudge.CloudStatus.CLEAR);
	}

	public CloudJudgeSubConsumer(CloudStatus toStatus) {
		super();
		monitor = new CloudStatusMonitor();
		setToStatus(toStatus);
	}

	public void consume(CloudImage image) {
		CloudStatus result = monitor.getCloudJudge().judgeClouds(image.getMetaData().getContrastResult());
		boolean shouldNotify = monitor.checkIfNotify(result, image.getMetaData().getDate());
		if (shouldNotify) {
			image.getMetaData().setNotify(true);
			notifier.notify(image);
		}
		// TODO do we need this, will always be the same as the
		// contrastresult...
		image.getMetaData().setCloudJudgeResult(result.toString());
	}

	public void setTransitionWaitInMinutes(int transitionWaitInMinutes) {
		this.monitor.setTransitionWaitInMinutes(transitionWaitInMinutes);
	}

	public void setToStatus(CloudStatus toStatus) {
		this.monitor.setToStatus(toStatus);
	}

	public void setCloudJudge(CloudJudge judge) {
		this.monitor.setCloudJudge(judge);
	}

	public Notifier getNotifier() {
		return notifier;
	}

	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}
}
