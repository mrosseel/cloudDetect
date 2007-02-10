package util;

import java.util.Random;

/**
 * A collection of frequently used mathematical methods.
 * 
 * @author <a href="mailto:Mike.Rosseel@synes.com">Mike Rosseel</a> (original)
 * @author <a href="mailto:Bert.VanVreckem@synes.com">Bert Van Vreckem</a>
 * 
 * @author $Author: mike $ (last modification)
 * @version $Revision: 1.8 $
 * 
 */
public class MathMethods {
    public static final boolean DEBUG = false;

    private static final Random RAND = new Random(Parameters.DEF_SEED);

    // ------------------------------------------------------------------------------------------------
    // Inclination
    // ------------------------------------------------------------------------------------------------

    /**
     * Calculate inclination, that is, the derivative of the line in a
     * two-dimensional Euclidian space specified by the two points given.
     * 
     * @param x1
     *            x-coordinate of the first point.
     * @param x2
     *            x-coordinate of the second point.
     * @param y1
     *            y-coordinate of the first point.
     * @param y2
     *            y-coordinate of the second point.
     * @return The inclination between the two points.
     */
    public static double inclination(double x1, double x2, double y1, double y2) {
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Predicate that checks whether the inclination between two points in an
     * <em>n</em>-dimensional space is `flat' (small enough). The value of
     * <em>n</em> should be at least 2. if this is not the case,
     * <code>true</code> is returned. This method originates from a MatLab
     * file (<code>inclinationTrue.m</code>) by Steven Raekelboom.
     * 
     * @param x
     *            the first point.
     * @param y
     *            the second point.
     * @param maxincl
     *            the maximum encountered inclination.
     * @param inc
     *            the percentage of the maximum inclination used to define
     *            `flat'.
     * @return can we describe this part of the curve as flat (true) or not
     *         (false) ?
     * @deprecated if you need it, write the junit test, if not, this can be
     *             deleted...
     */
    public static boolean inclinationTrue(double[] x, double[] y,
            double maxincl, double inc) {
        if (x.length != y.length)
            throw new ArithmeticException(
                    "MathMethods: incompatible lengths in array arguments.");

        for (int i = 0; i != x.length - 1; i++)
            if (Math.abs(inclination(x[i], x[i + 1], y[i], y[i + 1])) > maxincl
                    * inc)
                return false;
        return true;
    }

    // ------------------------------------------------------------------------------------------------
    // Mean Squared error
    // ------------------------------------------------------------------------------------------------

    /**
     * Returns the Mean Squared Error between the specified points in an
     * <em>n</em>-dimensional space.
     * 
     * @param x
     *            the first point.
     * @param y
     *            the second point.
     * @return the Mean Squared Error between the specified points.
     */
    public static double meanSquareError(double[] x, double[] y) {
        return meanSquareError(x, y, 0, x.length, 0, x.length);
    }

    /**
     * Returns the Mean Squared Error between the specified points.
     * 
     * @param x
     *            the first point.
     * @param y
     *            the second point.
     * @param left
     *            the left index of the array (inclusive).
     * @param right
     *            the right index of the array (inclusive).
     * @return the Mean Squared Error between the specified points.
     */
    public static double meanSquareError(double[] x, double[] y, int left,
            int right) {
        return meanSquareError(x, y, left, right, left, right);
    }

    /**
     * Mean Square error between (a certain part of) two points x and y in an
     * <em>n</em>-dimensional space. The left and right parameters indicate
     * the part of the x and y points which will be used. Notation of boundaries
     * is [left, right].
     * 
     * @param x
     *            the first point.
     * @param y
     *            the second point.
     * @param xleft
     *            the left index of the x array (inclusive).
     * @param xright
     *            the right index of the x array (inclusive).
     * @param yleft
     *            the left index of the y array (inclusive).
     * @param yright
     *            the right index of the y array (inclusive).
     */
    public static double meanSquareError(double[] x, double[] y, int xleft,
            int xright, int yleft, int yright) {
        double mse = 0;
        double tmp;

        if ((xright - xleft) != (yright - yleft)) {
            if (DEBUG) {
                System.out.println("xlength, ylength, xl, xr, yl, yr "
                        + x.length + " " + y.length + " " + xleft + " "
                        + xright + " " + yleft + " " + yright);
                System.out.println("xr-xl != yr-yl" + (xright - xleft) + " "
                        + (yright - yleft));
            }
            throw new ArithmeticException(
                    "MathMethods: meansquareError: indices of unequal length.");
        }

        for (int i = 0; i != xright - xleft + 1; i++) {
            tmp = x[xleft + i] - y[yleft + i];
            mse += tmp * tmp;
        }

        mse /= xright - xleft + 1;

        return mse;
    }

    // ------------------------------------------------------------------------------------------------
    // Square Distance Implementations
    // ------------------------------------------------------------------------------------------------

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. The first point may contain missing values. If this is the case,
     * then <code>Double.POSITIVE_INFINITY</code> is returned.
     * 
     * @param v1
     *            the first point, which may contain missing values.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double inftySqrDistance(double v1[], double v2[]) {
        double temp = sqrDistance(v1, v2);
        if (Double.isNaN(temp))
            return Double.POSITIVE_INFINITY;
        return temp;
    }

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. The first point may contain missing values. If this is the case,
     * then <code>Double.POSITIVE_INFINITY</code> is returned.
     * 
     * @param v1
     *            the first point, which may contain missing values.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double inftySqrDistance(Double v1[], double v2[]) {
        double temp = sqrDistance(v1, v2);
        if (Double.isNaN(temp))
            return Double.POSITIVE_INFINITY;
        return temp;
    }

    // ---------- sqrDist with missing values
    // ---------------------------------------------------------

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. The first point may contain missing values, which will be ignored
     * in the calculation.
     * 
     * @param v1
     *            the first point, which may contain missing values.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double missSqrDistance(double v1[], double v2[]) {
        double temp = 0;
        double cur;

        for (int i = 0; i != v1.length; i++) {
            cur = v1[i] - v2[i];
            if (!Double.isNaN(cur))
                temp += cur * cur;
        }
        return temp;
    }

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. The points may contain missing values, which will be ignored in
     * the calculation.
     * 
     * @param v1
     *            the first point.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double missSqrDistance(Double v1[], double v2[]) {
        double temp = 0;
        double cur, vi;

        for (int i = 0; i != v1.length; i++) {
            vi = v1[i].doubleValue();
            cur = vi - v2[i];
            if (!Double.isNaN(cur))
                temp += cur * cur;
        }
        return temp;
    }

    /**
     * Calculates the squared Euclidian distance between the specified points in
     * a multi-dimensional space, of which some components may be missing (or
     * effectively contain <code>Double.NaN</code>. These missing values are
     * ignored in the calculation. To optimise this process, the user provides
     * the method with the indexes of missing components. The argument
     * <code>aMissing</code> contains index <code>i</code> if and only if
     * <code>aV[i]</code> is missing or <code>aW[i]</code> is missing. You
     * may want to use this particularly when you know that one of the points
     * does not contain missing values.
     * 
     * @param aV
     *            the first point.
     * @param aW
     *            the second point.
     * @param aMissing
     *            the sorted indexes of missing values in both points.
     * @return the squared Euclidian distance between the specified points.
     */
    public static double missSqrDistance(double[] aV, double[] aW,
            int[] aMissing) {
        double distance = 0;
        int idx = 0; // index of current point component
        int miss = 0; // index of next missing value in aMissing
        double cur; // difference between current components

        while (miss != aMissing.length) {
            while (idx != aMissing[miss]) {
                cur = aW[idx] - aV[idx];
                distance += cur * cur;
                idx++;
            }
            idx++;
            miss++;
        }
        while (idx != aV.length) {
            cur = aW[idx] - aV[idx];
            distance += cur * cur;
            idx++;
        }

        return distance;
    }

    // ---------- `normal' sqrDist
    // --------------------------------------------------------------------

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. <b>CAUTION!</B> Be aware of the fact that this function does not
     * check on missing values. If you use it to compare samples with neuron
     * weights, things may go wrong.
     * 
     * @param v1
     *            the first point.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double sqrDistance(Double v1[], double[] v2) {
        double temp = 0;
        double cur;

        for (int i = 0; i != v1.length; i++) {
            cur = v1[i].doubleValue() - v2[i];
            temp += cur * cur;
        }

        return temp;
    }

    /**
     * Calculates the Euclidean distance between two points in an <em>n</em>-dimensional
     * space. <b>CAUTION!</B> Be aware of the fact that this function does not
     * check on missing values. If you use it to compare samples with neuron
     * weights, things may go wrong.
     * 
     * @param v1
     *            the first point.
     * @param v2
     *            the second point.
     * @return the Euclidean distance between the specified points.
     */
    public static double sqrDistance(double v1[], double[] v2) {
        double temp = 0;
        double cur;

        for (int i = 0; i != v1.length; i++) {
            cur = v1[i] - v2[i];
            temp += cur * cur;
        }

        return temp;
    }

    // ------------------------------------------------------------------------------------------------
    // Round
    // ------------------------------------------------------------------------------------------------

    /**
     * Given a precision, it will return the rounded value if the difference
     * with the <code>value</code> is greater than <code>precision</code>.
     * Otherwise the original <code>value</code> is returned.
     * 
     * @param value
     *            a <code>double</code> value, that might be rounded.
     * @param precision
     *            a <code>double</code> value that will be used to calulate
     *            the difference.
     * @return a <code>double</code> value that is rounded if the difference
     *         is greater than <code>precision</code>.
     * @deprecated this method gives unreliable results (java precisio). Rewrite
     *             it using BigDecimal if you really need it, else dump it.
     */
    public static double round(double value, double precision) {
        if (Math.abs(Math.round(value) - value) < precision) {
            System.out.println("In the if");
            return Math.round(value);
        } // end of if ()
        return value;
    }

    /**
     * Rounds the specified value to the specified number of decimals.
     * 
     * @param aValue
     *            the value to be rounded.
     * @param aPrecision
     *            the precision, that is, the number of decimals.
     * @return the rounded number.
     */
    public static double round(double aValue, int aPrecision) {
        double factor = Math.pow(10, aPrecision);

        return Math.round(aValue * factor) / factor;
    }

    // ------------------------------------------------------------------------------------------------
    // Transpose matrix
    // ------------------------------------------------------------------------------------------------

    /**
     * Transposes the specified matrix and returns the result. Note that this is
     * a non-destructive operation, i.e. the original matrix remains unchanged.
     * 
     * @param aMatrix
     *            the matrix to be transposed.
     * @return the transposed matrix.
     * @deprecated use {@link Matrix#transpose(double[][])} instead.
     */
    public static double[][] transpose(double aMatrix[][]) {
        int length = aMatrix.length;
        int width = (length == 0 ? 0 : aMatrix[0].length);
        double result[][] = new double[width][length];

        for (int i = 0; i != length; i++)
            for (int j = 0; j != width; j++)
                result[j][i] = aMatrix[i][j];
        return result;
    }

    // ------------------------------------------------------------------------------------------------
    // Miscellaneous
    // ------------------------------------------------------------------------------------------------

    /**
     * Implements Euclid's algorithm to find the greatest common denominator
     * (gcd) of the specified numbers.
     * 
     * @param a
     *            an integer.
     * @param b
     *            another integer
     * @return the greatest common denominator of <code>a</code> and
     *         <code>b</code>.
     */
    public static int gcd(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = b;
            b = a % b;
            a = tmp;
        } // end of while (b != 0)
        return a;
    }

