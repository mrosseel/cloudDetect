package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;

import be.eonconsult.clouddetect.UserData;

public class GeneralBlocks {

	@ApplicationState
	private UserData userData;
	
	@Inject
	private static Block userInfo;
	
	public boolean isUserExists() {
		return userData.isUserExists();
	}

	public static Block getUserInfo() {
		return userInfo;
	}	
}
