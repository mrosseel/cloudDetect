package persistence.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Feed {

		private int id;
		private String source;
		private String LocationFreeForm;
        private double latitude;
        private double longitude;
		private String feedName;
		private double division;
		private boolean isPrivate;
        private boolean isActive;
        private long secondsBetweenUpdates;
        private User owner;
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
        public boolean isActive() {
            return isActive;
        }
        public void setActive(boolean isActive) {
            this.isActive = isActive;
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
}
