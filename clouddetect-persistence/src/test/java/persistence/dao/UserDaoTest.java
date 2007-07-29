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
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return getFlatXmlDataSet("/minimal_full.xml");
	}

}
