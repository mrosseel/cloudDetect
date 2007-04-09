package notification;

import media.image.CloudImage;

public class UnitTestNotify implements Notifier {
	private boolean notified = false;
	
	public void notify(CloudImage image) {
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
