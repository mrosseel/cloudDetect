package persistence.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Feed {

	private long id;

	private String source;

	private String LocationFreeForm;

	private double latitude;

	private double longitude;

	private String name;

	private double division;

	private boolean isPrivate;

	private boolean isActive;
	
	/* amount subtracted from the observation */
	private double resultOffset;

	private long secondsBetweenUpdates;

	private ProducerType producerType;

	private User owner;

	private Collection<User> users;

	private CloudJudgeLimits cloudJudgeLimits;

	public String getName() {
		return name;
	}

	public void setName(String feedName) {
		this.name = feedName;
	}

	public String getLocationFreeForm() {
		return LocationFreeForm;
	}

	public void setLocationFreeForm(String locationFreeForm) {
		LocationFreeForm = locationFreeForm;
	}

	public double getDivision() {
		return division;
	}

	public void setDivision(double division) {
		this.division = division;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public ProducerType getProducerType() {
		return producerType;
	}

	public void setProducerType(ProducerType producerType) {
		this.producerType = producerType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@ManyToMany(mappedBy = "feeds", fetch = FetchType.EAGER)
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public double getResultOffset() {
		return resultOffset;
	}

	public void setResultOffset(double resultOffset) {
		this.resultOffset = resultOffset;
	}

	public long getSecondsBetweenUpdates() {
		return secondsBetweenUpdates;
	}

	public void setSecondsBetweenUpdates(long secondsBetweenUpdates) {
		this.secondsBetweenUpdates = secondsBetweenUpdates;
	}

	@OneToOne
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
	public CloudJudgeLimits getCloudJudgeLimits() {
		return cloudJudgeLimits;
	}

	public void setCloudJudgeLimits(CloudJudgeLimits cloudJudgeLimits) {
		this.cloudJudgeLimits = cloudJudgeLimits;
	}
}
