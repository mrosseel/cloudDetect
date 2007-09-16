package calculation.contrastmetric;

import media.image.CloudImage;

public interface ContrastMetric {

    public double calculateContrast(CloudImage image, int splitLocation);
}
