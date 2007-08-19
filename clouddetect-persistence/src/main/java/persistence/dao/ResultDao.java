package persistence.dao;

import java.util.Date;
import java.util.List;

import persistence.model.Result;

public interface ResultDao {

	public Result findResultById(long id);
	public List<Result> findResultByFeedId(long id);
	public Result findMostRecentResultByFeedId(long id);
	public List<Result> findResultsFromThePastHours(int hours, long id);
	public List<Result> findResultsFromUntil(Date from, Date until, long id);
	public void saveResult(Result result);
}
