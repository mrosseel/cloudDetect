package persistence.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		List find = getHibernateTemplate().find(
				"from persistence.model.User where email = ?", email);
		List<User> list = find;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public User getUserByUserName(String userName) {
		List<User> list = getHibernateTemplate().find(
				"from persistence.model.User where username = ?", userName);
		return (list == null || list.size() == 0) ? null : list.get(0);
	}

	public void save(User user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	public User getUserById(long id) {
		List<User> list = getHibernateTemplate().find(
				"from persistence.model.User where id = ?", id);
		return (list == null || list.size() == 0) ? null : list.get(0);
	}

	public boolean isUserOwnerOfFeed(User user, long feedId) {
		if(user == null) {
			return false;
		}
		// from Site s join s.Users as user where :user = user
		List<User> list = getHibernateTemplate()
				.find(
						"from persistence.model.User u join u.feeds feed where u.id = ? and feed.id = ?",
						new Object[] { user.getId(), feedId });
		return (list == null || list.size() == 0) ? false : true;
	}
}
	