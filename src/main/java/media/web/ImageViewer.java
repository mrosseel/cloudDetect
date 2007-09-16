/*
 * Created on 27-aug-2004
 *
 */
package media.web;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Show an image and a title in a new Frame.
 * 
 * @author mikers
 *
 */
public class ImageViewer {
    public static void showImage(Image image, String title) {
        Frame frame = new myFrame(image);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setSize(image.getWidth(null), image.getHeight(null));
        frame.setTitle(title);
        frame.setVisible(true);
    }
}

class myFrame extends Frame {
    private Image image;

    public myFrame(Image image) {
        super();
        this.image = image;
    }

    public void paint(Graphics graphics) {
        graphics.drawImage(image, 0, 0, null);
    }
}