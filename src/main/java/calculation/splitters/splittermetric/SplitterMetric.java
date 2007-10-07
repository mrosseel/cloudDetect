package calculation.splitters.splittermetric;

import media.image.CloudImage;

public interface SplitterMetric {
    /*
     * @return higher is better
     */
    public double compute(CloudImage data, int location);
}
