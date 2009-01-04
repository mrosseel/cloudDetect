/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:34:16 PM
 * To change this template use Options | File Templates.
 */
package calculation;

import media.image.CloudImageResult;
import util.VisualiseSplitterLocation;
import calculation.contrastmetric.MedianContrastMetric;
import calculation.splitters.BisectionSplitter;
import calculation.splitters.splittermetric.MinStdDevSplitterMetric;

public class MinStdDevMetric implements Metric {
    /**
     * Computes standard deviation on the input data
     * 
     * @param data
     * @return
     */
    public double compute(CloudImageResult data) {
        BisectionSplitter splitter = new BisectionSplitter();
        splitter.setSplitterMetric(new MinStdDevSplitterMetric());
        splitter.split(data);
        MedianContrastMetric metric = new MedianContrastMetric();
        VisualiseSplitterLocation.addSplitterLine(data, splitter
                .getBestSplitterLocation());

        return metric.calculateContrast(data, splitter
                .getBestSplitterLocation());
    }

}
