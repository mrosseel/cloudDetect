/**
 * One implementation of how to process a buffer 
 */
package media.processors;

import java.awt.Image;
import java.io.File;

import metrics.CutoffDifferenceMetric;
import metrics.LineMedianDifferenceMetric;
import metrics.PixelBrightnessMetric;

import org.jCharts.chartData.ChartDataException;

import ui.LineCharts;
import util.SoundUtil;

import com.mhuss.AstroLib.DateOps;
import com.mhuss.AstroLib.Lunar;
import com.mhuss.AstroLib.NoInitException;

public class BufferProcessorImpl implements BufferProcessor {
	private boolean isProcessing = false;
	private double[] data;
	private int width;
	private static BufferProcessorImpl instance;
	SoundUtil util;
	File sound; 

	private BufferProcessorImpl() {
		util = new SoundUtil();
		sound = new File((BufferProcessorImpl.class.getResource("/conf/sounds/rooster.wav")).getFile());
	}

	public static BufferProcessorImpl createInstance() {
		if (instance == null) {
			instance = new BufferProcessorImpl();
		}
		return instance;
	}

	public void process(double[] data) {
		LineCharts lineCharts= new LineCharts();
		try {
			lineCharts.run();
		} catch (ChartDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(
			"-------------------------------------------------------");

		// calculate extremes
		double smallest = Double.MAX_VALUE;
		double biggest = Double.MIN_VALUE;
		for (int i = 0; i != data.length; i++) {
			if (data[i] > biggest) {
				biggest = data[i];
			}
			if (data[i] < smallest) {
				smallest = data[i];
			}
		}

		//        if (persist == null)
		//            persist = new XMLPersister();
		//        if (keepData == null) {
		//            resetKeepData(data.length);
		//        }
		//
		//        for (int i = 0; i != data.length; i++) {
		//            keepData[i] = keepData[i] + data[i];
		//        }
		//        cntDataAccumulated++;

		double jd = DateOps.nowToDoubleDay();
		Lunar lunar = new Lunar(jd - 51544.5);
		double illuminated = -1;

		try {
			lunar.calcFundamentals(jd);
			illuminated = lunar.illuminatedFraction();
		} catch (NoInitException e) {
			e.printStackTrace();
		}

		//        if(cntDataAccumulated == 1) {
		//            for(int i=0;i!= data.length;i++) {
		//                data[i] = (short) (keepData[i]);
		//            }
		//        }

		System.out.println("length = " + data.length);
		System.out.println("biggest = " + biggest + " smallest = " + smallest);
		//            System.out.println("std dev = " + StandardDeviation.sdFast(data));
		//System.out.println("jd = " + jd);
		System.out.println("illuminated = " + illuminated);

		LineMedianDifferenceMetric metric = new LineMedianDifferenceMetric();
		//CutoffDifferenceMetric metric = new CutoffDifferenceMetric();
		metric.setWidth(this.width);
		//		MaxStdDevMetric stdev = new MaxStdDevMetric();
		double result = metric.compute(data);
		PixelBrightnessMetric brightness = new PixelBrightnessMetric();
		System.out.println("brightness = " + brightness.compute(data));

		if(result<1) {
			util.play(sound, 1);
		}
//		BufferedWriter writer;
//		try {
//			writer = new BufferedWriter(new FileWriter("results.txt", true));
//			writer.write(String.valueOf(result));
//			writer.write('\n');
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//        persist.addRecord(StandardDeviation.sdFast(data), illuminated, 0.2, "bla");
		//
		//        persist.writeFile();
		// resetKeepData(keepData.length);
	}

	public synchronized void run() {
		process(data);
		setProcessing(false);

	}

	public double[] getData() {
		return data;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public synchronized boolean isProcessing() {
		return isProcessing;
	}

	public synchronized void setProcessing(boolean processing) {
		isProcessing = processing;
	}

	/**
	 * @param img
	 * @param data
	 */
	public void setImageData(Image img, double[] data) {
		this.data = data;
	}
}
