package calculation.splitters.splittermetric;

import media.image.CloudImageResult;

public interface SplitterMetric {
    /*
     * @return higher is better
     */
    public double compute(CloudImageResult data, int location);
}
