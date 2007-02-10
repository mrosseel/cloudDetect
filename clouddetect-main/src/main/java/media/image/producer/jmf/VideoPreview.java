package media.image.producer.jmf;

import javax.media.*;
import javax.media.protocol.*;
import javax.media.control.FormatControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * TODO not used, see if we can learn something and kill it
 * 
 * This class opens a video capture device and reads frames from it, displaying
 * them in a Canvas in a Frame.
 */
public class VideoPreview extends Frame implements BufferTransferHandler {

    /**
     * 
     */
    private static final long serialVersionUID = -2052105484941499448L;

    PushBufferDataSource cds = null;

    BufferToImage bti = null;

    Canvas canvas = null;

    // Preferred capture format parameters
    String prefEncoding = "YUV";

    float prefFPS = 30.0f;

    Dimension prefSize = new Dimension(160, 120);

    VideoFormat prefFormat = new VideoFormat(prefEncoding, prefSize,
            Format.NOT_SPECIFIED, // length
            null, // data type
            prefFPS);

    public VideoPreview() {
        super("Video Preview");
        setSize(320, 0);
        Vector devices = CaptureDeviceManager.getDeviceList(prefFormat);
        if (devices.size() < 1) {
            System.err.println("No device");
            System.exit(-1);
        }
        CaptureDeviceInfo cdi = (CaptureDeviceInfo) devices.elementAt(0);

        try {
            DataSource ds = Manager.createDataSource(cdi.getLocator());
            // Simplified way to set the capture format on the device
            // Might need to handle error conditions such as format not
            // settable, or device having zero elements in FormatControl
            // array.
            if (ds instanceof CaptureDevice) {
                FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
                for (int i = 0; i < cdi.getFormats().length; i++) {
                    VideoFormat vf = (VideoFormat) cdi.getFormats()[i];
                    if (vf.matches(prefFormat)) {
                        prefFormat = (VideoFormat) vf.intersects(prefFormat);
                        fcs[0].setFormat(prefFormat);
                        break;
                    }
                }
            }

            ds.connect();

            // Unsafe assumption that it is a PushBufferDataSource, but
            // it should work with existing capture data sources.
            cds = (PushBufferDataSource) ds;

            cds.getStreams()[0].setTransferHandler(this);
            cds.start();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(-1);
        }
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                try {
                    cds.getStreams()[0].setTransferHandler(null);
                    cds.stop();
                    cds.disconnect();
                } catch (Exception e) {
                    System.err.println(e);
                }
                System.exit(0);
            }
        });
    }

    Buffer buf = new Buffer();

    public void transferData(PushBufferStream pbs) {
        try {
            pbs.read(buf);
        } catch (java.io.IOException ioe) {
            System.err.println("read exception " + ioe);
        }
        if (bti == null) {
            VideoFormat vf = (VideoFormat) buf.getFormat();
            canvas = new Canvas();
            canvas.setSize(vf.getSize());
            add("North", canvas);
            pack();
            bti = new BufferToImage(vf);
            System.err.println("Capture format is " + vf);
        }

        // This image will be a BufferedImage if running with
        // JDK 1.2 or higher.
        Image im = bti.createImage(buf);

        // Insert image processing code here!
        Graphics gim = im.getGraphics();
        gim.setColor(Color.gray);
        gim.drawString("Java", 20, 20);
        gim.setColor(Color.white);
        gim.drawString("Java", 19, 19);

        // Draw the image on the canvas
        Graphics g = canvas.getGraphics();
        g.drawImage(im, 0, 0, this);
    }

    public static void main(String[] args) {
        (new VideoPreview()).setVisible(true);
    }
}