package util;

import java.util.NoSuchElementException;

/**
 * The equivalent of a Java {@link java.util.Iterator} that returns
 * <code>int</code>s instead of <code>Object</code>s.
 * 
 * @author Peter De Maeyer
 * 
 * @author PDEMAEYE
 * @version 10 (UNDERWORK)
 * 
 */
public interface IntIterator {
    /**
     * Returns <code>true</code> if the iteration has more elements. (In other
     * words, returns <code>true</code> if <code>next</code> would return an
     * element rather than throwing an exception.)
     * 
     * @return <code>true</code> if the iterator has more elements.
     */
    public boolean hasNext();

    /**
     * Returns the next element (<code>int</code>) in the interation.
     * <p>
     * Note that the returned element is <i>typed</i>.
     * 
     * @return the next <code>int</code> in the iteration.
     * @exception NoSuchElementException
     *                if no more elements can be retrieved.
     */
    public int next() throws NoSuchElementException;

    /**
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation). This method can be called only once per
     * call to <code>next</code>. The behavior of an iterator is unspecified
     * if the underlying collection is modified while the iteration is in
     * progress in any way other than by calling this method (this means that
     * strange things can happen).
     * 
     * @exception UnsupportedOperationException
     *                if the implementation does not support removal of elements
     *                through the iterator.
     */
    public void remove() throws UnsupportedOperationException;

}
