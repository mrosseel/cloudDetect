package be.eonconsult.clouddetect.web.data;

import org.apache.tapestry5.corelib.components.Form;

public class LoginForm extends Form {

	private String username;
	
	private String password;
	
	private boolean rememberMe;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	
	
	
	
}
