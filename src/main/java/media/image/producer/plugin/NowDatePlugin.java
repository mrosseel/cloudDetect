package media.image.producer.plugin;

import java.util.Date;

import media.image.CloudImage;

public class NowDatePlugin implements ImageDatePlugin {

    public void insertDateInMetaData(CloudImage image, String filename) {
        image.getMetaData().setDate(new Date());
    }
}
