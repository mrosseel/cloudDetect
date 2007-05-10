package be.eonconsult.clouddetect.pages;

import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.Renderable;
import org.apache.tapestry.annotations.Parameter;

public class WebcamList {
    
    private String method;
    
    public Renderable getFirstBox() {
        return new Renderable()
        {
            public void render(MarkupWriter writer)
            {
                writer.write("This proves it works.");
                writer.write(method);
                
            }
        };
    }
    
    void onActivate(String aMethod)
    {
       method = aMethod;
    }
}
