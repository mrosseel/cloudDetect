package media.image.consumer;

import media.image.CloudImageResult;
import media.image.CloudResult;
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
public class JudgeImageSubConsumer implements SubConsumer<CloudImageResult> {
	private static Log log = LogFactory.getLog(JudgeImageSubConsumer.class);

	private Notifier<CloudResult> notifier;

	private CloudStatusMonitor monitor;
	
	private CloudJudge judge;

	/**
	 * Standard ToStatus is set to CLEAR
	 * 
	 */
	public JudgeImageSubConsumer() {
		this(CloudJudge.CloudStatus.CLEAR);
	}

	public JudgeImageSubConsumer(CloudStatus toStatus) {
		super();
		monitor = new CloudStatusMonitor(toStatus);
		setToStatus(toStatus);
	}


	@Override
	public void consume(CloudImageResult content) {
		CloudStatus result = judge.judgeClouds(content.getMetaData().getResult());
		boolean shouldNotify = monitor.checkIfNotify(result, content.getMetaData().getDate());
		if (shouldNotify) {
			content.getMetaData().setNotify(true);
			notifier.notify(content);
		}
		// TODO do we need this, will always be the same as the
		// contrastresult...
		content.getMetaData().setCloudJudgeResult(result.toString());
	}

	public void setTransitionWaitInMinutes(int transitionWaitInMinutes) {
		this.monitor.setTransitionWaitInMinutes(transitionWaitInMinutes);
	}

	public void setToStatus(CloudStatus toStatus) {
		this.monitor.setToStatus(toStatus);
	}

	public void setCloudJudge(CloudJudge judge) {
		this.judge = judge;
	}
	
	public CloudJudge getJudge() {
		return judge;
	}

	public Notifier<CloudResult> getNotifier() {
		return notifier;
	}

	public void setNotifier(Notifier<CloudResult> notifier) {
		this.notifier = notifier;
	}

}
