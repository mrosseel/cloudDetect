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

    public void save(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public Feed getUserById(long id) {
        return (Feed) getHibernateTemplate().find("from persistence.model.User where id = ?", id).get(0);
    }

}
