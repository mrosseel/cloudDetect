package calculation;

/**
 * Nothing to do with metrics, just a POJO which knows how to judge cloud
 * results *
 */
public class CloudJudge {
    public enum CloudStatus {
        CLEAR, PARTIALLY_CLEAR, CLOUDED
    };

    private double maxClear;

    private double maxPartialClear;

    public CloudStatus judgeClouds(double result) {
        if (result <= maxClear) {
            return CloudStatus.CLEAR;
        } else if (result <= maxPartialClear) {
            return CloudStatus.PARTIALLY_CLEAR;
        }

        return CloudStatus.CLOUDED;
    }

    public double getMaxClear() {
        return maxClear;
    }

    public void setMaxClear(double maxClear) {
        this.maxClear = maxClear;
    }

    public double getMaxPartialClear() {
        return maxPartialClear;
    }

    public void setMaxPartialClear(double maxPartialClear) {
        this.maxPartialClear = maxPartialClear;
    }
}
