package media.image;

import java.io.File;

public class CloudFileResultImpl implements CloudFileResult {

	private int cloudStatus;
	private CloudResultMetaData metaData = new CloudResultMetaData();
	String originComment;
	String fileData;

	public int getCloudStatus() {
		return cloudStatus;
	}

	public void setCloudStatus(int cloudStatus) {
		this.cloudStatus = cloudStatus;
	}

	@Override
	public CloudResultMetaData getMetaData() {
		return this.metaData;
	}

	@Override
	public String getOriginComment() {
		return this.originComment;
	}

	@Override
	public void setMetaData(CloudResultMetaData metaData) {
		this.metaData = metaData;
	}

	@Override
	public void setOriginComment(String origin) {
		this.originComment = origin;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
}
