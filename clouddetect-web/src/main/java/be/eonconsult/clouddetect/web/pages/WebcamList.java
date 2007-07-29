package be.eonconsult.clouddetect.web.pages;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Service;
import org.apache.tapestry.util.TextStreamResponse;

import persistence.dao.FeedDao;
import persistence.model.Feed;
import persistence.model.User;

import com.thoughtworks.xstream.XStream;

public class WebcamList {
    @Inject
    private FeedDao feedDao;
    
    @Inject
    private Log log;
    
    
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
