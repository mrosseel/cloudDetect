package util;

import java.util.Arrays;
import java.util.Comparator;
import util.IntVector;

/**
 * This class offers the kMean binning algorithm. Some important observations
 * about this algorihtm can be made:
 * <ul>
 * <li>the elements need not be sorted, but sorting them considerable reduces
 * the # iterations needed to complete the process;</li>
 * <li>the result of the algorithm <em>depends</em> very much on the ordering
 * of the elements, different orderings will result in different bins!</li>
 * </ul>
 * 
 * @author <a href="mailto:Peter.DeMaeyer@synes.com">Peter De Maeyer</a>
 *         (original)
 * @author Lode Theunis (in memoriam)
 * @author $Author: pdemaeye $ (last modification)
 * @version $Revision: 1.2 $
 */
public class KMeans {

    // ///////////////////////////////////////////////////////////////////////////
    // Instance attributes
    // ///////////////////////////////////////////////////////////////////////////

    /** # bins. */
    private int mBinCount;

    /** Bin sizes. */
    private int[] mBinSizes;

    /** Bin minima. */
    private double[] mBinMinima;

    /** Bin maxima. */
    private double[] mBinMaxima;

    /** Bin means. */
    private double[] mBinMeans;

    // ///////////////////////////////////////////////////////////////////////////
    // Construction
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Creates a new <code>KMeans</code> instance. The elements need not be
     * sorted, but the resulting bin data will be!
     * 
     * @param aElements
     *            a <code>double[]</code> value
     * @param aBinCount
     *            an <code>int</code> value
     */
    public KMeans(double[] aElements, int aBinCount) {
        int elementCount = aElements.length;
        this.mBinCount = (elementCount < aBinCount) ? elementCount : aBinCount;
        this.mBinSizes = new int[this.mBinCount];
        this.mBinMinima = new double[this.mBinCount];
        this.mBinMaxima = new double[this.mBinCount];
        this.mBinMeans = new double[this.mBinCount];
        int[][] bins = new int[this.mBinCount][];
        // Perform kMeans
        kMeans(aElements, this.mBinCount, bins, this.mBinMinima,
                this.mBinMaxima, this.mBinMeans);
        // Put bins in ascending order
        // sortBins(this.mBinCount,
        // bins,
        // this.mBinMinima,
        // this.mBinMaxima,
        // this.mBinMeans);
        int binCount = 0;
        for (int i = 0; i != this.mBinCount; i++) {
            this.mBinSizes[i] = bins[i].length;
            if (this.mBinSizes[i] != 0) {
                binCount++;
            }
        }
        // Remove empty bins...
        if (binCount != this.mBinCount) {
            this.mBinCount = binCount;
            int[] binSizes = this.mBinSizes;
            double[] binMinima = this.mBinMinima;
            double[] binMaxima = this.mBinMaxima;
            double[] binMeans = this.mBinMeans;
            this.mBinSizes = new int[this.mBinCount];
            this.mBinMinima = new double[this.mBinCount];
            this.mBinMaxima = new double[this.mBinCount];
            this.mBinMeans = new double[this.mBinCount];
            System.arraycopy(binSizes, 0, this.mBinSizes, 0, this.mBinCount);
            System.arraycopy(binMinima, 0, this.mBinMinima, 0, this.mBinCount);
            System.arraycopy(binMaxima, 0, this.mBinMaxima, 0, this.mBinCount);
            System.arraycopy(binMeans, 0, this.mBinMeans, 0, this.mBinCount);
        }
    }

    /**
     * Returns the # bins this kMeans histogram has.
     * 
     * @return the # bins this kMeans histogram has
     */
    public int getBinCount() {
        return this.mBinCount;
    }

    /**
     * Returns the bin sizes (bin heights) for all bins.
     * 
     * @return the bin sizes (bin heights) for all bins
     */
    public int[] getBinSizes() {
        return this.mBinSizes;
    }

    /**
     * Returns the bin minima for all bins.
     * 
     * @return the bin minima for all bins
     */
    public double[] getBinMinima() {
        return this.mBinMinima;
    }

    /**
     * Returns the bin maxima for all bins.
     * 
     * @return the bin maxima for all bins
     */
    public double[] getBinMaxima() {
        return this.mBinMaxima;
    }

    /**
     * Returns the bin means for all bins.
     * 
     * @return the bin means for all bins
     */
    public double[] getBinMeans() {
        return this.mBinMeans;
    }

