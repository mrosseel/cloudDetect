package media.image.consumer;

import java.io.File;
import java.io.IOException;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.InstanceFactory;

public class SaveContrastChartSubConsumer implements ImageSubConsumer {

    private static Log log = LogFactory
            .getLog(SaveContrastChartSubConsumer.class);

    private String saveFileName;

    public void consume(CloudImage image) {

        try {
            org.jfree.chart.ChartUtilities.saveChartAsJPEG(new File(saveFileName), 95, InstanceFactory.getContrastChart()
                    .getChart(), 640, 480);
        } catch (IOException e) {
            log
                    .error("Error writing contrast chart to file "
                            + saveFileName, e);
        }
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

}
