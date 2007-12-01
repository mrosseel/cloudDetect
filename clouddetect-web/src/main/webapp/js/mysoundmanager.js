

 soundManager.url = '/js/soundmanager2.swf'; // path to movie
 soundManager.onload = function() {
// 	soundManager._writeDebug('soundManager.onload() - my code executes here');
 	soundManager.createSound({
		 id: 'mySound', // required
		 url: soundToPlay,
		 // optional sound parameters here, see Sound Properties for full list
		 volume: 100,
		 autoPlay: true,
		 multishot: true,
		 onfinish: playSound
	 });

 	nrTimes = 0;
	stopSound = false; 
	playSound();
}

function playSound() {

 	if (nrTimes < timesToPlaySound && !stopSound)
    {
	 	nrTimes++;	
	 	soundManager.play('mySound');

 	}
}