package be.eonconsult.clouddetect;

import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;

public class UserData {

	private long userId;
	private boolean userExists;
	
	@Inject
	private UserDao userDao;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
		this.userExists = getUser() != null;
	}
	
	public User getUser() {
		return userDao.getUserById(getUserId());
	}
	
	public void setUser(User user) {
		this.setUserId(user.getId());
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}

	public boolean isUserExists() {
		return userExists;
	}
	
	public boolean isFeedAllowed(long feedId) {
		return userDao.isUserOwnerOfFeed(getUser(), feedId);
	}
}
