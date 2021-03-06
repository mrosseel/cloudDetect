package persistence.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.Feed;

public class FeedDaoImpl extends HibernateDaoSupport implements FeedDao {

	@SuppressWarnings("unchecked")
	public List<Feed> getAllActiveFeeds() {
		List find = getHibernateTemplate().find("from persistence.model.Feed where active = true");
		List<Feed> list = find;
		return list;
	}

	public List<Feed> getAllFeeds() {
		throw new UnsupportedOperationException();
	}

	public void saveResult(Feed feed) {
		getHibernateTemplate().saveOrUpdate(feed);
	}

	public Feed getFeed(long id) {
		return (Feed) getHibernateTemplate().find("from persistence.model.Feed where id = ?", id).get(0);
	}

	

}
