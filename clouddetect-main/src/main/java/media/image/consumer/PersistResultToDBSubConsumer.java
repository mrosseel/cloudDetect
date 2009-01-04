package media.image.consumer;

import media.image.CloudResult;
import media.image.CloudResultMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import persistence.dao.ResultDao;
import persistence.model.Result;
import util.DateUtil;

/**
 * Class ImageScoringSubConsumer
 * 
 * saves the contrastresult from the metadata into the DB
 * 
 */
public class PersistResultToDBSubConsumer implements SubConsumer<CloudResult> {
	private static Log log = LogFactory
			.getLog(PersistResultToDBSubConsumer.class);
	
	ResultDao dao;

	public void consume(CloudResult image) {
		CloudResultMetaData metaData = image.getMetaData();
		Result result = new Result();
		result.setResult(metaData.getResult());
		result.setFeedId(metaData.getFeedId());
		result.setTime((metaData.getDate() == null)?DateUtil.getCurrentDate():metaData.getDate());
		result.setCloudJudgeResult(metaData.getCloudJudgeResult());
		dao.saveResult(result);
	}

	public ResultDao getDao() {
		return dao;
	}

	public void setDao(ResultDao dao) {
		this.dao = dao;
	}
}
