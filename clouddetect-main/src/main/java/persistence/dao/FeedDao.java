package persistence.dao;

import java.util.List;

import persistence.model.Feed;

public interface FeedDao {
    
    public List<Feed> getAllFeeds();
    
}
