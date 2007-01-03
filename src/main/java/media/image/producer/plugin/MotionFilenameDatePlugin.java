package media.image.producer.plugin;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import media.image.CloudImage;
import media.image.CloudImageMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.DateUtil;

public class MotionFilenameDatePlugin implements ImageDatePlugin {
    private static Log log = LogFactory.getLog(MotionFilenameDatePlugin.class);

    public void insertDateInMetaData(CloudImage image, String filename) {
        CloudImageMetaData metaData = image.getMetaData();
        Calendar calendar = DateUtil.getCurrentCalendar();

        Pattern p = Pattern
                .compile("(?i).*(\\d\\d)[-](\\d\\d\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)[-].*");
        Matcher m = p.matcher(filename);
        if (m.find() && m.groupCount() == 7) {
            calendar.set(Integer.valueOf(m.group(2)), Integer.valueOf(m
                    .group(3)), Integer.valueOf(m.group(4)), Integer.valueOf(m
                    .group(5)), Integer.valueOf(m.group(6)), Integer.valueOf(m
                    .group(7)));
            // calendar.set(Calendar.MILLISECOND, 0);

        } else {
            log.warn("Could not determine date and time from filename: "
                    + filename);
            // calendar set to current time, no need to change this.
        }

        metaData.setDate(calendar.getTime());
    }

}
