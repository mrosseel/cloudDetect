//	google.load("maps", "2.78");
//	google.load("search", "1"`);


	// Our global state
    var gMap;

    // Create our "tiny" marker icon
    var gSmallIcon = new GIcon();
	gSmallIcon.image = "/images/weather/medium/weather-clear-night.png";	
    gSmallIcon.shadow = "/images/weather/medium/shadow.png";
    gSmallIcon.iconSize = new GSize(22, 22);
    gSmallIcon.shadowSize = new GSize(22, 22);
    gSmallIcon.iconAnchor = new GPoint(6, 20);
    gSmallIcon.infoWindowAnchor = new GPoint(5, 1);

	// ajax stuff
	function loadWebcams() {
		 // Create the XMLHttpRequest object
        var request = GXmlHttp.create();
        // Prepare an asynchronous HTTP request to the server
        request.open("GET", "/webcamlist", true);
        // Returned data will be processed by this function
        request.onreadystatechange = getCallbackFunction(request, processWebcamData);
        // Send the query
        request.send(null);
	}
	
	function getCallbackFunction(req, processData) {
          // Return an anonymous function that listens to the
          // XMLHttpRequest instance
          return function () {

            // If the request's status is "complete"
            if (req.readyState == 4) {
              if (req.status == 200) {

                // Pass the XML payload of the response to the
                // handler function
                processData(req.responseXML);

              } else {

                // An HTTP problem has occurred
                alert("HTTP error: "+req.status);
              }
            }
          }
        }

        function processWebcamData(xmlDoc) {
            // obtain the array of markers and loop through it
            var siteMarkers = xmlDoc.documentElement.getElementsByTagName("persistence.model.Feed");
            displaySitesMarkers(siteMarkers);
        }

        function displaySitesMarkers(siteMarkers) {
            gMap.clearOverlays();
            for (var i = 0; i < siteMarkers.length; i++) {
                // obtain the attributes of each marker
                var lat = parseFloat(siteMarkers[i].getElementsByTagName("latitude")[0].firstChild.nodeValue);
                var lng = parseFloat(siteMarkers[i].getElementsByTagName("longitude")[0].firstChild.nodeValue);
                var id = siteMarkers[i].getElementsByTagName("id")[0].firstChild.nodeValue;
                var name = siteMarkers[i].getElementsByTagName("name")[0].firstChild.nodeValue;
                if(siteMarkers[i].getElementsByTagName("LocationFreeForm").length != 0) {
	                var freeForm = siteMarkers[i].getElementsByTagName("LocationFreeForm")[0].firstChild.nodeValue;
                }
                var url = siteMarkers[i].getElementsByTagName("source")[0].firstChild.nodeValue;
                marker = createMarker(new GLatLng(lat,lng),name, freeForm,id, url);
                gMap.addOverlay(marker);
            }
        }
        
         // Creates a marker at the given point with the given number label
		function createMarker(point, name, freeForm, id, url) {
		  var marker = new GMarker(point, {icon:gSmallIcon});
		  GEvent.addListener(marker, "click", function() {
		    marker.openInfoWindowHtml('<b>' + name + '</b><p/>' + freeForm + '<p/><a href="details/' + id + '">More details...</a>');
		  });
		  return marker;
		}
		
	
		  

    // Set up the map and the local searcher.
    function OnLoad() {
	  loadWebcams();

      // Initialize the map
      gMap = new GMap2(document.getElementById("map"));
      gMap.addControl(new GSmallMapControl());
      gMap.addControl(new GMapTypeControl());
      gMap.addControl(new GOverviewMapControl());
      gMap.setCenter(new GLatLng(51.200001, 2.870000), 6);
      gMap.enableDoubleClickZoom();
      gMap.enableContinuousZoom();
      gMap.enableScrollWheelZoom();
      // Initialize the local searcher
      // request that tabular search results should be suppressed
      var options = {
		resultList : google.maps.LocalSearch.RESULT_LIST_SUPPRESS,
		searchFormHint : "Example Search: Londen, UK"
	};

	
      gMap.addControl(new google.maps.LocalSearch(options),
      new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(60,0))
      );
//      searchControl.focus();
    }
    
    	///prevent page scroll

		function wheelevent(e)
		{
				if (!e){
					e = window.event
				}
				if (e.preventDefault){
					e.preventDefault()
				}
				e.returnValue = false;
		}
		
    
    //google.setOnLoadCallback(OnLoad);
      GSearch.setOnLoadCallback(OnLoad);
//      GSearch.setUnLoadCallback(GUnLoad);
		GEvent.addDomListener(gMap.getContainer(), "DOMMouseScroll", wheelevent);
		gMap.getContainer().onmousewheel = wheelevent; 

