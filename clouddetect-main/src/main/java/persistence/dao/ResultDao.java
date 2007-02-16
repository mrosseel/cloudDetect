package persistence.dao;

import java.util.Date;
import java.util.List;

import persistence.model.Result;

public interface ResultDao {

	public Result findResultById(int id);
	public List<Result> findResultsFromThePastHours(int hours);
	public List<Result> findResultsFromUntil(Date from, Date until);
	public void saveResult(Result result);
	
	
}
