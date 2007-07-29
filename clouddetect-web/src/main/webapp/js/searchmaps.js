// Our global state
    var gLocalSearch;
    var gMap;
    var gSelectedResults = [];
    var gCurrentResults = [];
    var gSearchForm;

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
      gSearchForm = new GSearchForm(false, document.getElementById("searchform"));
      gSearchForm.setOnSubmitCallback(null, CaptureForm);
      gSearchForm.input.focus();

      // Initialize the map
      gMap = new GMap(document.getElementById("map"));
      gMap.addControl(new GSmallMapControl());
      gMap.addControl(new GMapTypeControl());
      gMap.addControl(new GOverviewMapControl());
      gMap.setCenter(new GLatLng(51.200001, 2.870000), 6);
      gMap.enableScrollWheelZoom();
      // Initialize the local searcher
      gLocalSearch = new GlocalSearch();
      gLocalSearch.setCenterPoint(gMap);
      gLocalSearch.setSearchCompleteCallback(null, OnLocalSearch);
      
      // Execute the initial search
      //gSearchForm.execute("italian restaurants");
    }

    // Called when Local Search results are returned, we clear the old
    // results and load the new ones.
    function OnLocalSearch() {

      if (!gLocalSearch.results) return;
      var searchWell = document.getElementById("searchwell");

      // Clear the map and the old search well
      searchWell.innerHTML = "";
      for (var i = 0; i < gCurrentResults.length; i++) {
        if (!gCurrentResults[i].selected()) {
          gMap.removeOverlay(gCurrentResults[i].marker());
        }
      }

      gCurrentResults = [];
      for (var i = 0; i < gLocalSearch.results.length; i++) {
        gCurrentResults.push(new LocalResult(gLocalSearch.results[i]));
      }
 /*   
      var attribution = gLocalSearch.getAttribution();
      if (attribution) {
        document.getElementById("searchwell").appendChild(attribution);
      }
*/
      // move the map to the first result
      var first = gLocalSearch.results[0];

      gMap.recenterOrPanToLatLng(new GPoint(parseFloat(first.lng), parseFloat(first.lat)));
      gMap.setZoom(10);
    }

    // Cancel the form submission, executing an AJAX Search API search.
    function CaptureForm(searchForm) {
      gLocalSearch.execute(searchForm.input.value);
      return false;
    }

    // A class representing a single Local Search result returned by the
    // Google AJAX Search API.
    function LocalResult(result) {
    /*
      this.result_ = result;
      this.resultNode_ = this.unselectedHtml();
      document.getElementById("searchwell").appendChild(this.resultNode_);
      gMap.addOverlay(this.marker(gSmallIcon));
      */
    }

    // Returns the GMap marker for this result, creating it with the given
    // icon if it has not already been created.
    LocalResult.prototype.marker = function(opt_icon) {
      if (this.marker_) return this.marker_;
      var marker = new GMarker(new GLatLng(parseFloat(this.result_.lat),
                                         parseFloat(this.result_.lng)),
                               opt_icon);
      GEvent.bind(marker, "click", this, function() {
        marker.openInfoWindow(this.selected() ? this.selectedHtml() :
                                                this.unselectedHtml());
      });
      this.marker_ = marker;
      return marker;
    }

    // "Saves" this result if it has not already been saved
    LocalResult.prototype.select = function() {
      if (!this.selected()) {
        this.selected_ = true;

        // Remove the old marker and add the new marker
        gMap.removeOverlay(this.marker());
        this.marker_ = null;
        gMap.addOverlay(this.marker(G_DEFAULT_ICON));

        // Add our result to the saved set
        document.getElementById("selected").appendChild(this.selectedHtml());

        // Remove the old search result from the search well
        this.resultNode_.parentNode.removeChild(this.resultNode_);
      }
    }

    // Returns the HTML we display for a result before it has been "saved"
    LocalResult.prototype.unselectedHtml = function() {
      var container = document.createElement("div");
      container.className = "unselected";
      container.appendChild(this.result_.html.cloneNode(true));
      var saveDiv = document.createElement("div");
      saveDiv.className = "select";
      saveDiv.innerHTML = "Save this location";
      GEvent.bindDom(saveDiv, "click", this, function() {
        gMap.closeInfoWindow();
        this.select();
        gSelectedResults.push(this);
      });
      container.appendChild(saveDiv);
      return container;
    }

    // Returns the HTML we display for a result after it has been "saved"
    LocalResult.prototype.selectedHtml = function() {
      return this.result_.html.cloneNode(true);
    }

    // Returns true if this result is currently "saved"
    LocalResult.prototype.selected = function() {
      return this.selected_;
    }
    
    GSearch.setOnLoadCallback(OnLoad);