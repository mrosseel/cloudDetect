package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.UserData;

public class UserInfo {

	@ApplicationState
	private UserData userData;
	
	private boolean userExists = false;
	
	
	public String onActionFromLogin() {
		return "Login";
	}
	
	public String onActionFromRegister() {
		return "Register";
	}

	public String onActionFromLogout() {
		userData = null;
		System.out.println("Logout");
		setUserExists(false);
		return "Start";
	}

	public User getUser() {
		return userData.getUser();
	}

	public void setUser(User user) {
		this.userData.setUserId(user.getId());
	}

	public boolean isUserExists() {
		return getUser() != null;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
}
