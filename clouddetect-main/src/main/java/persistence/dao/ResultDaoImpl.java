package persistence.dao;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import persistence.model.Result;
import util.DateUtil;

public class ResultDaoImpl extends HibernateDaoSupport implements ResultDao {

	public Result findResultById(int id) {
		List list = getHibernateTemplate().find("from Result where id=?", id);
		return (Result) list.get(0);
	}

	public List<Result> findResultsFromThePastHours(int hours) {
		DateTime until = new DateTime(DateUtil.getCurrentDate());
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
