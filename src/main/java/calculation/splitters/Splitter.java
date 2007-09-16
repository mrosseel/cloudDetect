package calculation.splitters;

public interface Splitter {

    public double getResult();

    public void setResult(double result);

    public int getBestSplitterLocation();

    /**
     * @param i
     */
    public void setBestSplitterLocation(int i);

}