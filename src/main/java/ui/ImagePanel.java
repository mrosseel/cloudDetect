/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Sep 3, 2003
 * Time: 11:25:26 PM
 * To change this template use Options | File Templates.
 */
package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ui.handlers.ImagePanelHandler;

public class ImagePanel extends JPanel {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static Log log = LogFactory.getLog(ImagePanel.class);

	private JPanel panel;

	private String txtName = "Current View"; // too specific, remove it

	public Image image;

	public Image currentImage;

	public ImagePanel() {
		int width = 160;
		int heigth = 120;
		this.setSize(width, heigth);
		this.setPreferredSize(new Dimension(width, heigth));
		this.setMinimumSize(new Dimension(width, heigth));
		new ImagePanelHandler(this);
	}

	private void initComponents(String txtName) {
		int width = image.getWidth(this);
		int heigth = image.getHeight(this);
		this.setSize(width, heigth);
		this.setPreferredSize(new Dimension(width, heigth));
	}

	/***************************************************************************
	 * 
	 * @param graphics
	 **************************************************************************/
	// public void paint(Graphics graphics) {
	// if (image != currentImage) {
	// currentImage = image;
	// graphics.drawImage(currentImage, 0, 0, this);
	// repaint();
	// }
	//		
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics graphics) {
		if (image != null) {
			currentImage = image;
			graphics.drawImage(currentImage, 0, 0, this);
			// repaint();
		}
	}

	private void exitForm(WindowEvent windowEvent) {
		System.exit(0);
	}

	/**
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(Image image) {
		if (image == null) {
			if (log.isDebugEnabled()) {
				log.debug("Skipping empty image.");
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("inside setimage");
			}
			this.image = image;

			if (currentImage == null) {
				initComponents(txtName);
			}
			repaint();
		}
	}

}
