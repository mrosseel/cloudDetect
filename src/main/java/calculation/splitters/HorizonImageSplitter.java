package calculation.splitters;

import calculation.splitters.splittermetric.HorizonMetric;
import calculation.splitters.splittermetric.SplitterMetric;

public class HorizonImageSplitter extends AbstractGenericImageSplitter {

    HorizonMetric horizon = new HorizonMetric();
   
protected void preDataManipulation(double[] data) {
    // TODO Auto-generated method stub
    
}

protected void postDataManipulation(double[] data) {
    // TODO Auto-generated method stub
    
}

protected double calculateValue(double[] data, int from, int to) {
    return 0;
}

public void setSplitterMetric(SplitterMetric metric) {
    // TODO Auto-generated method stub
    
}

public SplitterMetric getSplitterMetric() {
    // TODO Auto-generated method stub
    return null;
}


}
