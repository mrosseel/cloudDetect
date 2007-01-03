/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Apr 23, 2003
 * Time: 10:34:16 PM
 * To change this template use Options | File Templates.
 */
package calculation;

import media.image.CloudImage;
import util.VisualiseSplitterLocation;
import calculation.contrastmetric.MedianContrastMetric;

public class ManualMetric implements Metric {
    private double manualSplitterLocation;

    /**
     * Computes standard deviation on the input data
     * 
     * @param data
     * @return
     */
    public double compute(CloudImage data) {
        MedianContrastMetric metric = new MedianContrastMetric();
        int splitterLocation = (int) Math.round(data.getMonochromeData().length
                * getManualSplitterLocation());
        VisualiseSplitterLocation.addSplitterLine(data, splitterLocation);

        return metric.calculateContrast(data, splitterLocation);
    }

    /**
     * value between 0 and 1, where 1 = 100%s (bottom row) and 0 = 0% (top row)
     * 
     * @return
     */
    public double getManualSplitterLocation() {
        return manualSplitterLocation;
    }

    /**
     * value between 0 and 1, where 1 = 100%s (bottom row) and 0 = 0% (top row)
     * 
     * @param manualSplitterLocation
     */
    public void setManualSplitterLocation(double manualSplitterLocation) {
        this.manualSplitterLocation = manualSplitterLocation;
    }
}
