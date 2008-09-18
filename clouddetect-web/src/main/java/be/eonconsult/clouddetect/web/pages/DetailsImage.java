package be.eonconsult.clouddetect.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;

import persistence.dao.ResultDao;
import persistence.model.Feed;
import persistence.model.Result;
import application.InstanceFactory;

public class DetailsImage {
    @Persist
    private int id;
    @Persist
    private Feed feed;
    
    void onActivate(int id, Feed feed)
    {
       this.id = id;
       this.feed = feed;
    }

    public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getContext() {
		List<Object> arrayList = new ArrayList<Object>(1);
		arrayList.add(id);
		return arrayList;
	}

	public String getCloudStatus() {
		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		return result.getCloudJudgeResult();
	}
}
