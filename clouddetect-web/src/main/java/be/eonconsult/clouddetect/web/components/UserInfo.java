package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.annotations.ApplicationState;

import persistence.model.User;

public class UserInfo {

	@ApplicationState
	private User user;
	
	private boolean userExists;
	
	
	public String onActionFromLogin() {
		return "Login";
	}
	
	public String onActionFromRegister() {
		return "Register";
	}

	public String onActionFromLogout() {
		user = null;
		System.out.println("Logout");
		return "Start";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserExists() {
		return userExists;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
}
