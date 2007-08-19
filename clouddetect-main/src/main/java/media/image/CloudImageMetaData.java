package media.image;

import java.util.Date;

import calculation.CloudJudge.CloudStatus;

public class CloudImageMetaData {
    private Date date;

    private double contrastResult;
    
    private boolean notify;
    private int feedId;
    private String cloudJudgeResult;
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getContrastResult() {
        return contrastResult;
    }

    public void setContrastResult(double contrastResult) {
        this.contrastResult = contrastResult;
    }

    public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    // TODO dirty hack for presentation
	public String getCloudJudgeResult() {
		return cloudJudgeResult;
	}

	public void setCloudJudgeResult(String cloudJudgeResult) {
		this.cloudJudgeResult = cloudJudgeResult;
	}
    
}
