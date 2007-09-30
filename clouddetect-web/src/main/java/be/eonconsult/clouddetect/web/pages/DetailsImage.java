package be.eonconsult.clouddetect.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Persist;

import application.InstanceFactory;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.Feed;
import persistence.model.Result;

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
