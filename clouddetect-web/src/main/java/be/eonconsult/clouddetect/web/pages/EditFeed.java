package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.OnEvent;
import org.apache.tapestry.annotations.Parameter;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.annotations.SetupRender;

import persistence.dao.FeedDao;
import persistence.model.Feed;

public class EditFeed {

	@Persist
	private int feedId;
	
    @Inject
    private FeedDao feedDao;

    @Persist
	private Feed feed;
    
    void onActivate(int id)
    {
       this.feedId = id;
    }
    
    @OnEvent(value="prepare")
    void initializeFeed() {
    	setFeed(feedDao.getFeed(feedId));
    }

    @OnEvent(value="success")
	public void saveResults() {
		feedDao.saveResult(feed);
	}
	
	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	
	
}
