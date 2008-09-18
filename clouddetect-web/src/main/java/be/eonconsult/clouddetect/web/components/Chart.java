package be.eonconsult.clouddetect.web.components;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import media.chart.ContrastChartCreator;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.RenderSupport;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Mixin;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.mixins.RenderInformals;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.joda.time.DateTime;

import persistence.dao.FeedDao;
import persistence.dao.ResultDao;
import persistence.model.Feed;
import persistence.model.Result;
import util.AstroUtil;
import util.RiseSetPair;
import application.InstanceFactory;

/**
 */
public class Chart {

	@Parameter
	private int chartHeight;

	@Parameter
	private int chartWidth;

	@Parameter
	private int day;

	@Parameter
	private List<?> _context;

	@Parameter
	private int id;

	@Parameter
	private String name;

	@Parameter
	private double maxClear;

	@Parameter
	private double maxPartlyClear;

	@Inject
	private ComponentResources _resources;

	@SuppressWarnings("unused")
	@Mixin
	private RenderInformals _renderInformals;

	@Environmental
	private RenderSupport _support;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		String clientId = _support.allocateClientId(_resources.getId());

		Object[] contextArray = _context == null ? new Object[0] : _context.toArray();

		Link link = _resources
				.createActionLink(org.apache.tapestry5.EventConstants.ACTION, false, contextArray);

		writer.element("img", "src", link, "id", clientId);

		_resources.renderInformalParameters(writer);

	}

	@AfterRender
	void afterRender(MarkupWriter writer) {
		writer.end();
	}

	@OnEvent
	public StreamResponse renderChart() throws IOException {
		JFreeChart jfreechart = createChart();

		return new ChartStreamResponse(jfreechart);
	}

	// 
	private class ChartStreamResponse implements StreamResponse {

		private JFreeChart jfreechart;

		public ChartStreamResponse(JFreeChart jfreechart) {
			this.jfreechart = jfreechart;
		}

		public String getContentType() {
			return "image/png";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.tapestry.StreamResponse#getStream()
		 */
		public InputStream getStream() throws IOException {
			BufferedImage image = jfreechart.createBufferedImage(chartWidth, chartHeight);
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ChartUtilities.writeBufferedImageAsPNG(byteArray, image, true, 5);
			// ChartUtilities.writeBufferedImageAsJPEG(byteArray, (float) 0.7,
			// image);
			return new ByteArrayInputStream(byteArray.toByteArray());
		}

		public void prepareResponse(Response arg0) {
			// TODO Auto-generated method stub

		}
	}

	/**
	 * @param month
	 * @param year
	 * @return
	 */
	private JFreeChart createChart() {
		ResultDao dao = (ResultDao) InstanceFactory.getBean("resultdao");
		FeedDao feedDao = (FeedDao) InstanceFactory.getBean("feeddao");
		Feed feed = feedDao.getFeed(id);
		double offset = feed.getResultOffset();
		ContrastChartCreator creator = new ContrastChartCreator();
		creator.addMarker(maxClear - offset, Color.green, "Clear");
		creator.addMarker(maxPartlyClear - offset, Color.orange, "Partly Clear");


		 
		RiseSetPair pair = AstroUtil.getLastNight(feed.getLatitude(), feed.getLongitude(), new DateTime(), day);
		List<Result> results = dao.findResultsFromUntil(pair.getRise().toDate(), pair.getSet().toDate(), id);

		for (Result result : results) {
			result.setResult(result.getResult()-offset);
		}
		if (results != null) {
			creator.addValue(name, results);
		}

		JFreeChart result = creator.getJFreeChart();
		result.setTitle("Night of " + pair.getRise().toString("dd/MM/YY") + " to "
						+ pair.getSet().toString("dd/MM/YY"));
		return result;
	}

	public int getChartHeight() {
		return chartHeight;
	}

	public void setChartHeight(int chartHeight) {
		this.chartHeight = chartHeight;
	}

	public int getChartWidth() {
		return chartWidth;
	}

	public void setChartWidth(int chartWidth) {
		this.chartWidth = chartWidth;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMaxClear() {
		return maxClear;
	}

	public void setMaxClear(double maxClear) {
		this.maxClear = maxClear;
	}

	public double getMaxPartlyClear() {
		return maxPartlyClear;
	}

	public void setMaxPartlyClear(double maxPartlyClear) {
		this.maxPartlyClear = maxPartlyClear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
