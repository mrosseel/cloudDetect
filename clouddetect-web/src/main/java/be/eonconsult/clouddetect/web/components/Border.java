package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.model.User;
import be.eonconsult.clouddetect.Global;

public class Border {
    
	@ApplicationState
	private User user;
	
	private boolean userExists;
	
	@ApplicationState
	private Global global;
	
	
    @Inject
    @Path("context:images/CloudWatchBETA2.png")
    private Asset logo;
    
    @Inject
    @Path("context:css/style.css")
    private Asset css;

    
    public boolean getMapPage() {
        return false;
    }

    public Asset getLogo() {
        return logo;
    }

    public void setLogo(Asset logo) {
        this.logo = logo;
    }

	public boolean isUserExists() {
		return userExists;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
    
	public boolean isTracking() {
		return global.isTracking();
	}

	public Asset getCss() {
		return css;
	}
}
