package notification;

import media.image.CloudResult;

public class UnitTestNotify implements Notifier<CloudResult> {
	private boolean notified = false;
	
	public void notify(CloudResult image) {
		setNotified(true);
	}

	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}
	
	public void reset() {
		setNotified(false);
	}
}
