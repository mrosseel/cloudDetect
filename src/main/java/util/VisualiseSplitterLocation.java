package util;

import javax.swing.JFrame;

import ui.ImagePanel;
import media.image.CloudImage;

public class VisualiseSplitterLocation {

    public static void showImage(CloudImage image, int location) {
        double[] datax = image.getData();
        int value = Integer.MIN_VALUE;
        for (int dataCnt = location - image.getWidth(); dataCnt < location
                + image.getWidth() && dataCnt < datax.length; dataCnt++) {
            datax[dataCnt] = value;
            if (value == Integer.MAX_VALUE) {
                value = Integer.MIN_VALUE;
            } else {
                value = Integer.MAX_VALUE;
            }
        }
        image.setData(datax, image.getWidth(), image.getHeight());
        ImagePanel panel = new ImagePanel();
        JFrame frame = new JFrame();
        panel.setImage(image.getImage());
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
