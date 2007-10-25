package be.eonconsult.clouddetect.web.pages;

import java.util.List;

import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.util.TextStreamResponse;
import org.slf4j.Logger;

import persistence.dao.FeedDao;
import persistence.model.Feed;

import com.thoughtworks.xstream.XStream;

public class WebcamList {
    @Inject
    private FeedDao feedDao;
    
    @Inject
    private Logger log;
    
    
    public String getWebcamList() {
        List<Feed> list = feedDao.getAllActiveFeeds();
        XStream xstream = new XStream();
        for (Feed feed: list) {
        	feed.setUsers(null);
        	feed.setOwner(null);
		}
//        xstream.omitField(Collection.class, "users");
//        xstream.omitField(User.class, "owner");
        
        return xstream.toXML(list);
    }
    
    public Object onActivate()
    {
    	  String text = getWebcamList();

          return new TextStreamResponse("application/xml", text);
    }
}