    /**
     * Makes sure the bins are in ascending order.
     * 
     * @param aBinCount
     *            an <code>int</code> value
     * @param aBins
     *            an <code>int[][]</code> value
     * @param aBinMinima
     *            a <code>double[]</code> value
     * @param aBinMaxima
     *            a <code>double[]</code> value
     * @param aBinMeans
     *            a <code>double[]</code> value
     */
    public static final void sortBins(int aBinCount, int[][] aBins,
            double[] aBinMinima, double[] aBinMaxima, double[] aBinMeans) {
        Bin[] bins = new Bin[aBinCount];
        for (int i = 0; i != aBinCount; i++) {
            bins[i] = new Bin(aBins[i], aBinMinima[i], aBinMaxima[i],
                    aBinMeans[i]);
        }
        Arrays.sort(bins, new Comparator<Object>() {

            public int compare(Object aBin1, Object aBin2) {
                Bin bin1 = (Bin) aBin1;
                Bin bin2 = (Bin) aBin2;
                if (bin1.mBinMean < bin2.mBinMean) { // bin1 < bin2
                    return -1;
                } else if (bin1.mBinMean > bin2.mBinMean) { // bin1 > bin2
                    return 1;
                } else if (bin1.mBinMean == bin2.mBinMean) { // bin1 == bin2
                    return 0;
                } else if (bin1.mBin.length == 0) { // bin1 == NaN
                    return 1;
                } else { // bin2 == NaN
                    return -1;
                }
            }

        });
        Bin laden;
        for (int i = 0; i != aBinCount; i++) {
            laden = bins[i];
            aBins[i] = laden.mBin;
            aBinMinima[i] = laden.mBinMinimum;
            aBinMaxima[i] = laden.mBinMaximum;
            aBinMeans[i] = laden.mBinMean;
        }
    }

    /**
     * Makes sure the bins are in ascending order.
     * 
     * @param aBinCount
     *            an <code>int</code> value
     * @param aBins
     *            an <code>int[][]</code> value
     * @param aBinMeans
     *            a <code>double[]</code> value
     */
    public static final void sortBins(int aBinCount, int[][] aBins,
            double[] aBinMeans) {
        sortBins(aBinCount, aBins, new double[aBinCount],
                new double[aBinCount], aBinMeans);
    }

    /**
     * Performs the kMeans binning algorithm on an array of elements.
     * 
     * @param aElements
     *            an array of elements of arbitrary length (must not be
     *            <code>null</code>)
     * @param aBinCount
     *            a # bins (must be <code>&gt;= 0</code>)
     * @param aBinMeans
     *            an array of bin means, where the resulting bin means will be
     *            copied in (out parameter). The length of this array must be
     *            equal to the # bins
     * @param aBins
     *            an array of <code>int[]</code>, where the indexes in the
     *            original elements array will be copied in. The length of the
     *            array must be equal to the # bins
     * @param aBinMinima
     *            the bin minima
     * @param aBinMaxima
     *            the bin maxima
     */
    public static final void kMeans(double[] aElements, /* in */
    int aBinCount, /* in */
    int[][] aBins, /* out */
    double[] aBinMinima, /* out */
    double[] aBinMaxima, /* out */
    double[] aBinMeans) { /* out */
        kMeans(aElements, aBinCount, aBins, aBinMeans);
        // Calculate bin minima/maxima
        for (int i = 0; i != aBinCount; i++) {
            int[] bin = aBins[i];
            int binSize = bin.length;
            double binMinimum = Double.POSITIVE_INFINITY;
            double binMaximum = Double.NEGATIVE_INFINITY;
            for (int j = 0; j != binSize; j++) {
                double val = aElements[bin[j]];
                if (val > binMaximum) {
                    binMaximum = val;
                }
                if (val < binMinimum) {
                    binMinimum = val;
                }
            }
            aBinMinima[i] = binMinimum;
            aBinMaxima[i] = binMaximum;
        }
    }

    /**
     * Performs the kMeans binning algorithm on an array of elements, without
     * calculating bin minima/maxima.
     * 
     * @param aElements
     *            a <code>double[]</code> value
     * @param aBinCount
     *            an <code>int</code> value
     * @param aBins
     *            an <code>int[][]</code> value
     * @param aBinMeans
     *            a <code>double[]</code> value
     */
    public static final void kMeans(double[] aElements, /* in */
    int aBinCount, /* out */
    int[][] aBins, /* in */
    double[] aBinMeans) { /* out */

        final util.IntVector[] intVectors = new util.IntVector[aBinCount];
        init(aElements, aBinCount, intVectors, aBinMeans);
        calc(aElements, aBinCount, intVectors, aBinMeans);
        for (int i = 0; i != aBinCount; i++) {
            aBins[i] = intVectors[i].toArray();
        }
    }

