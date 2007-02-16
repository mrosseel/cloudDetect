package media.image.consumer;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import media.chart.ContrastChartCreator;
import media.image.CloudImage;
import media.processors.CalculateMetricOnCloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import persistence.dao.ResultDao;
import persistence.model.Result;
import application.InstanceFactory;

/**
 * Class ImageScoringSubConsumer
 * 
 */
public class ImageScorePersistenceSubConsumer implements ImageSubConsumer {
    private static Log log = LogFactory.getLog(ImageScorePersistenceSubConsumer.class);

    public void consume(CloudImage image) {
        // TODO inject it or something
        CalculateMetricOnCloudImage proc = InstanceFactory
                .getCalculateMetricOnCloudImage();
        double score = proc.process(image);
        ResultDao dao = (ResultDao) InstanceFactory.getAppContext().getBean("resultdao");
        Result result = new Result();
        result.setResult(score);
        result.setTime(new Date());
        dao.saveResult(result);
        
        // Move this to another subconsumer!
        
        ContrastChartCreator creator = new ContrastChartCreator();
        List<Result> results = dao.findResultsFromThePastHours(24);
        creator.addValue("blabla",results);
        String saveFileName = "db_chart.jpg";
        try {
            org.jfree.chart.ChartUtilities.saveChartAsJPEG(new File(saveFileName), 95, creator
                    .getJFreeChart(), 640, 480);
        } catch (IOException e) {
            log
                    .error("Error writing contrast chart to file "
                            + saveFileName, e);
        }
    }
}
