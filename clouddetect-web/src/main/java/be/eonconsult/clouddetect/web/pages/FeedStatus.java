package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.util.TextStreamResponse;
import org.slf4j.Logger;

import persistence.dao.ResultDao;
import persistence.model.Result;
import application.InstanceFactory;
import calculation.CloudJudge.CloudStatus;

public class FeedStatus {
	@Inject
	private Logger log;

	public String getFeedStatus(int feedID) {
		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(feedID);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", (result == null)?"Offline":CloudStatus.valueOf(result.getCloudJudgeResult()).toString());
		jsonObject.put("time", (result == null)?"":result.getTime().toString());

		return jsonObject.toString();
	}

	public Object onActivate(int feedID) {
		String text = getFeedStatus(feedID);

		return new TextStreamResponse("application/json", text);
	}
}
