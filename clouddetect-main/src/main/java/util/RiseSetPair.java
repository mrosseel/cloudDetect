package util;

import java.util.Date;

import org.joda.time.DateTime;

public class RiseSetPair {
	DateTime rise;
	DateTime set;
	
	public RiseSetPair(DateTime rise, DateTime set) {
		this.rise = rise;
		this.set = set;
	}

	public Date getRiseDate() {
		return rise.toDate();
	}

	public Date getSetDate() {
		return set.toDate();
	}
	
	public DateTime getRise() {
		return rise;
	}
	
	public DateTime getSet() {
		return set;
	}
	
	public void setRise(DateTime rise) {
		this.rise = rise;
	}

	public void setSet(DateTime set) {
		this.set = set;
	}

	public RiseSetPair clonePreviousDay(int nrOfDays) {
		return new RiseSetPair(rise.minusDays(nrOfDays), set.minusDays(nrOfDays));
	}
}