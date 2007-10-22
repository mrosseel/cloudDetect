package util;

/**
 * Contains static methods that calculate descriptive statistics for the
 * specified data. Use the methods in this class when you need to calculate
 * <em>one</em> or a few statistics of a small data set (that fits in memory).
 * If you want to calculate all (or most) of the descriptive statistics, you
 * should use the {@link StatsMethods} class that is optimised for that very
 * task. If your data set doesn't fit into memory, you should use
 * {@link com.synes.util.Statistics}.
 * 
 * @author <a href="mailto:Mike.Rosseel@synes.com">Mike Rosseel</a> (original)
 * @author $Author: bvanvrec $
 * @version $Revision: 1.4 $
 */
public class StatisticsToolkit {
    public static double[] variance(double[][] aSamples, int aDimensionality,
            double[] aMeans) {
        double[] result = new double[aDimensionality];
        double factor = 1.0 / (aSamples.length - 1);
        int samplesLoop, dimensionLoop;

        for (samplesLoop = 0; samplesLoop != aSamples.length; samplesLoop++) {
            for (dimensionLoop = 0; dimensionLoop != aDimensionality; dimensionLoop++)
                result[dimensionLoop] += Math
                        .pow(
                                (aSamples[samplesLoop][dimensionLoop] - aMeans[dimensionLoop]),
                                2);
        }

        for (dimensionLoop = 0; dimensionLoop != aDimensionality; dimensionLoop++)
            result[dimensionLoop] *= factor;

        return result;
    }

    /**
     * Returns the mean of the elements of <code>aData</code> starting from
     * index <code>aFrom</code> and ending <em>before</em> <code>aTo</code>.
     * 
     * @param aData
     *            sample data.
     * @param aFrom
     *            index of first element.
     * @param aTo
     *            index of element right after the last element.
     * @return the mean of the specified data.
     */
    public static double mean(double[] aData, int aFrom, int aTo) {
        double sum = 0;
        for (int i = aFrom; i != aTo; i++) {
            sum += aData[i];
        }
        return sum / (aTo - aFrom);
    }

    /**
     * Calculates the mean of the specified data set.
     * 
     * @param aData
     *            sample data.
     * @return the mean of the specified data set.
     */
    public static double mean(double[] aData) {
        return mean(aData, 0, aData.length);
    }
} // StatisticsToolkit
