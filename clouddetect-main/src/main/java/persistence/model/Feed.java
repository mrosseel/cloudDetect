package persistence.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Feed {

		private int id;
		private String source;
		private String LocationFreeForm;
		private String feedName;
		private double division;
		private boolean isPrivate;
		private Collection<User> users;
		
		public double getDivision() {
			return division;
		}
		public void setDivision(double division) {
			this.division = division;
		}
		public String getFeedName() {
			return feedName;
		}
		public void setFeedName(String feedName) {
			this.feedName = feedName;
		}
		
		@Id
		@GeneratedValue
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLocationFreeForm() {
			return LocationFreeForm;
		}
		public void setLocationFreeForm(String locationFreeForm) {
			LocationFreeForm = locationFreeForm;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public boolean isPrivate() {
			return isPrivate;
		}
		public void setPrivate(boolean isPrivate) {
			this.isPrivate = isPrivate;
		}
		@ManyToMany(mappedBy="feeds")
		public Collection<User> getUsers() {
			return users;
		}
		public void setUsers(Collection<User> users) {
			this.users = users;
		}
		
		
		
		
		
}
