package calculation.splitters.splittermetric;

import util.StandardDeviation;
import media.image.CloudImageResult;

public class MinStdDevSplitterMetric implements SplitterMetric {
    int leftCount, rightCount;

    public double compute(CloudImageResult data, int location) {
        double monoData[] = data.getMonochromeData();
        if (location >= monoData.length - 1) {
            throw new IllegalArgumentException(
                    "no compute locations at the last element of the array!");
        }
        leftCount = location + 1;
        rightCount = monoData.length - location - 1;

        double left = StandardDeviation.sdFast(monoData, 0, location)
                / (location + 1);
        double right = StandardDeviation.sdFast(monoData, location + 1,
                monoData.length - 1)
                / (monoData.length - location + 1);
        return 1.0 / (left + right);
    }
}
