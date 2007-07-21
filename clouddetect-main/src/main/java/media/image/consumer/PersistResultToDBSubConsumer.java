package media.image.consumer;

import media.image.CloudImage;
import media.image.CloudImageMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import persistence.dao.ResultDao;
import persistence.model.Result;
import util.DateUtil;
import application.InstanceFactory;

/**
 * Class ImageScoringSubConsumer
 * 
 * saves the contrastresult from the metadata into the DB
 * 
 */
public class PersistResultToDBSubConsumer implements ImageSubConsumer {
	private static Log log = LogFactory
			.getLog(PersistResultToDBSubConsumer.class);
	
	ResultDao dao;

	public void consume(CloudImage image) {
		CloudImageMetaData metaData = image.getMetaData();
		Result result = new Result();
		result.setResult(metaData.getContrastResult());
		result.setFeedId(metaData.getFeedId());
		result.setTime((metaData.getDate() == null)?DateUtil.getCurrentDate():metaData.getDate());
		dao.saveResult(result);
	}

	public ResultDao getDao() {
		return dao;
	}

	public void setDao(ResultDao dao) {
		this.dao = dao;
	}
}
