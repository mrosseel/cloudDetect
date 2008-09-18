package persistence.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.Feed;
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
        List find = getHibernateTemplate().find(
                        "from persistence.model.User where username = ?", userName);
        List<User> list = find;
        return (list==null || list.size() == 0)?null:list.get(0);
    }

    
    public void save(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public User getUserById(long id) {
        return (User) getHibernateTemplate().find("from persistence.model.User where id = ?", id).get(0);
    }

	public boolean isUserOwnerOfFeed(User user, long feedId) {
		// from Site s join s.Users as user where :user = user
		return (getHibernateTemplate().find("from persistence.model.User u join u.feeds feed where u.id = ? and feed.id = ?", new Object[]{user.getId(), feedId})).size() != 0;
	}

}
