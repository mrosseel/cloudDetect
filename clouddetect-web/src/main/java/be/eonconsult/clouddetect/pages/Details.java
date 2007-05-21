package be.eonconsult.clouddetect.pages;

import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.ioc.internal.util.ClasspathResource;
import org.apache.tapestry.spring.SpringBean;

import persistence.dao.FeedDao;
import persistence.model.Feed;

public class Details {
    @Inject
    @SpringBean("feedDao")
    private FeedDao feedDao;
    
    private long id;
    private Feed feed;
    
    void onActivate(long id)
    {
       this.id = id;
       this.feed = feedDao.getFeed(this.id);
    }
    
 
    public String getName() {
        return this.feed.getFeedName();
    }
    
    public String getImage() {
        return this.feed.getSource();
    }
}