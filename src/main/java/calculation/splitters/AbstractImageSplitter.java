package calculation.splitters;

import media.image.CloudImage;
import calculation.splitters.splittermetric.SplitterMetric;

public abstract class AbstractImageSplitter implements ImageSplitter {
    protected SplitterMetric metric;

    protected int bestSplitterLocation;

    protected double result = Double.MIN_VALUE;

    public abstract void split(CloudImage data);

    public void setSplitterMetric(SplitterMetric metric) {
        this.metric = metric;
    }

    public SplitterMetric getSplitterMetric() {
        return metric;
    }

    public double getResult() {
        // TODO Auto-generated method stub
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getBestSplitterLocation() {
        return bestSplitterLocation;
    }

    public void setBestSplitterLocation(int location) {
        this.bestSplitterLocation = location;
    }

}
