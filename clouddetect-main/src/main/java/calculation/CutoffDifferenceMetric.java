/*
 * Created on 22-dec-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package calculation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import calculation.splitters.SlidingWindowSplitter;

import media.image.CloudImageResult;

/**
 * @author Mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CutoffDifferenceMetric implements Metric {
    private int width;

    /**
     * 
     */
    public CutoffDifferenceMetric() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see metrics.Metric#compute(short[])
     */
    public double compute(CloudImageResult image) {
        double[] data = image.getData();
        double resultL = 0;
        double resultR = 0;
        double result;
        SlidingWindowSplitter splitter = new SlidingWindowSplitter();
        int splitterLocation = splitter.split(data, 20);
        for (int i = 0; i != splitterLocation; i++) {
            resultL += data[i];
        }
        for (int i = splitterLocation; i != data.length; i++) {
            resultR += data[i];
        }
        resultL /= splitterLocation;
        resultR /= data.length - splitterLocation + 1;

        System.out.println("left = " + resultL + " right = " + resultR);
        result = Math.abs(resultL - resultR);
        System.out.println("splitter = " + splitterLocation);
        System.out.println("Contrast = " + result);

        return result;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // for debugging
    private void writeData(short[] data) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("linedata.txt"));

            for (int counter = 0; counter != data.length; counter++) {
                writer.write(String.valueOf(data[counter]));
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
