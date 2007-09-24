package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class CloudJudgeLimits {
	
	private double maxClear;
	private double maxPartlyClear;
	private Long id;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getMaxClear() {
		return maxClear;
	}
	public void setMaxClear(double maxClear) {
		this.maxClear = maxClear;
	}
	public double getMaxPartlyClear() {
		return maxPartlyClear;
	}
	public void setMaxPartlyClear(double maxPartlyClear) {
		this.maxPartlyClear = maxPartlyClear;
	}
	
	
	

}
