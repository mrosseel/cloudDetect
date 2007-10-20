/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Image;

/**
 * @author Mike
 * 
 * This is a wrapper for an Image, with convenience methods to convert it to an
 * array of primitives if that's more convenient for our calculations.
 * 
 */
public interface CloudImage {

    public Image getImage();

    public double[] getData();
    
    public void setData(double[] data, int width, int heigth);
    
    public double[] getMonochromeData();

    public int getHeight();
    
    public int getWidth();

    /**
     * Get where the image comes from
     * @return
     */
    public String getOriginComment();

    /**
     * Set where the image comes from
     * @return
     */
    public void setOriginComment(String origin);
    
    public CloudImageMetaData getMetaData();
    
    public void setMetaData(CloudImageMetaData metaData);
    
}
