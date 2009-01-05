package media.image;


public interface CloudFileResult extends CloudResult {
	public int getCloudStatus();
	public void setCloudStatus(int cloudStatus);
	public String getFileData();
	public void setFileData(String fileData);
}
