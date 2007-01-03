package notification;

import media.audio.PlayWarning;
import media.image.CloudImage;

public class SoundNotify implements Notifier {

    public void notify(CloudImage image) {
        PlayWarning.playRooster();
    }

}
