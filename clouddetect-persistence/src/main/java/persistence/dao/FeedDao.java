package persistence.dao;

import java.util.List;

import persistence.model.Feed;

public interface FeedDao {
    
    public List<Feed> getAllFeeds();
    
    public List<Feed> getAllActiveFeeds();
    
    public Feed getFeed(long id);
    
    public void saveResult(Feed feed);
    
}
