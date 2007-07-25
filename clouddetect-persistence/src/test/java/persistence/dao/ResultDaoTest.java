package persistence.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

import persistence.SpringDBTest;
import persistence.model.Result;


public class ResultDaoTest extends SpringDBTest {

//	public List<Result> findResultByFeedId(int id);
//	public List<Result> findResultsFromThePastHours(int hours);
//	public List<Result> findResultsFromUntil(Date from, Date until);
	
	
	@Test
	public void testFindResultById() {
		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
        Result result = resultDao.findResultById(13);
        Assert.assertNotNull(result);
        result = resultDao.findResultById(1300);
        Assert.assertNull(result);
	}

	@Test
	public void testFindResultByFeedId() {
		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
        List<Result> result = resultDao.findResultByFeedId(0);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 10);
        result = resultDao.findResultByFeedId(1);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
        result = resultDao.findResultByFeedId(1300);
        Assert.assertNull(result);
	}
	
	@Test
	public void testFindResultsFromThePastHours() {
		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
        List<Result> result = resultDao.findResultsFromThePastHours(0, 0);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
	}
        
	@Override
	protected IDataSet getDataSet() throws Exception {
		return getFlatXmlDataSet("/minimal_full.xml");
	}

}
