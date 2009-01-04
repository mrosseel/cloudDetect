package notification;

import media.audio.PlayWarning;
import media.image.CloudResult;

public class SoundNotify implements Notifier<CloudResult> {

    public void notify(CloudResult image) {
        PlayWarning.playRooster();
    }

}
