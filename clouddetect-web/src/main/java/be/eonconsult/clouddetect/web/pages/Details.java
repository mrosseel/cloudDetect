package be.eonconsult.clouddetect.web.pages;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Persist;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.CloudJudgeLimits;
import persistence.model.Feed;
import persistence.model.Result;
import application.InstanceFactory;
import calculation.CloudStatusMonitor;
import calculation.CloudJudge.CloudStatus;

public class Details {
	private static Log log = LogFactory.getLog(Details.class);
	
    @Inject
    private FeedDao feedDao;
    @InjectPage
    private DetailsImage detailsImage;
    @InjectPage
    private Details details;

    
    @Persist
    private int id;

    @Persist
    private Feed feed;
    
    @Persist
    private boolean clearWarning;
    
    @Persist
    private boolean cloudyWarning;
    
    @Persist
    private int delay = 20;
    
    @Persist
    private CloudStatusMonitor monitor;
    
    @Persist
    private CloudStatus cloudStatusResult;
    
    @Persist
    private boolean notify = true;
    
    void onActivate(int id)
    {
    	log.info("in activate with id " + id);
       this.id = id;
       this.feed = feedDao.getFeed(this.id);
       if(monitor == null) {
    	   monitor = new CloudStatusMonitor();
    	   log.debug("Creating new cloudStatusMonitor");
       }
       ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
	   Result result = dao.findMostRecentResultByFeedId(new Long(feed.getId()).longValue());
	   cloudStatusResult = CloudStatus.valueOf(result.getCloudJudgeResult());
	   this.notify = monitor.checkIfNotify(cloudStatusResult, new Date());
	   this.notify = true;
    }
    
    int onPassivate() {
    	return id;
    }

    public CloudJudgeLimits getLimits() {
    	return feed.getCloudJudgeLimits();
    }
	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	
	public String getCloudStatus() {
		return cloudStatusResult.toString();
	}
	
	
	 Object onActionFromCurrentImage()
	  {

	    detailsImage.setFeed(feed);
	    detailsImage.setId(id);

	    return detailsImage;
	  }
	 
	 void onSubmitFromTransition() {
//		 details.onActivate(id);
//		 return details;
		 
	 }

	public boolean isClearWarning() {
		return clearWarning;
	}

	public void setClearWarning(boolean clearWarning) {
		this.clearWarning = clearWarning;
	}

	public boolean isCloudyWarning() {
		return cloudyWarning;
	}

	public void setCloudyWarning(boolean cloudyWarning) {
		this.cloudyWarning = cloudyWarning;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}
}
