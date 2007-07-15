package persistence.dao;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.Result;

public class ResultDaoImpl extends HibernateDaoSupport implements ResultDao {

	public Result findResultById(int id) {
		List list = getHibernateTemplate().find("from Result where id=?", id);
		return (list.size()>0)?(Result) list.get(0):null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Result> findResultByFeedId(int id) {
		List<Result> list = getHibernateTemplate().find("from Result where feedid=?", id);
		return (list.size()>0)?list:null;
	}


	public List<Result> findResultsFromThePastHours(int hours) {
		DateTime until = new DateTime();
		DateTime from = until.minusHours(hours);
		return findResultsFromUntil(from.toDate(), until.toDate());
	}

	@SuppressWarnings("unchecked")
	public List<Result> findResultsFromUntil(Date from, Date until) {
		List<Result> list = getHibernateTemplate().find(
				"from Result where time>? and time<?",
				new Object[] { from, until });
		return list;
	}

	public void saveResult(Result result) {
		getHibernateTemplate().saveOrUpdate(result);
	}

}
