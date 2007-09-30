package be.eonconsult.clouddetect.web.pages;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Persist;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.CloudJudgeLimits;
import persistence.model.Feed;
import persistence.model.Result;
import application.InstanceFactory;
import calculation.CloudStatusMonitor;
import calculation.CloudJudge.CloudStatus;

public class Details {
	private static Log log = LogFactory.getLog(Details.class);

	@Inject
	private FeedDao feedDao;

	@InjectPage
	private DetailsImage detailsImage;

	@InjectPage
	private Details details;

	@Persist
	private int id;

	@Persist
	private Feed feed;

	@Persist
	private boolean clearWarning;

	@Persist
	private boolean cloudyWarning;

	@Persist
	private int delayClear = 20;

	@Persist
	private int delayCloudy = 0;

	@Persist
	private CloudStatusMonitor clearMonitor;

	@Persist
	private CloudStatusMonitor cloudyMonitor;

	@Persist
	private CloudStatus cloudStatusResult;

	@Persist
	private boolean notify = false;

	void onActivate(int id) {
		log.info("in activate with id " + id);
		this.id = id;
		this.feed = feedDao.getFeed(this.id);

		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		cloudStatusResult = CloudStatus.valueOf(result.getCloudJudgeResult());
		if (clearMonitor == null || cloudyMonitor == null) {
			clearMonitor = new CloudStatusMonitor(CloudStatus.CLEAR, cloudStatusResult);
			cloudyMonitor = new CloudStatusMonitor(CloudStatus.CLOUDED, cloudStatusResult);
			log.debug("Creating new cloudStatusMonitor");
		}
		clearMonitor.setTransitionWaitInMinutes(delayClear);
		cloudyMonitor.setTransitionWaitInMinutes(delayCloudy);

		Date now = new Date();

		if (clearWarning) {
			this.notify = clearMonitor.checkIfNotify(cloudStatusResult, now);
		}
		if (cloudyWarning) {
			this.notify = this.notify || cloudyMonitor.checkIfNotify(cloudStatusResult, now);
		}
	}

	int onPassivate() {
		return id;
	}

	public CloudJudgeLimits getLimits() {
		return feed.getCloudJudgeLimits();
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public String getCloudStatus() {
		return cloudStatusResult.toString();
	}

	Object onActionFromCurrentImage() {

		detailsImage.setFeed(feed);
		detailsImage.setId(id);

		return detailsImage;
	}

	void onSubmitFromTransition() {
		// details.onActivate(id);
		// return details;

	}

	public boolean isClearWarning() {
		return clearWarning;
	}

	public void setClearWarning(boolean clearWarning) {
		this.clearWarning = clearWarning;
	}

	public boolean isCloudyWarning() {
		return cloudyWarning;
	}

	public void setCloudyWarning(boolean cloudyWarning) {
		this.cloudyWarning = cloudyWarning;
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

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	private CloudStatusMonitor getClearMonitor() {
		return clearMonitor;
	}

	private void setClearMonitor(CloudStatusMonitor clearMonitor) {
		this.clearMonitor = clearMonitor;
	}

	private CloudStatusMonitor getCloudyMonitor() {
		return cloudyMonitor;
	}

	private void setCloudyMonitor(CloudStatusMonitor cloudyMonitor) {
		this.cloudyMonitor = cloudyMonitor;
	}

}
