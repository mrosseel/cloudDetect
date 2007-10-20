/*
 * Created on 4-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation;

import java.util.Arrays;

import media.image.CloudImage;
import util.Median;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MedianPixelBrightnessMetric implements Metric {

    /**
     * 
     */
    public double compute(CloudImage image) {
        double[] data = image.getMonochromeData();
        Arrays.sort(data);
        double lowest = data[0];
        double median = Median.find(data, 0, data.length);
        
        return (median-lowest) / (double) data.length / 255 * 100;
    }
}
