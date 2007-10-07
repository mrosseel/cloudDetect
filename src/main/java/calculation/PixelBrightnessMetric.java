/*
 * Created on 4-jan-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation;

import media.image.CloudImage;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PixelBrightnessMetric implements Metric {

    /**
     * 
     */
    public double compute(CloudImage image) {
        double[] data = image.getMonochromeData();
        double result = 0;
        for (int counter = 0; counter != data.length; counter++) {
            result += data[counter];
        }

        return result / (double) data.length / 255 * 100;
    }
}
