package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry.annotations.ApplicationState;
import org.apache.tapestry.annotations.OnEvent;

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
	
	@OnEvent(value="setupRender")
	public boolean checkPermissions() {
		if(!userExists) {
			return false;
		}
		
		return true;
	}

}
