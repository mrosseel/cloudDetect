package util;

/**
 * A simple, text based progress bar.
 * 
 * @author <a href="mailto:Bert.VanVreckem@synes.com">Bert Van Vreckem</a>
 *         (original)
 * @author $Author: bvanvrec $
 * @version $Revision: 1.4 $
 */
public class TextProgressBar {
    // ------------------------------------------------------------------------------------------------
    // Private variables
    // ------------------------------------------------------------------------------------------------

    /** Current value of the progress bar. */
    private double mVal;

    /** Total length of the progress bar. */
    private int mLength;

    /** Smallest value of progress (start value). */
    private double mMin;

    /** Span between min and max. */
    private double mSpan;

    // ------------------------------------------------------------------------------------------------
    // Construction
    // ------------------------------------------------------------------------------------------------

    /**
     * Creates a new <code>TextProgressBar</code> instance with the specified
     * settings.
     * 
     * @param aLength
     *            the length of the progress bar on the screen.
     * @param aMin
     *            the smallest possible progress value.
     * @param aMax
     *            the largest possible value.
     */
    public TextProgressBar(int aLength, double aMin, double aMax) {
        this.mVal = aMin;
        this.mLength = aLength;
        setScalingParameters(aMin, aMax);
    }

    /**
     * Creates a new <code>TextProgressBar</code> instance of the specified
     * length and between 0 and 1.
     * 
     * @param aLength
     *            the length of the progress bar on the screen.
     */
    public TextProgressBar(int aLength) {
        this(aLength, 0, 1);
    }

    /**
     * Creates a new <code>TextProgressBar</code> instance of length 10 and
     * between 0 and 1.
     * 
     */
    public TextProgressBar() {
        this(10);
    }

    /**
     * Puts a new value into the progress bar. This should be a value between 0
     * and 1 (100%), unless you have set the scaling parameters using
     * {@link #setScalingParameters(double, double)}. Values greater than the
     * maximume are accepted, though, and if this happens, the progress bar
     * becomes longer.
     * 
     * @param aVal
     *            the new value for the progress bar.
     */
    public void update(double aVal) {
        this.mVal = (aVal - this.mMin) / this.mSpan;
    }

    /**
     * <code>setScalingParameters</code> method.
     * 
     * Set your own scaling parameters if your values are not between 0 and 1 !
     * 
     * @param aMin
     *            a <code>double</code> object, start value of progress.
     *            Smaller values will geve negative progress !
     * @param aMax
     *            a <code>double</code> object, end value of progress.
     */
    public void setScalingParameters(double aMin, double aMax) {
        this.mMin = aMin;
        this.mSpan = aMax - aMin;
    }

    /**
     * Prints the progress bar. Note that the progress bar starts with a
     * carriage return and is <em>not</em> terminated by a newline character.
     * The consequence is that consecutive calls of the print function result in
     * an `animated' progress bar that's being filled.
     * 
     */
    public void print() {
        int len = (int) Math.round(this.mVal * this.mLength);

        System.out.print("\r[" + StringToolkit.repeatString("#", len)
                + StringToolkit.repeatString("-", this.mLength - len) + "] "
                + MathMethods.round(this.mVal * 100, 2) + "% ");
    }

} // TextProgressBar
