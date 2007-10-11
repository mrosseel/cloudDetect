function doitagain() {


 	while (nrTimes <= 10 && !stopSound)
    {
	 	nrTimes++;	
	 	soundManager.play('mySound');

 	}
}

 soundManager.url = '/js/soundmanager2.swf'; // path to movie
 soundManager.onload = function() {
 	soundManager._writeDebug('soundManager.onload() - my code executes here');
 	soundManager.createSound({
		 id: 'mySound', // required
		 url: '/js/alert.wav.mp3',
		 // optional sound parameters here, see Sound Properties for full list
		 volume: 100,
		 autoPlay: true,
		 multishot: true,
		 onbeforefinish: doitagain
	 });

 	nrTimes = 0;	 
	stopSound = false; 
 	soundManager.play('mySound');
 	
}