    /** Alternative init method, independent of sorting etc. */
    private static final void randomInit(double[] aElements, int aBinCount,
            util.IntVector[] aBins, double[] aBinMeans) {

        // final int div = aElements.length / aBinCount;
        // final int divPlusOne = div + 1;
        // final int mod = aElements.length % aBinCount;
        // util.IntVector bin;
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i != aBinCount; i++) {
            aBins[i] = new IntVector();
        }
        for (int k = 0; k != aElements.length; k++) {
            aBins[rand.nextInt(4)].add(k);
        }

        means(aElements, aBinCount, aBins, aBinMeans);
    }

    /**
     * Initialises the bins, distibuting elements as equally as possible over
     * all the bins.
     */
    private static final void init(double[] aElements, int aBinCount,
            IntVector[] aBins, double[] aBinMeans) {

        final int div = aElements.length / aBinCount;
        final int divPlusOne = div + 1;
        final int mod = aElements.length % aBinCount;
        IntVector bin;
        int k = 0;

        for (int i = 0, j; i != mod; i++) {
            bin = new IntVector(divPlusOne);
            for (j = 0; j != divPlusOne; j++) {
                bin.add(k++);
            }
            aBins[i] = bin;
        }

        for (int i = mod; i != aBinCount; i++) {
            bin = new IntVector(div);
            for (int j = 0; j != div; j++) {
                bin.add(k++);
            }
            aBins[i] = bin;
        }

        means(aElements, aBinCount, aBins, aBinMeans);
    }

    /** Recalculates the means. */
    private static final void means(double[] aElements, int aBinCount,
            IntVector[] aBins, double[] aBinMeans) {

        double binMean;
        int binSize;
        IntVector bin;
        int i, j;

        for (i = 0; i != aBinCount; i++) {
            bin = aBins[i];
            binSize = bin.size();
            binMean = 0.;
            for (j = 0; j != binSize; j++) {
                binMean += aElements[bin.elementAt(j)];
            }
            aBinMeans[i] = binMean / (double) binSize;
        }
    }

    /**
     * Performs the iterative kMeans binning process. Iteration stops when no
     * elements migrate to other bins anymore.
     */
    private static final void calc(double[] aElements, int aBinCount,
            IntVector[] aBins, double[] aBinMeans) {

        int index;
        int jndex;
        IntVector bin;
        int binSize;
        boolean cont;
        int i, j;

        do {
            cont = false;
            for (i = 0; i != aBinCount; i++) {
                bin = aBins[i];
                binSize = bin.size();
                for (j = 0; j != binSize;) {
                    jndex = bin.elementAt(j);
                    index = index(aBinMeans, aElements[jndex]);
                    if (index != i) {
                        bin.removeElementAt(j); // Remove the element from the
                                                // bin...
                        binSize--; // Don't forget to update the bin size...
                        aBins[index].add(jndex); // Put the element in the
                                                    // new bin...
                        cont = true;

                        means(aElements, aBinCount, aBins, aBinMeans); // Recalculate
                                                                        // the
                                                                        // means...
                    } else {
                        j++;
                    }
                }
            }
            System.out.print(".");
        } while (cont);
    }

    /** Returns the index of the bin that is closest to a given element. */
    private static final int index(double[] aBinMeans, double aElement) {

        double shortestDist = Double.POSITIVE_INFINITY;
        double dist;
        int index = -1;

        for (int i = 0; i != aBinMeans.length; i++) {
            dist = Math.abs(aBinMeans[i] - aElement);
            if (dist <= shortestDist) {
                shortestDist = dist;
                index = i;
            }
        }
        return index;
    }

    /** Internal class used for grouping and sorting bin data. */
    private static class Bin {

        final int[] mBin;

        final double mBinMinimum;

        final double mBinMaximum;

        final double mBinMean;

        public Bin(int[] aBin, double aBinMinimum, double aBinMaximum,
                double aBinMean) {
            this.mBin = aBin;
            this.mBinMinimum = aBinMinimum;
            this.mBinMaximum = aBinMaximum;
            this.mBinMean = aBinMean;
            // DEBUG
            // System.out.println("bin = " +
            // com.synes.util.ArrayUtils.toString(this.mBin));
            // System.out.println("bin min = " + this.mBinMinimum);
            // System.out.println("bin max = " + this.mBinMaximum);
            // System.out.println("bin mean = " + this.mBinMean);
        }

    }

}