    /**
     * Converts a neuron (a weight vector and a radius) to a uniform
     * hypersphere.
     * 
     * @param aWeightVector
     *            the weight vector of the neuron.
     * @param aRadius
     *            the radius of the neuron.
     * @param aNumPoints
     *            the number of points to generate.
     */
    public static final double[][] toUniformSphere(double[] aWeightVector,
            double aRadius, int aNumPoints) {
        int i, j;
        int dim = aWeightVector.length;
        double[][] uniformSphere = new double[aNumPoints][dim];
        double radius;
        double norm;
        double pow = 1.0 / (double) dim;
        // Initialize a uniform unity sphere
        for (i = 0; i != aNumPoints; i++) {
            // Generate a random radius (normalized)
            radius = Math.pow(Math.random(), pow);
            // Generate a random direction (unnormalized)
            for (j = 0; j != dim; j++)
                uniformSphere[i][j] = ((RAND.nextInt() % 2 == 0) ? 1.0 : -1.0)
                        * Math.random();
            // Normalize the direction & multiply with the radius
            norm = norm(uniformSphere[i]);
            for (j = 0; j != dim; j++)
                uniformSphere[i][j] *= radius / norm;
        }
        // Rescale the unity sphere to a sphere around the neuron center
        for (i = 0; i != aNumPoints; i++) {
            for (j = 0; j != dim; j++) {
                uniformSphere[i][j] *= aRadius;
                uniformSphere[i][j] += aWeightVector[j];
            }
        }
        return uniformSphere;
    }

