package util;


import javax.swing.JFrame;

import media.image.CloudImageResult;

public class VisualiseSplitterLocation {

    public static void showImage(CloudImageResult image, int location) {
        try {
            image = addSplitterLine(image, location);
            ImagePanel panel = new ImagePanel();
            JFrame frame = new JFrame();
            panel.setImage(image.getImage());
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
            Thread.sleep(10000);
        }

        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloudImageResult addSplitterLine(CloudImageResult image, int location) {
        double[] datax = image.getData();
        int value = Integer.MIN_VALUE;
        for (int dataCnt = Math.max(0, location - image.getWidth()); dataCnt < location
                + image.getWidth()
                && dataCnt < datax.length; dataCnt++) {
            datax[dataCnt] = value;
            if (value == Integer.MAX_VALUE) {
                value = Integer.MIN_VALUE;
            } else {
                value = Integer.MAX_VALUE;
            }
        }
        image.setData(datax, image.getWidth(), image.getHeight(), image.isColor());
        return image;
    }

}
