package media.image.producer.plugin;

import media.image.CloudImage;

public interface ImageDatePlugin {

    public void insertDateInMetaData(CloudImage image, String filename);
}
