package media.image.producer.jmf;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.media.Buffer;
import javax.media.Codec;
import javax.media.Format;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JFrame;

import media.image.RGBImageStacker;
import media.processors.BufferProcessorImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import persistence.XMLPersister;

public class PostAccessCodec implements Codec {
	
	
	// RGB or YUV or ...
	private VideoFormat format = null;
	
	private XMLPersister persist;
	private int[] keepData;
	private int cntDataAccumulated;
	private Thread thread = null;
	private BufferProcessorImpl buffer = null;
	private static JFrame imageFrame;
	private static RGBImageStacker imageStacker;

	// We'll advertize as supporting all video formats.
	protected Format supportedIns[] = new Format[] { new VideoFormat(null)};

	// We'll advertize as supporting all video formats.
	protected Format supportedOuts[] = new Format[] { new VideoFormat(null)};

	Format input = null, output = null;

	private static Log log = LogFactory.getLog(PostAccessCodec.class);
	
	public PostAccessCodec() {
		persist = new XMLPersister();
		//this.collector.setImageSink(this.sink);
	// TODO here we notified that our image is ready for UI showing, how to do it now?
		}
	

	/**
	 * Callback to access individual video frames.
	 */
	void accessFrame(Buffer frame) {
		processBuffer(frame);
	}

	private synchronized void processBuffer(Buffer frame) {
		if (buffer == null) {
			buffer = BufferProcessorImpl.createInstance();
		}

		// should we process ?
		if (!buffer.isProcessing()) {

			if (format == null) {
				format = (VideoFormat) frame.getFormat();
				//imageStacker = new RGBImageStacker(format);
			}

			byte[] originalData = (byte[]) frame.getData();
			
				log.info("getting data of length: " + originalData.length );
			
			/*
			imageStacker.stackData(originalData);

			if (imageStacker.isStacked()) {
				originalData = imageStacker.getOriginalStackData();
				// TODO look why this is needed and reinstate it
				//CloudFrame.imageContainer.setOriginalImage(originalData);
				//writeData(data);
				frame.setData(originalData);
				*/
				BufferToImage bufferto =
					new BufferToImage((VideoFormat) format);
				Image img = bufferto.createImage(frame);

				System.out.println(frame.getFormat().toString());
				//collector.addImage(img);
				/*
				ImageCommand command = new ImageCommand(img);
				command.execute();
				buffer.setImageData(img, imageStacker.getProcessedStackData());
				buffer.setWidth(img.getWidth(null));
				thread = new Thread(buffer, "buffer processor");
				buffer.setProcessing(true);
				thread.start();
			}
			*/
		}
	}


	/**
	 * The code for a pass through codec.
	 */


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

	// TODO delete
	private void writeData(short[] data) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt"));

			for (int counter = 0; counter != data.length; counter++) {
				writer.write(String.valueOf(data[counter]));
				writer.write('\n');
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object[] getControls() {
		return new Object[0];
	}

	public Object getControl(String type) {
		return null;
	}
}
