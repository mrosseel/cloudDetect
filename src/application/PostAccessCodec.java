package application;

import util.StandardDeviation;

import javax.media.Format;
import javax.media.Buffer;
import javax.media.Codec;
import javax.media.util.BufferToImage;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;

import persistence.XMLPersister;
import com.mhuss.AstroLib.DateOps;
import com.mhuss.AstroLib.Lunar;
import com.mhuss.AstroLib.NoInitException;
import metrics.*;
import processors.BufferProcessorImpl;

public class PostAccessCodec implements Codec {
    XMLPersister persist;
    private int[] keepData;
    private int cntDataAccumulated;
    private Thread thread = null;
    private BufferProcessorImpl buffer = null;

    // We'll advertize as supporting RGBFormat.
    public PostAccessCodec() {
        supportedIns = new Format[]{
            new RGBFormat()
        };

        persist = new XMLPersister();
    }

    /**
     * Callback to access individual video frames.
     */
    void accessFrame(Buffer frame) {
        short[] data = (short[]) frame.getData();
        VideoFormat format = new VideoFormat("fsa");
        BufferToImage bufferto = new BufferToImage(format);
        bufferto.createImage(frame);


        if (thread == null) {
            buffer = new BufferProcessorImpl();
            thread = new Thread(buffer, "buffer");
        }

        if (!buffer.isProcessing()) {
            buffer.setData(data);
            thread = new Thread(buffer, "buffer");
            thread.start();
        }


    }

    private void resetKeepData(int length) {
        keepData = new int[length];
        cntDataAccumulated = 0;
    }

    /**
     * The code for a pass through codec.
     */

    // We'll advertize as supporting all video formats.
    protected Format supportedIns[] = new Format[]{
        new VideoFormat(null)
    };

    // We'll advertize as supporting all video formats.
    protected Format supportedOuts[] = new Format[]{
        new VideoFormat(null)
    };

    Format input = null, output = null;

    public String getName() {
        return "Post-Access Codec";
    }

    // No op.
    public void open() {
    }

    // No op.
    public void close() {
    }

    // No op.
    public void reset() {
    }

    public Format[] getSupportedInputFormats() {
        return supportedIns;
    }

    public Format[] getSupportedOutputFormats(Format in) {
        if (in == null)
            return supportedOuts;
        else {
            // If an input format is given, we use that input format
            // as the output since we are not modifying the bit stream
            // at all.
            Format outs[] = new Format[1];
            outs[0] = in;
            return outs;
        }
    }

    public Format setInputFormat(Format format) {
        input = format;
        return input;
    }

    public Format setOutputFormat(Format format) {
        output = format;
        return output;
    }

    public int process(Buffer in, Buffer out) {

        // This is the "Callback" to access individual frames.
        accessFrame(in);

        // Swap the data between the input & output.
        Object data = in.getData();
        in.setData(out.getData());
        out.setData(data);

        // Copy the input attributes to the output
        out.setFormat(in.getFormat());
        out.setLength(in.getLength());
        out.setOffset(in.getOffset());

        return BUFFER_PROCESSED_OK;
    }

    public Object[] getControls() {
        return new Object[0];
    }

    public Object getControl(String type) {
        return null;
    }
}
