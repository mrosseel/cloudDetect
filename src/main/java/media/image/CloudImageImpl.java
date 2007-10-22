/*
 * Created on 28-mrt-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package media.image;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.jmx.mbeanserver.MetaData;

import util.ImageToolkit;

/**
 * @author Mike
 * 
 * This is a wrapper for an Image, with convenience methods to convert it to an
 * array of primitives if that's more convenient for our calculations.
 * 
 * TODO check for null conditions!
 */
public class CloudImageImpl implements CloudImage {
    private static Log log = LogFactory.getLog(CloudImageImpl.class);

    /** where does this image come from? */
    private String originComment;

    private Image image;

    private double[] data;

    private int width;

    private int height;

    private Color color;

    private CloudImageMetaData metaData = new CloudImageMetaData();

    public CloudImageImpl(Image image) {
        if (image == null) {
            log.error("image passed to constructor is null");
        }
        setImage(image);
    }

    public CloudImageImpl(double[] data, int width, int height) {
        setData(data, width, height);
    }

    public CloudImageImpl(int[] data, int width, int height) {
        setData(intToDoubleArray(data), width, height);
    }

    private void setImage(Image image) {
        this.image = image;
        this.data = null;
        setWidth(image.getWidth(null));
        setHeight(image.getHeight(null));
    }

    public Image getImage() {
        if (image == null) {
            this.image = createImage(doubleToIntArray(getData()), getWidth(),
                    getHeight());
        }

        return this.image;
    }

    public void setData(double[] data, int width, int height) {
        this.data = data;
        this.image = null;
        setWidth(width);
        setHeight(height);
    }

    public double[] getData() {
        if (data == null) {
            this.data = createData(image);
        }

        return this.data;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private double[] createData(Image image) {
        return intToDoubleArray(getIntArrayFromImage(image));
    }

    private Image createImage(int[] data, int width, int height) {
        MemoryImageSource memImg = new MemoryImageSource(width, height, data,
                0, width);
        return Toolkit.getDefaultToolkit().createImage(memImg);
    }

    public String getOriginComment() {
        return originComment;
    }

    public void setOriginComment(String origin) {
        this.originComment = origin;
    }

    private double[] intToDoubleArray(int[] data) {
        double[] result = new double[data.length];
        for (int dataCounter = 0; dataCounter < data.length; dataCounter++) {
            result[dataCounter] = data[dataCounter];
        }
        return result;
    }

    private int[] doubleToIntArray(double[] data) {
        int[] result = new int[data.length];
        for (int dataCounter = 0; dataCounter < data.length; dataCounter++) {
            result[dataCounter] = (int) Math.round(data[dataCounter]);
        }
        return result;
    }

    // ////////// example code for pixelgrabber and memoryimagesource

    private int[] getIntArrayFromImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        int[] pixstart = new int[width * height];
        grab(image, pixstart, width, height);
        return pixstart;
        // System.arraycopy(pixstart, 0, pixbuf, 0, width * height);
        // memImg = new MemoryImageSource(width, height, pixbuf, 0, width);
        // memImg.setAnimated(true);
        // Image actual = Toolkit.getDefaultToolkit().createImage(memImg);
    }

    protected void grab(Image img, int pix[], int width, int height) {
        // Kopiert die Bilddaten des ?bergebenen
        // Bildes in das ?bergebene Array
        PixelGrabber grabber = new PixelGrabber(img, 0, 0, width, height, pix,
                0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double[] getMonochromeData() {
        double[] data = getData();
        double[] convertedData = new double[data.length];
        for (int i = 0; i < convertedData.length; i++) {
            convertedData[i] = ImageToolkit.getMonochrome((int) Math
                    .round(data[i])) / 2.55;
        }
        return convertedData;
    }

    public CloudImageMetaData getMetaData() {
        // TODO Auto-generated method stub
        return metaData;
    }

    public void setMetaData(CloudImageMetaData metaData) {
        this.metaData = metaData;
    }
}
