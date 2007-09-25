package calculation;

import persistence.model.CloudJudgeLimits;

/**
 * Nothing to do with metrics, just a POJO which knows how to judge cloud
 * results *
 */
public class CloudJudge {
    public enum CloudStatus {
        CLEAR, PARTIALLY_CLEAR, CLOUDED
    };
    
    private CloudJudgeLimits limits;

    public CloudStatus judgeClouds(double result) {
        if (result <= limits.getMaxClear()) {
            return CloudStatus.CLEAR;
        } else if (result <= limits.getMaxPartlyClear()) {
            return CloudStatus.PARTIALLY_CLEAR;
        }

        return CloudStatus.CLOUDED;
    }

    public CloudJudgeLimits getCloudJudgeLimits() {
		return limits;
	}

	public void setCloudJudgeLimits(CloudJudgeLimits limits) {
		this.limits = limits;
	}
}
