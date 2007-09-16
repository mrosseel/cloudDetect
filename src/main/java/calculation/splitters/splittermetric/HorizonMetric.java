package calculation.splitters.splittermetric;

import media.image.CloudImage;
import Jama.Matrix;

public class HorizonMetric implements SplitterMetric {

    public double compute(double[] data, int location) {
//      compute the horizon metric 
        double[] left = new double[location+1];
        double[] right = new double[data.length-location-1];
        System.arraycopy(data, 0, left, 0, location+1);
        System.arraycopy(data, location+1, right, 0, data.length-location-1);
        
        double variance1 = computeVariance(left);
        double variance2 = computeVariance(right);
        
        return variance1 + variance2; 
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
