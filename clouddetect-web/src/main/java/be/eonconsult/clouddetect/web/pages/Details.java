package be.eonconsult.clouddetect.web.pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.ioc.annotations.Inject;

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
	
	private Map validTimes = createMap();

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
	private boolean shouldWarnWhenClear = false;

	@Persist
	private boolean shouldWarnWhenCloudy = false;

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
	
	@Persist
	private String startTime;

	
	/* TODO

	@Inject
	private ComponentResources resources;
	 
	public String getUrlFromClass() {
	     return resources.createActionLink("rpcTrigger",false).toString();
	}
	
	StreamResponse onRpcTrigger() {
	      //build your json object and wrapp it in a StreamResponse
	     return streamResponse;
	}
	 */	
	
	void onActivate(int id) {
		log.info("in activate with id " + id);
		this.id = id;
		this.feed = feedDao.getFeed(this.id);

		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		cloudStatusResult = CloudStatus.valueOf(result.getCloudJudgeResult());
		monitor.setDelayClear(delayClear);
		monitor.setDelayCloudy(delayCloudy);
		if(shouldWarnWhenClear) {
			isClearNotify = monitor.isClearNotify(cloudStatusResult, result.getTime());
		}
		if(shouldWarnWhenCloudy) {
			isCloudyNotify = monitor.isCloudyNotify(cloudStatusResult, result.getTime());
		}
		
		isClearNotify = true;
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
	
	Map createMap() {
		Map map = new HashMap();
		map.put("19:00", "negniet uur");
		map.put("19:01", "negniet1 uur");
		map.put("19:02", "negniet2 uur");
		map.put("19:03", "negniet3 uur");
		map.put("19:04", "negniet4 uur");
		return map;
		
	}

	public boolean isClearWarning() {
		return shouldWarnWhenClear;
	}

	public void setClearWarning(boolean clearWarning) {
		this.shouldWarnWhenClear = clearWarning;
	}

	public boolean isCloudyWarning() {
		return shouldWarnWhenCloudy;
	}

	public void setCloudyWarning(boolean cloudyWarning) {
		this.shouldWarnWhenCloudy = cloudyWarning;
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

	public boolean isClearOrCloudyNotify() {
		return isCloudyNotify || isClearNotify;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Map getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(Map validTimes) {
		this.validTimes = validTimes;
	}

	
	

}
