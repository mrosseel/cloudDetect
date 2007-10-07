package calculation.splitters.splittermetric;

import media.image.CloudImage;
import Jama.Matrix;

public class HorizonMetric implements SplitterMetric {
    int leftCount;
    int rightCount;

    // smaller is better
    public double compute(double[] data, int location) {
//      compute the horizon metric 
        if(location >= data.length-1) {
            throw new IllegalArgumentException("no compute locations at the last element of the array!");
        }
        leftCount = location+1;
        rightCount = data.length-location-1;
        double[] left = new double[leftCount];
        double[] right = new double[rightCount];
        System.arraycopy(data, 0, left, 0, leftCount);
        System.arraycopy(data, leftCount, right, 0, rightCount);
        
        double variance1 = computeVariance(left);
        double variance2 = computeVariance(right);
        double varianceResult = variance1 + variance2;
        
        return (varianceResult==0)?Double.MAX_VALUE:(1.0/varianceResult);
    }
   
    public double compute(CloudImage data, int location) {
       double[] monoData = data.getMonochromeData();
       return compute(monoData, location);
    }
    
    protected double computeVariance(double[] data) {
        double avg = getAverage(data);
        for (int count = 0; count < data.length; count++) {
            data[count] = data[count]-avg;
        }
        
        return variance(data);
    }
    
    protected double variance(double[] data) {
        Matrix a = new Matrix(data, 1);
        Matrix b = new Matrix(data, data.length);
        return (a.times(b).get(0,0))/data.length;
    }
    
    protected double getAverage(double[] data) {
        double avg = 0;
        for (int count = 0; count < data.length; count++) {
            avg+=data[count];
        }
        avg/=data.length;
        return avg;
    }
}
