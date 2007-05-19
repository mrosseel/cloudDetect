package be.eonconsult.clouddetect.components;

import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.Renderable;

public class Border {
    
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
