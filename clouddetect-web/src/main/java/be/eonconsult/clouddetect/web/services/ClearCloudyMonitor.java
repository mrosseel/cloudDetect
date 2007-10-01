package be.eonconsult.clouddetect.web.services;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import calculation.CloudStatusMonitor;
import calculation.CloudJudge.CloudStatus;

public class ClearCloudyMonitor {
	private static Log log = LogFactory.getLog(ClearCloudyMonitor.class);

	private CloudStatusMonitor clearMonitor;

	private CloudStatusMonitor cloudyMonitor;

	private int delayClear;

	private int delayCloudy;

	public boolean isClearNotify(CloudStatus cloudStatusResult) {
		setupMonitors(cloudStatusResult);
		Date now = new Date();

		return clearMonitor.checkIfNotify(cloudStatusResult, now);
	}

	public boolean isCloudyNotify(CloudStatus cloudStatusResult) {
		setupMonitors(cloudStatusResult);
		Date now = new Date();

		return cloudyMonitor.checkIfNotify(cloudStatusResult, now);
	}

	public int getDelayClear() {
		return delayClear;
	}

	public void setDelayClear(int delayClear) {
		this.delayClear = delayClear;
	}

	public int getDelayCloudy() {
		return delayCloudy;
	}

	public void setDelayCloudy(int delayCloudy) {
		this.delayCloudy = delayCloudy;
	}

	private void setupMonitors(CloudStatus cloudStatusResult) {
		if (clearMonitor == null || cloudyMonitor == null) {
			clearMonitor = new CloudStatusMonitor(CloudStatus.CLEAR, cloudStatusResult);
			cloudyMonitor = new CloudStatusMonitor(CloudStatus.CLOUDED, cloudStatusResult);
			log.debug("Creating new cloudStatusMonitor");
		}
		clearMonitor.setTransitionWaitInMinutes(getDelayClear());
		cloudyMonitor.setTransitionWaitInMinutes(getDelayCloudy());
	}

}
