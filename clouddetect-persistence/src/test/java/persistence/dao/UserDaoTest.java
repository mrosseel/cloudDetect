package persistence.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

import persistence.SpringDBTest;
import persistence.model.Feed;
import persistence.model.User;


public class UserDaoTest extends SpringDBTest {

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("mike@email.com");
		user.setLastName("mike");
        UserDao userDao = (UserDao) appContext.getBean("userdao");
        userDao.save(user);
        
        User search = userDao.getUserByEmail("mike@email.com");
        Assert.assertEquals(search.getEmail(), "mike@email.com");
	}

	@Test
	public void testGetById() {
		User user = new User();
		user.setEmail("mike@email.com");
		user.setLastName("mike");
        UserDao userDao = (UserDao) appContext.getBean("userdao");
        userDao.save(user);
        long id = user.getId();
        
        
        User search = userDao.getUserById(id);
        Assert.assertEquals(search.getEmail(), "mike@email.com");
	}
	
	@Test
	public void testGetUserByUserName() {
		User user;
        UserDao userDao = (UserDao) appContext.getBean("userdao");
        user = userDao.getUserByUserName("miker");
        
        Assert.assertEquals(user.getEmail(), "miker@me.org");
	}
	
	@Test
	public void testIsUserOwnerOfFeed() {
		User user = new User();
		user.setId(1);
        UserDao userDao = (UserDao) appContext.getBean("userdao");
        boolean result1 = userDao.isUserOwnerOfFeed(user,1);
        boolean result2 = userDao.isUserOwnerOfFeed(user,2);
        boolean result5 = userDao.isUserOwnerOfFeed(user,5);
        
        Assert.assertEquals(result1, true);
        Assert.assertEquals(result2, false);
        Assert.assertEquals(result5, false);
		
	}
	
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return getFlatXmlDataSet("/minimal_full.xml");
	}

}
