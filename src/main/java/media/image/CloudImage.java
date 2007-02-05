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

    public int getHeight();
    
    public int getWidth();

    public String getOrigin();

    public void setOrigin(String origin);
}
