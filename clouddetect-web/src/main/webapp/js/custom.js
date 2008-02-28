	// lang javascript
	Ajax.Responders.register({
		onCreate: function() {
		 if($('busy') && Ajax.activeRequestCount>0)
		 Effect.Appear('busy',{duration:0.5,queue:'end'});
		},
		onComplete: function() { if($('busy') && Ajax.activeRequestCount==0) Effect.Fade('busy',{duration:0.5,queue:'end'});
		}
	});