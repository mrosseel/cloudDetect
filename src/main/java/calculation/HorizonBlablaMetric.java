package calculation;

import util.VisualiseSplitterLocation;
import media.image.CloudImage;
import calculation.contrastmetric.MedianContrastMetric;
import calculation.splitters.BisectionSplitter;
import calculation.splitters.splittermetric.HorizonMetric;

public class HorizonBlablaMetric implements Metric {

    public double compute(CloudImage data) {
        BisectionSplitter splitter = new BisectionSplitter();
        splitter.setSplitterMetric(new HorizonMetric());
        splitter.split(data);
        MedianContrastMetric metric = new MedianContrastMetric();
        VisualiseSplitterLocation.addSplitterLine(data, splitter
                .getBestSplitterLocation());

        return metric.calculateContrast(data, splitter
                .getBestSplitterLocation());
    }

}
