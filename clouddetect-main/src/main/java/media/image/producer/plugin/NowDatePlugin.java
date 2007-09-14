package media.image.producer.plugin;

import media.image.CloudImage;
import util.DateUtil;

/**
 * Inserts the current date/time into the image metadata.
 * 
 * The timezone is UT so we can easily calculate this for every user's preferences.
 * 
 * @author mike
 *
 */
public class NowDatePlugin implements ImageDatePlugin {

    public void insertDateInMetaData(CloudImage image, String filename) {
        image.getMetaData().setDate(DateUtil.getCurrentDate());
    }
}
