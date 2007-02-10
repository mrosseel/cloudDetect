package notification;

import media.image.CloudImage;

public interface Notifier {

    public abstract void notify(CloudImage image);
}