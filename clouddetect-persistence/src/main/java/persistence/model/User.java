package persistence.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity()
public class User {
	private long id;

	private String name;

	private String firstName;

	private String email;

	private String password;

	private boolean isPrivate;

	private boolean isPremium;
	
	private boolean isAdmin;
	
	private Collection<Feed> feeds;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	@Column(unique = true, nullable = false)
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			//name="USER_FEED", 
//			joinColumns={@JoinColumn(name="USER_ID")},
			inverseJoinColumns={@JoinColumn(name="FEED_ID")})
	public Collection<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(Collection<Feed> feeds) {
		this.feeds = feeds;
	}
	
	

}
