package persistence.dao;

import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.joda.time.DateTime;
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
	
	@Test
	public void testFindMostRecentResultByFeedId() {
		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
		Result result = resultDao.findMostRecentResultByFeedId(0);
		
		Assert.assertEquals(0.507843137254901, result.getResult());
	}
	
//	@Test
//	public void testFindCloudStatuses() {
//		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
//		List<String> result = resultDao.findCloudStatuses();
//		for (Iterator iter = result.iterator(); iter.hasNext();) {
//			String element = (String) iter.next();
//			System.out.println("oeuhuoehoeuoeun" + element);
//		}
////	Assert.assertEquals(0.507843137254901, result.getResult());
//	}
	
	@Test
	public void testFindResultsFromUntil() {
		ResultDao resultDao = (ResultDao) appContext.getBean("resultdao");
		DateTime startDate = new DateTime("2007-04-06T00:01:00.993");
		DateTime endDate = new DateTime("2007-04-06T00:01:31.558");
		
		List<Result> result = resultDao.findResultsFromUntil(startDate.toDate(), endDate.toDate(), 0);
		
		Assert.assertEquals(result.size(), 7);
	}
        
	@Override
	protected IDataSet getDataSet() throws Exception {
		return getFlatXmlDataSet("/minimal_full.xml");
	}

}
