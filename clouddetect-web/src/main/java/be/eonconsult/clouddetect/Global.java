package be.eonconsult.clouddetect;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Global {
    private static boolean googleTracking;

    private static Log log = LogFactory.getLog(Global.class);
    
    public Global() {
        // Read properties file.
        Properties properties = new Properties();
        try {
            properties.load(Global.class
                    .getResourceAsStream("/clouddetect-web.properties"));
            googleTracking = Boolean.valueOf((String) properties
                    .get("google.tracking"));
            log.info("Clouddetect Google tracking=" + googleTracking);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isTracking() {
        return googleTracking;
    }

    public static void setTracking(boolean tracking) {
        googleTracking = tracking;
    }

}
