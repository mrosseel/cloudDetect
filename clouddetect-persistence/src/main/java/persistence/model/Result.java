package persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity()
public class Result {
	private long id;
	private int feedId;
	private Date time;
	private double result;
	private String cloudJudgeResult;
	
	
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCloudJudgeResult() {
		return cloudJudgeResult;
	}
	public void setCloudJudgeResult(String cloudJudgeResult) {
		this.cloudJudgeResult = cloudJudgeResult;
	}
	
}
