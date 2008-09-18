package persistence.dao;

import persistence.model.Feed;
import persistence.model.User;

public interface UserDao {
	    public User getUserByEmail(String email);
	    
	    public User getUserByUserName(String userName);

	    public void save(User user);

	    public User getUserById(long id);

		public boolean isUserOwnerOfFeed(User user, long feedId);
}
