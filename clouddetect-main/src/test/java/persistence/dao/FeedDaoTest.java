package persistence.dao;

import java.util.List;

import junit.framework.TestCase;
import persistence.model.Feed;
import application.InstanceFactory;

public class FeedDaoTest extends TestCase {
    
    public void testFeed() {
        Feed feed = new Feed();
        feed.setActive(true);
        feed.setDivision(0.7);
        feed.setFeedName("test");
        feed.setLatitude(50);
        feed.setLongitude(3);
        feed.setPrivate(false);
        feed.setSource("http://miker.homelinux.org/overview.html");
                
        FeedDao feedDao = (FeedDao) InstanceFactory.getBean("feeddao");
        feedDao.saveResult(feed);
        List<Feed> list = feedDao.getAllActiveFeeds();
        System.out.println(list.size());
    }

}
