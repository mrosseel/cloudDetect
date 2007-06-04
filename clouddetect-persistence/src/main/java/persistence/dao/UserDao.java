package persistence.dao;

import java.util.List;

import persistence.model.Feed;
import persistence.model.User;

public interface UserDao {
	    public User getUserByEmail(String email);

	    public void save(User user);

	    public User getUserById(long id);
}
