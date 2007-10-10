package be.eonconsult.clouddetect.web.components;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import media.chart.ContrastChartCreator;

import org.apache.commons.logging.Log;
import org.apache.tapestry.ComponentResources;
import org.apache.tapestry.Link;
import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.PageRenderSupport;
import org.apache.tapestry.StreamResponse;
import org.apache.tapestry.annotations.AfterRender;
import org.apache.tapestry.annotations.BeginRender;
import org.apache.tapestry.annotations.Environmental;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Mixin;
import org.apache.tapestry.annotations.OnEvent;
import org.apache.tapestry.annotations.Parameter;
import org.apache.tapestry.corelib.mixins.RenderInformals;
import org.apache.tapestry.services.Response;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.joda.time.DateTime;

import persistence.dao.ResultDao;
import persistence.model.Result;
import util.AstroUtil;
import util.RiseSetPair;
import application.InstanceFactory;

/**
 */
public class Chart {

	@Parameter
	private int height;

	@Parameter
	private int width;

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

	@Inject
	private Log log;

	@SuppressWarnings("unused")
	@Mixin
	private RenderInformals _renderInformals;

	@Environmental
	private PageRenderSupport _support;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		String clientId = _support.allocateClientId(_resources.getId());

		Object[] contextArray = _context == null ? new Object[0] : _context.toArray();

		Link link = _resources
				.createActionLink(org.apache.tapestry.TapestryConstants.ACTION_EVENT, false, contextArray);

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
			BufferedImage image = jfreechart.createBufferedImage(width, height);
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
		ContrastChartCreator creator = new ContrastChartCreator();
		creator.addMarker(maxClear, Color.green, "Clear");
		creator.addMarker(maxPartlyClear, Color.orange, "Partly Clear");

		// List<Result> results = dao.findResultsFromThePastHours(hours,id);
		RiseSetPair pair = AstroUtil.getLastNight(50, 4, new DateTime(), day);
		List<Result> results = dao.findResultsFromUntil(pair.getRise().toDate(), pair.getSet().toDate(), id);

		if (results != null) {
			log.debug("number of results: " + results.size());
			creator.addValue(name, results);
		}

		JFreeChart result = creator.getJFreeChart();
		result
				.setTitle("Night of " + pair.getRise().toString("dd/MM/YY") + " to "
						+ pair.getSet().toString("dd/MM/YY"));
		return result;
	}
}
