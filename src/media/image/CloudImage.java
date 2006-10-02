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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CloudImage {

	private Image image;
	private double[] data;
	
	public CloudImage(Image image) {
		setImage(image);
	}
	
	public CloudImage(double[] data) {
		setData(data);
	}
	
	 public void setImage(Image image) {
	 	this.image = image;
	 	setData(image);
	 }
	 
	 public void setData(double[] data){
	 	this.data = data;
	 	setImage(data);
	 }
	 
	 private void setData(Image image) {
	 	
	 }
	 
	 private void setImage(double[] data) {
	 	
	 }
	
}
