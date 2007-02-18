/*
 *
 *
 */
package media.chart;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * Time series chart
 * 
 */
public class ContrastChartCreator {

	private static Log log = LogFactory.getLog(ContrastChartCreator.class);
    static TimeSeriesCollection dataset = new TimeSeriesCollection();

    ChartPanel chartPanel;

    JFreeChart chart;

    private HashMap<String,TimeSeries> timeSeriesMap = new HashMap<String,TimeSeries>();

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

        Iterator iter = timeSeriesMap.values().iterator();
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
    private JFreeChart createChart(XYDataset dataset) {

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
        plot.setOutlinePaint(Color.cyan);
//        plot.setBackgroundPaint(new GradientPaint( 0.0f, 0.0f, Color.green, 480.0f, 480.0f, Color.lightGray ));

        XYItemRenderer renderer = plot.getRenderer();
        if (renderer instanceof StandardXYItemRenderer) {
            StandardXYItemRenderer rr = (StandardXYItemRenderer) renderer;
            rr.setPlotShapes(true);
            rr.setShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd/MM - kk:mm"));
        return chart;
    }

    public void addValue(String timeSeriesName, double value, Date date) {
        TimeSeries timeSeries = (TimeSeries) timeSeriesMap
                .get(timeSeriesName);
        if (timeSeries == null) {
            timeSeries = createTimeSeries(timeSeriesName);
        }
        try{
        timeSeries.add(new FixedMillisecond(date), new Double(value));
        } catch(Throwable e) {
        	log.warn("Error adding observation to the chart.", e);
        }
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
        timeSeriesMap.put(timeSeriesName, timeSeries);
        //super.setChart(getJFreeChart());
        return timeSeries;
    }
}
