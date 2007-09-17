/*
 *
 *
 */
package media.chart;

import java.awt.Color;
import java.awt.GradientPaint;
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
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import persistence.model.Result;
import util.DateUtil;

/**
 * Time series chart
 * 
 */
public class ContrastChartCreator {

	private static Log log = LogFactory.getLog(ContrastChartCreator.class);
    
    ChartPanel chartPanel;

    JFreeChart chart;
    
    String title;

    private HashMap<String,TimeSeries> timeSeriesMap = new HashMap<String,TimeSeries>();

    /**
     * A demonstration application showing how to create a simple time series
     * chart. This example uses monthly data.
     * 
     * @param title
     *            the frame title.
     */
    public ContrastChartCreator(String title) {
    	this.title = title;
    }
    
    public ContrastChartCreator() {
    	this("");
    }

    public  JFreeChart getJFreeChart() {

    	TimeSeriesCollection dataset = new TimeSeriesCollection();

        Iterator iter = timeSeriesMap.values().iterator();
        while (iter.hasNext()) {
            dataset.addSeries((TimeSeries) iter.next());
        }
//        dataset.setDomainIsPointsInTime(true);

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
                title, "", "value", dataset, false, true, false);

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // ValueAxis val = plot.getRangeAxis();
        
        

        // contrast axis
        LogarithmicAxis val = new LogarithmicAxis("Contrast");
        val.setRange(0, 100);
        val.setLog10TickLabelsFlag(false);
        plot.setRangeAxis(val);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setOutlinePaint(Color.cyan);
        plot.setBackgroundPaint(new GradientPaint( 0.0f, 0.0f, Color.green, 480.0f, 480.0f, Color.lightGray ));
        
        // date axis
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd/MM - kk:mm"));
        axis.setUpperMargin(0.11);
//        axis.setLowerMargin(0.50);

        
////      set axis margins to allow space for marker labels...
//        final DateAxis domainAxis = new DateAxis("Time");
//        domainAxis.setUpperMargin(0.50);
//        plot.setDomainAxis(domainAxis);
//
//        final ValueAxis rangeAxis = plot.getRangeAxis();
//        rangeAxis.setUpperMargin(0.30);
//        rangeAxis.setLowerMargin(0.50);
        
        
        // markers
        final Marker clear = new ValueMarker(1);
        clear.setPaint(Color.green);
        clear.setLabel("Clear");
        clear.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        clear.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(clear);

        final Marker partClear = new ValueMarker(2);
        partClear.setPaint(Color.orange);
        partClear.setLabel("Partly Clear");
        partClear.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        partClear.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(partClear);
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setShapesVisible(true);
            renderer.setShapesFilled(true);
        }

        
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
