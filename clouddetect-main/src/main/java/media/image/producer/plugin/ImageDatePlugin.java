package media.image.producer.plugin;

import media.image.CloudImageResult;

public interface ImageDatePlugin {

    public void insertDateInMetaData(CloudImageResult image, String filename);
}
