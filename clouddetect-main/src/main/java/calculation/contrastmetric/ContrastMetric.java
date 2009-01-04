package calculation.contrastmetric;

import media.image.CloudImageResult;

public interface ContrastMetric {

    public double calculateContrast(CloudImageResult image, int splitLocation);
}
