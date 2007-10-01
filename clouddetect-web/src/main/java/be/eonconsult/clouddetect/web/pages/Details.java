package be.eonconsult.clouddetect.web.pages;

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
import be.eonconsult.clouddetect.web.services.ClearCloudyMonitor;
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
	private boolean clearWarning = false;

	@Persist
	private boolean cloudyWarning = false;

	@Persist
	private int delayClear = 20;

	@Persist
	private int delayCloudy = 0;
	
	@Persist
	private ClearCloudyMonitor monitor = new ClearCloudyMonitor();


	@Persist
	private CloudStatus cloudStatusResult;

	@Persist
	private boolean isClearNotify = false;

	@Persist
	private boolean isCloudyNotify = false;

	
	void onActivate(int id) {
		log.info("in activate with id " + id);
		this.id = id;
		this.feed = feedDao.getFeed(this.id);

		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		cloudStatusResult = CloudStatus.valueOf(result.getCloudJudgeResult());
		monitor.setDelayClear(delayClear);
		monitor.setDelayCloudy(delayCloudy);
		isClearNotify = monitor.isClearNotify(cloudStatusResult);
		isCloudyNotify = monitor.isCloudyNotify(cloudStatusResult);
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

	public boolean isClearNotify() {
		return isClearNotify;
	}

	public void setClearNotify(boolean isClearNotify) {
		this.isClearNotify = isClearNotify;
	}

	public boolean isCloudyNotify() {
		return isCloudyNotify;
	}

	public void setCloudyNotify(boolean isCloudyNotify) {
		this.isCloudyNotify = isCloudyNotify;
	}



}
