/**
 * Created by IntelliJ IDEA.
 * User: Mike
 * Date: Sep 9, 2003
 * Time: 7:01:28 PM
 * To change this template use Options | File Templates.
 */
package processors;

import metrics.MaxStdDevMetric;
import metrics.Metric;

import com.mhuss.AstroLib.DateOps;
import com.mhuss.AstroLib.Lunar;
import com.mhuss.AstroLib.NoInitException;

public class BufferProcessorImpl implements BufferProcessor {
    private boolean isProcessing = false;
    private short[] data;


    public void process(short[] data) {
        // calculate extremes
        short smallest = Short.MAX_VALUE, biggest = Short.MIN_VALUE;
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
        Lunar lunar = new Lunar(jd);
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
        System.out.println("jd = " + jd);
        System.out.println("illuminated = " + illuminated);
        Metric stdev = new MaxStdDevMetric();
        System.out.println("stdev = " + stdev.compute(data));
//        persist.addRecord(StandardDeviation.sdFast(data), illuminated, 0.2, "bla");
//
//        persist.writeFile();
        // resetKeepData(keepData.length);

    }


    public void run() {
        setProcessing(true);
        process(data);
        setProcessing(false);
    }

    public short[] getData() {
        return data;
    }

    public void setData(short[] data) {
        this.data = data;
    }


    public synchronized boolean isProcessing() {
        return isProcessing;
    }

    private synchronized void setProcessing(boolean processing) {
        isProcessing = processing;
    }

}
