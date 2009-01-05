package media.image;

public interface CloudResult {
	
    /**
     * Get where the image comes from
     * 
     * @return
     */
    public String getOriginComment();

    /**
     * Set where the image comes from
     * 
     * @return
     */
    public void setOriginComment(String origin);

    public CloudResultMetaData getMetaData();

    public void setMetaData(CloudResultMetaData metaData);

}
