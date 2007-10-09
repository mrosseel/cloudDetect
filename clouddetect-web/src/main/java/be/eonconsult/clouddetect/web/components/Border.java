package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry.Asset;
import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.Renderable;
import org.apache.tapestry.annotations.ApplicationState;
import org.apache.tapestry.annotations.Component;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Path;

import be.eonconsult.clouddetect.Global;

import persistence.model.User;

public class Border {
    
	@ApplicationState
	private User user;
	
	private boolean userExists;
	
	@ApplicationState
	private Global global;
	
	
    @Inject
    @Path("context:images/CloudWatchBETA2.png")
    private Asset logo;
    
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
}
