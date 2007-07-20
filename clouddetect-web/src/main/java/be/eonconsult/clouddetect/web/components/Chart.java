package be.eonconsult.clouddetect.web.components;

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

import persistence.dao.ResultDao;
import persistence.model.Result;
import application.InstanceFactory;


/**
 */
public class Chart {
	
	public static final int CHART_HEIGHT = 240;

	public static final int CHART_WIDTH = 320;

	@Parameter
	private List<?> _context;

	@Parameter
	private int id;

	@Parameter
	private String name;

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

		Link link = _resources.createActionLink(org.apache.tapestry.TapestryConstants.ACTION_EVENT, false, contextArray);

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

	private class ChartStreamResponse implements StreamResponse {

		private JFreeChart jfreechart;

		public ChartStreamResponse(JFreeChart jfreechart) {
			this.jfreechart = jfreechart;
		}

		public String getContentType() {
			return "image/png";
		}

		/* (non-Javadoc)
		 * @see org.apache.tapestry.StreamResponse#getStream()
		 */
		public InputStream getStream() throws IOException {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(out, jfreechart, CHART_WIDTH, CHART_HEIGHT);
			return new ByteArrayInputStream(out.toByteArray());
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
        
        List<Result> results = dao.findResultByFeedId(id);
        
        if(results!=null) {
        	log.info("number of results: " + results.size());
        	creator.addValue(name,results);	
        }
        
        return creator.getJFreeChart();
	}
}

