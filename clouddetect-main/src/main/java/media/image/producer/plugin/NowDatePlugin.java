package media.image.producer.plugin;

import media.image.CloudImage;
import util.DateUtil;

public class NowDatePlugin implements ImageDatePlugin {

    public void insertDateInMetaData(CloudImage image, String filename) {
        image.getMetaData().setDate(DateUtil.getCurrentDate());
    }
}
