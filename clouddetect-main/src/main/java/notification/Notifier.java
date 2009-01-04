package notification;

import media.image.CloudImageResult;

public interface Notifier<T> {

    public abstract void notify(T content);
}