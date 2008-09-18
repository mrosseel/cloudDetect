package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.util.TextStreamResponse;
import org.slf4j.Logger;

import persistence.dao.FeedDao;
import persistence.model.Feed;

public class FeedInfo {
	@Inject
	private FeedDao feedDao;

	@Inject
	private Logger log;

	public String getWebcamList(int feedID) {
		Feed feed = feedDao.getFeed(feedID);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", feedID);
		jsonObject.put("name", feed.getName());
		jsonObject.put("descr", feed.getLocationFreeForm());
		jsonObject.put("secondsbetweenupdates", feed.getSecondsBetweenUpdates());
		jsonObject.put("starthour", "12");
		jsonObject.put("startminute", "00");
		jsonObject.put("endhour", "13");
		jsonObject.put("endminute", "01");

		return jsonObject.toString();
	}

	public Object onActivate(int feedID) {
		String text = getWebcamList(feedID);

		return new TextStreamResponse("application/json", text);
	}
}
