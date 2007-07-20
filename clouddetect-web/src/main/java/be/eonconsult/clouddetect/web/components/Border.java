package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry.Asset;
import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.Renderable;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Path;

public class Border {
    
    @Inject
    @Path("context:images/webcam5.png")
    private Asset logo;
    
    public Renderable getFirstBox() {
        return new Renderable()
        {
            public void render(MarkupWriter writer)
            {
                writer.write("This proves it works.");
                
            }
        };
    }
    
    public boolean getMapPage() {
        return false;
    }

    public Asset getLogo() {
        return logo;
    }

    public void setLogo(Asset logo) {
        this.logo = logo;
    }
    
    
    
//    @Component(id="login")
//    private Login login;
//    
//    public Login getFirstBox() {
//        return login;
//    }
//
//    public Login getLogin() {
//        return login;
//    }
//
//    public void setLogin(Login login) {
//        this.login = login;
//    }
    
    

   
}
