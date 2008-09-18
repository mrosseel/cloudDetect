package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry5.annotations.ApplicationState;

import persistence.model.User;

public class ProtectedPage {
	
	@ApplicationState
	private User user;
	
	private boolean userExists;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	String onActivate() {
		if(!userExists) {
			return "login";
		}
		
		return null;
	}

}
