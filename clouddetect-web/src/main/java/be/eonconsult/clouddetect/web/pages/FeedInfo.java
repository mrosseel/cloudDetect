package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.util.TextStreamResponse;
import org.joda.time.DateTime;
import org.slf4j.Logger;

import persistence.dao.FeedDao;
import persistence.model.Feed;
import util.AstroUtil;
import util.RiseSetPair;

public class FeedInfo {
	@Inject
	private FeedDao feedDao;

	@Inject
	private Logger log;

	public String getWebcamList(int feedID) {
		Feed feed = feedDao.getFeed(feedID);
		RiseSetPair pair = AstroUtil.getRiseSet(feed.getLatitude(), feed.getLongitude(), new DateTime());

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", feedID);
		jsonObject.put("name", feed.getName());
		jsonObject.put("descr", feed.getLocationFreeForm());
		jsonObject.put("secondsbetweenupdates", feed.getSecondsBetweenUpdates());
		jsonObject.put("starthour", pair.getSet().getHourOfDay());
		jsonObject.put("startminute", pair.getSet().getMinuteOfHour());
		jsonObject.put("endhour", pair.getRise().getHourOfDay());
		jsonObject.put("endminute", pair.getRise().getMinuteOfHour());

		
		return jsonObject.toString();
	}

	public Object onActivate(int feedID) {
		String text = getWebcamList(feedID);

		return new TextStreamResponse("application/json", text);
	}
}
