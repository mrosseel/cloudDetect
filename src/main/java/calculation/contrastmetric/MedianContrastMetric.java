package calculation.contrastmetric;

import util.Median;
import media.image.CloudImage;

public class MedianContrastMetric implements ContrastMetric {

    public double calculateContrast(CloudImage image, int splitLocation) {
        double[] data = image.getMonochromeData();
        double leftMedian = Median.find(data, 0, splitLocation);
        double rightMedian = Median.find(data, splitLocation + 1,
                data.length - 1);
        return Math.abs(leftMedian - rightMedian);
    }
}
