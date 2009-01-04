package notification;

import media.image.CloudImageResult;
import media.image.CloudResult;

public class DummyNotify implements Notifier<CloudResult> {

    public void notify(CloudResult image) {
        //  let's not do anything
    }

}
