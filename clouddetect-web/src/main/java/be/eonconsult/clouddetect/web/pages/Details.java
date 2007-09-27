package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Persist;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.CloudJudgeLimits;
import persistence.model.Feed;
import persistence.model.Result;
import application.InstanceFactory;

public class Details {
    @Inject
    private FeedDao feedDao;
    
    @Persist
    private int id;
    @Persist
    private Feed feed;
    @InjectPage
    private DetailsImage detailsImage;
    
    void onActivate(int id)
    {
       this.id = id;
       this.feed = feedDao.getFeed(this.id);
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
		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
		return result.getCloudJudgeResult();
	}
	
	
	 Object onAction()
	  {

	    detailsImage.setFeed(feed);
	    detailsImage.setId(id);

	    return detailsImage;
	  }
}
