package be.eonconsult.clouddetect.web.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.joda.time.DateTime;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.CloudJudgeLimits;
import persistence.model.Feed;
import persistence.model.Result;
import util.AstroUtil;
import util.DateUtil;
import util.RiseSetPair;
import application.InstanceFactory;
import be.eonconsult.clouddetect.web.components.GeneralBlocks;
import be.eonconsult.clouddetect.web.components.UserInfo;
import be.eonconsult.clouddetect.web.services.ClearCloudyMonitor;
import calculation.CloudJudge.CloudStatus;

public class Details extends EnhancedPage {
	private static Log log = LogFactory.getLog(Details.class);

	@Persist
	private List<String> validTimes;
	
	@Persist
	private Map<String, DateTime> validTimesMap;

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
	private boolean shouldWarnWhenClear;

	@Persist
	private boolean shouldWarnWhenCloudy;

	@Persist
	private int delayClear;

	@Persist
	private int delayCloudy;

	@Persist
	private ClearCloudyMonitor monitor;

	@Persist
	private String cloudStatusString;

	@Persist
	private boolean isClearNotify;

	@Persist
	private boolean isCloudyNotify;

	@Persist
	private String startTime;
	
	@Persist
	private String endTime;

	
	@Persist
	private int nrTimesToPlaySound;

	@Inject
	private ComponentResources resources;
	
	@Inject
	private Block sound;
	
	@Inject
	private Block userInfo;
	
	@Inject
	@Path("context:images/ajax-loader.gif")
	private Asset ajaxLoader;

	// maybe there's a phase in the rendering where we need to do this?
	void initPersistentFields() {
		delayClear = 20;
		delayCloudy = 20;
		nrTimesToPlaySound = 3;
		monitor = new ClearCloudyMonitor();
	}
	
	void onActivate(int id) {
		log.info("in activate with id " + id);
		initPersistentFields();
		this.id = id;
		this.feed = feedDao.getFeed(this.id);
		if(validTimesMap == null) {
			createTimeFrameMappings();
		}

		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		// no results so nothing else to check
		if (result == null) {
			cloudStatusString = "Feed is offline.";
			return;
		}
		
		CloudStatus cloudStatusResult = CloudStatus.valueOf(result.getCloudJudgeResult());
		monitor.setDelayClear(delayClear);
		monitor.setDelayCloudy(delayCloudy);
		
	    //	TODO use the user's timezone to override the feed timezone for within time frame calculations. Or not? No think not.
		//  If you want to look at nighttime graphics during daytime, that's your problem. Stay in the feed's timezone for everything.
		boolean isWithinTimeFrame = DateUtil.withinTimeFrame(validTimesMap.get(startTime),validTimesMap.get(endTime), new DateTime(result.getTime()));

		if(isWithinTimeFrame) {
			if (shouldWarnWhenClear) {
				isClearNotify = monitor.isClearNotify(cloudStatusResult, result.getTime());
			}
			if (shouldWarnWhenCloudy) {
				isCloudyNotify = monitor.isCloudyNotify(cloudStatusResult, result.getTime());
			}
			cloudStatusString = cloudStatusResult.toString();
		} else {
			cloudStatusString = "Only works at night...";
		}
//		isClearNotify = true;
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
		return cloudStatusString;
	}

	Object onActionFromCurrentImage() {
		detailsImage.setFeed(feed);
		detailsImage.setId(id);

		return detailsImage;
	}

	Block onActionFromTestSound() {
		
		return sound;
	}

	Block getUserInfo() {
		return GeneralBlocks.getUserInfo();
	}
	
	void onSubmitFromTransition() {
		// details.onActivate(id);
		// return details;

	}

	void createTimeFrameMappings() {
		Map<String,DateTime> map = new HashMap<String,DateTime>();
		List<String> list = new ArrayList<String>();
		// TODO use real lat, lon
		RiseSetPair pair = AstroUtil.getRiseSet(feed.getLatitude(), feed.getLongitude(), new DateTime());
		DateTime rise = pair.getRise();
		DateTime set = pair.getSet();
		DateTime hold;
		int nrSteps = (int) Math.round((1440 - set.getMinuteOfDay() + rise.getMinuteOfDay())/15.0); // easy way to calculate spanning 2 days
		String current;
		list.add(set.toString("H:mm"));
		map.put(set.toString("H:mm"), set);
		int adder = DateUtil.getDistanceToMultipleOf15(set);
		for (int counter = 0; counter < nrSteps; counter++) {
			hold = set.plusMinutes(15*counter+adder);
			current = hold.toString("H:mm");
			list.add(current);
			map.put(current, hold);
		}
		setEndTime(list.get(list.size()-1).toString());
		setStartTime(list.get(0).toString());
		this.validTimes = list;
		this.validTimesMap = map;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(List validTimes) {
		this.validTimes = validTimes;
	}

	public int getNrTimesToPlaySound() {
		return nrTimesToPlaySound;
	}

	public void setNrTimesToPlaySound(int nrTimesToPlaySound) {
		this.nrTimesToPlaySound = nrTimesToPlaySound;
	}

	public Asset getAjaxLoader() {
		return ajaxLoader;
	}
	
	public boolean isFeedAllowed() {
		return super.isFeedAllowed(id);
	}
}
