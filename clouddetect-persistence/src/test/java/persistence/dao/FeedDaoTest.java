package persistence.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

import persistence.SpringDBTest;
import persistence.model.Feed;
import persistence.model.User;

public class FeedDaoTest extends SpringDBTest {
    
	@Test
    public void testFeed() {
        Feed feed = new Feed();
        User owner = new User();
        owner.setFirstName("mike");
        owner.setName("rosseel");
        
        feed.setActive(true);
        feed.setDivision(0.7);
        feed.setFeedName("test2");
        feed.setLatitude(50);
        feed.setLongitude(3);
        feed.setPrivate(false);
        feed.setSource("http://miker.homelinux.org/overview.html");
        feed.setOwner(owner);
        
        FeedDao feedDao = (FeedDao) appContext.getBean("feeddao");
        UserDao userDao = (UserDao) appContext.getBean("userdao");
        userDao.save(owner);
        
        feedDao.saveResult(feed);
        List<Feed> list = feedDao.getAllActiveFeeds();
        Assert.assertEquals(list.size(), 1);
    }

	@Test
    public void testFeed2() {
        Feed feed = new Feed();
        feed.setActive(true);
        feed.setDivision(0.7);
        feed.setFeedName("test");
        feed.setLatitude(50);
        feed.setLongitude(3);
        feed.setPrivate(false);
        feed.setSource("http://miker.homelinux.org/overview.html");
                
        FeedDao feedDao = (FeedDao) appContext.getBean("feeddao");
        feedDao.saveResult(feed);
        List<Feed> list = feedDao.getAllActiveFeeds();
        Assert.assertEquals(list.size(), 1);
    }
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return getFlatXmlDataSet("/minimal_full.xml");
	}
}
