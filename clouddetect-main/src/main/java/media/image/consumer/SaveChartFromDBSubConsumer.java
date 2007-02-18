package media.image.consumer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import media.chart.ContrastChartCreator;
import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;

import persistence.dao.ResultDao;
import persistence.model.Result;

public class SaveChartFromDBSubConsumer implements ImageSubConsumer {

	private static Log log = LogFactory.getLog(SaveChartFromDBSubConsumer.class);
	private int nrOfHours;
	private String saveFilename;
		
	public void consume(CloudImage image) {
		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
        ContrastChartCreator creator = new ContrastChartCreator();
        
        List<Result> results = dao.findResultsFromThePastHours(24);
        creator.addValue(image.getOriginComment(),results);
        try {
            org.jfree.chart.ChartUtilities.saveChartAsJPEG(new File(saveFilename), 85, creator
                    .getJFreeChart(), 640, 480);
        } catch (IOException e) {
            log
                    .error("Error writing contrast chart to file "
                            + saveFilename, e);
        }
	}

	public String getSaveFilename() {
		return saveFilename;
	}

	public void setSaveFilename(String filename) {
		this.saveFilename = filename;
	}

	public int getNrOfHours() {
		return nrOfHours;
	}

	public void setNrOfHours(int nrOfHours) {
		this.nrOfHours = nrOfHours;
	}
}
