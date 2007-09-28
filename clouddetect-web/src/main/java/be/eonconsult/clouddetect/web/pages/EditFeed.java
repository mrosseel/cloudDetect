package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.OnEvent;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.internal.structure.Page;

import persistence.dao.FeedDao;
import persistence.model.Feed;

public class EditFeed extends ProtectedPage {

	@Persist
	private long feedId;
	
    @Inject
    private FeedDao feedDao;
    
    @InjectPage
    private EditFeed editFeed;

    @Persist
	private Feed feed;
    
    void onActivate(int id)
    {
       this.feedId = id;
    }
    
    
    @OnEvent(value="prepare")
    void initializeFeed() {
    	if(feedId != 0) {
    		setFeed(feedDao.getFeed(feedId));	
    	} else {
    		setFeed(new Feed());
    	}
    }

    @OnEvent(value="success")
	public Object saveResults() {
		feedDao.saveResult(feed);
		editFeed.setFeedId(feed.getId());
		return editFeed;
	}
	
	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}


	public long getFeedId() {
		return feedId;
	}


	public void setFeedId(long l) {
		this.feedId = l;
	}

	
	
	
}
