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

		
	    public String getScript() {
	    	return 
	    	String.format("soundManager.url = '/js/soundmanager2.swf'; // path to movie") +
	    	String.format("soundManager.onload = function() {") +
	    	String.format("soundManager._writeDebug('soundManager.onload() - my code executes here');") +
	    	String.format("soundManager.createSound('mySound','song.mp3');") +
	    	String.format("soundManager.play('mySound');");
	    }

	    @Environmental
	    private PageRenderSupport _pageRenderSupport;
		
	    @Inject
	    @Path("context:js/soundmanager2.js")
	    private Asset jsSoundManager2Script;
	    
//	    @Inject
//	    private Environment environment;
	    
	    void beginRender(MarkupWriter writer) {
			_pageRenderSupport.addScriptLink(jsSoundManager2Script);
			
			//todo fix this when TAPESTRY-1393 is fixed
//			Document document = environment.peek(Document.class);
//			Element head = document.find("html/head");
//			if (head == null) return;
//			head.elementAt(0, "link", "rel", "stylesheet", "type", "text/css", "href", jsCalendarStylesheet
//					.toClientURL());
		}
	}

	
	