    /**
     * Returns the length of a n-dimensional vector.
     * 
     * @param aVector
     *            an array of size n denoting a vector in real n-dimensional
     *            space.
     * @return the length of the vector. This is the square root of the sum of
     *         the squares of its coordinates.
     */
    public static final double norm(double[] aRray) {
        double norm = 0.0;
        for (int i = 0, iend = aRray.length; i != iend; i++)
            norm += aRray[i] * aRray[i];
        return Math.sqrt(norm);
    }

    public static final double min(double[] data) {
        return min(data, 0, data.length);
    }

    public static final double min(double[] data, int begin, int end) {
        double smallest = Double.MAX_VALUE;
        for (int i = begin; i != end; i++) {
            if (data[i] < smallest) {
                smallest = data[i];
            }

        }
        return smallest;
    }

    public static final double max(double[] data) {
        return max(data, 0, data.length);
    }

    public static final double max(double[] data, int begin, int end) {
        double biggest = Double.MIN_VALUE;
        for (int i = begin; i != end; i++) {

            if (data[i] > biggest) {
                biggest = data[i];
            }
        }
        return biggest;
    }

    // private static final void shuffle(double[][] aRray)
    // {
    // double[] tmp;
    // int j;
    // for (int i = 0, iend = aRray.length; i != iend; i++)
    // {
    // j = RAND.nextInt(iend);
    // tmp = aRray[i];
    // aRray[i] = aRray[j];
    // aRray[j] = tmp;
    // }
    // }

}
