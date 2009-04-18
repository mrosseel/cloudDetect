package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.UserData;

public class EnhancedPage {
	
	@ApplicationState
	private UserData userData;
	
	public User getUser() {
		return userData.getUser();
	}

	public void setUser(User user) {
		this.userData.setUser(user);
	}
	
	public boolean isFeedAllowed(long feedId) {
		return userData.isFeedAllowed(feedId);
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
