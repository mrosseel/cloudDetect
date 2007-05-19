package be.eonconsult.clouddetect.pages;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.Renderable;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.spring.SpringBean;
import org.apache.tapestry.util.TextStreamResponse;

import persistence.dao.FeedDao;
import persistence.model.Feed;

import com.thoughtworks.xstream.XStream;

public class WebcamList {
    @Inject
    @SpringBean("feedDao")
    private FeedDao feedDao;
    
    @Inject
    private Log log;
    
    
    private String method;
    
    public String getWebcamList() {
        List<Feed> list = feedDao.getAllActiveFeeds();
        XStream xstream = new XStream();
        return xstream.toXML(list);
    }
    
    public Object onActionFromTextStreamResponse() {
        String text = getWebcamList();

        return new TextStreamResponse("application/xml", text);
    }
    
    public Object getFirstBox() {
        return onActionFromTextStreamResponse();
    }
    
//    public Renderable getFirstBox() {
//        return new Renderable()
//        {
//            public void render(MarkupWriter writer)
//            {
//                log.info("writing webcamlist");
//                writer.write(getWebcamList());
//                
//            }
//        };
//    }
    
    void onActivate(String aMethod)
    {
       method = aMethod;
    }
}
