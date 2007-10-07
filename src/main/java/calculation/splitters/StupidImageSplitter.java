package calculation.splitters;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StupidImageSplitter extends AbstractImageSplitter {
    private static Log log = LogFactory.getLog(StupidImageSplitter.class);

    public void split(CloudImage data) {
        double currentResult;
        int length = data.getHeight() * data.getWidth();
        try {
            for (int location = 2; location < length - 2; location++) {
                currentResult = metric.compute(data, location);
                if (log.isDebugEnabled()) {
                    log.debug("Testing location " + location + " result = " + getResult() + " current=" + currentResult + " bestloc = " + getBestSplitterLocation());
                }
                if (currentResult > getResult()) {
                    setResult(currentResult);
                    setBestSplitterLocation(location);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
