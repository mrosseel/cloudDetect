package be.eonconsult.clouddetect.web.components;


import org.apache.tapestry.Asset;
import org.apache.tapestry.MarkupWriter;
import org.apache.tapestry.PageRenderSupport;
import org.apache.tapestry.annotations.Environmental;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Path;


/**
 * A component that provides the soundmanager.
 */
public class SoundManager {
	    @Environmental
	    private PageRenderSupport _pageRenderSupport;
		
	    @Inject
	    @Path("context:js/soundmanager2.js")
	    private Asset jsSoundManager2Script;

	    @Inject
	    @Path("context:js/mysoundmanager.js")
	    private Asset mySoundManagerScript;

	    
	    
//	    @Inject
//	    private Environment environment;
	    
	    void beginRender(MarkupWriter writer) {
			_pageRenderSupport.addScriptLink(jsSoundManager2Script);
			_pageRenderSupport.addScriptLink(mySoundManagerScript);
			
			//todo fix this when TAPESTRY-1393 is fixed
//			Document document = environment.peek(Document.class);
//			Element head = document.find("html/head");
//			if (head == null) return;
//			head.elementAt(0, "link", "rel", "stylesheet", "type", "text/css", "href", jsCalendarStylesheet
//					.toClientURL());
		}
	}

	
	
