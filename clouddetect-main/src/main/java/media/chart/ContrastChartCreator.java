/*
 *
 *
 */
package media.chart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.StandardXYItemRenderer;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.data.XYDataset;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import persistence.model.Result;
import util.DateUtil;

/**
 * An example of a time series chart. For the most part, default settings are
 * used, except that the renderer is modified to show filled shapes (as well as
 * lines) at each data point.
 * 
 * @author David Gilbert
 */
public class ContrastChartCreator {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    static TimeSeriesCollection dataset = new TimeSeriesCollection();

    static ChartPanel chartPanel;

    static JFreeChart chart;

    private static HashMap timeSeriesMap = new HashMap();

    /**
     * A demonstration application showing how to create a simple time series
     * chart. This example uses monthly data.
     * 
     * @param title
     *            the frame title.
     */
    public ContrastChartCreator() {
    }

    public  JFreeChart getJFreeChart() {

        // XYDataset dataset = createDataset();

        Iterator iter = ContrastChartCreator.timeSeriesMap.values().iterator();
        while (iter.hasNext()) {
            dataset.addSeries((TimeSeries) iter.next());
        }
        dataset.setDomainIsPointsInTime(true);

        JFreeChart chart = createChart(dataset);
        return chart;
    }

    /**
     * Creates a chart.
     * 
     * @param dataset
     *            a dataset.
     * 
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Cloud Detection", "Time", "", dataset, true, true, false);

        chart.setBackgroundPaint(Color.white);

        StandardLegend sl = (StandardLegend) chart.getLegend();
        sl.setDisplaySeriesShapes(true);

        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // ValueAxis val = plot.getRangeAxis();

        LogarithmicAxis val = new LogarithmicAxis("Contrast");
        val.setRange(0, 100);
        val.setLog10TickLabelsFlag(false);
        plot.setRangeAxis(val);
        // deprecated since 0.9.16, don't know how to change
        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer) {
            StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            rr.setPlotShapes(true);
            rr.setShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd - kk:mm"));
        ContrastChartCreator.chart = chart;
        return chart;
    }

    public void addValue(String timeSeriesName, double value, Date date) {
        TimeSeries timeSeries = (TimeSeries) ContrastChartCreator.timeSeriesMap
                .get(timeSeriesName);
        if (timeSeries == null) {
            timeSeries = createTimeSeries(timeSeriesName);
        }
        timeSeries.add(new FixedMillisecond(date), new Double(value));
    }

    public void addValue(String timeSeriesName, double value) {
        addValue(timeSeriesName, value, DateUtil.getCurrentDate());
    }

    public void addValue(String timeSeriesName, Result result) {
    	addValue(timeSeriesName, result.getResult(), result.getTime());
    }
    
   public void addValue(String timeSeriesName, List<Result> result) {
	   Iterator<Result> iter = result.iterator();
	   while(iter.hasNext()) {
		   addValue(timeSeriesName, iter.next());
	   }
    }
    
    private TimeSeries createTimeSeries(String timeSeriesName) {
        TimeSeries timeSeries = new TimeSeries(timeSeriesName, "Millisecond",
                "Value", FixedMillisecond.class);
        if (timeSeriesName == null) {
            throw new RuntimeException(
                    "All CloudImages should provide an origin a name.");
        }
        ContrastChartCreator.timeSeriesMap.put(timeSeriesName, timeSeries);
        //super.setChart(getJFreeChart());
        return timeSeries;
    }

    private void writeImage() {

        try {
            // org.jfree.chart.ChartUtilities.saveChartAsJPEG(new
            // File("V:/current.jpg"),
            // 99, ContrastChart.chart, 640,480);

            org.jfree.chart.ChartUtilities.saveChartAsPNG(new File(
                    "V:/current.png"), ContrastChartCreator.chart, 640, 480);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Rectangle r = this.getBounds();
        // Image image = this.createImage(r.width, r.height);
        // try {
        // ImageIO.write((RenderedImage) image, "png", new
        // File("V:/current.png"));
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     * 
     * @return the dataset.
     */
    private XYDataset createDataset() {
        return null;

    }
}
