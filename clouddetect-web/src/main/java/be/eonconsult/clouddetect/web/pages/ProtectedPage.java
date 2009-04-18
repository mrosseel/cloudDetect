package be.eonconsult.clouddetect.web.pages;


public class ProtectedPage extends EnhancedPage {
	
	String onActivate() {
		if(!getUserData().isUserExists()) {
			return "login";
		}
		
		return null;
	}
}
