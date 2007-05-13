package persistence.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.Feed;

public class FeedDaoImpl extends HibernateDaoSupport implements FeedDao {

    @SuppressWarnings("unchecked")
    public List<Feed> getAllActiveFeeds() {
        List find = getHibernateTemplate().find(
                        "from Feed where isactive = true");
        List<Feed> list = find;
        return list;
    }

    public List<Feed> getAllFeeds() {
        // TODO Auto-generated method stub
        return null;
    }

}
