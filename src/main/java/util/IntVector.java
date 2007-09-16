package util;

import java.io.Serializable;
import java.util.*;

/**
 * The <code>IntVector</code> class implements a growable array of
 * <code>int</code>s. In fact it is made to match as closely as possible the
 * interface of the <code>java.util.Vector</code> class.
 *
 * @author Peter De Maeyer
 * 
 * @author PDEMAEYE
 * @version 11 (UNDERWORK)
 * 
 * @see java.util.Vector
 */
public class IntVector implements Serializable
{
////////////////////////////////////////////////////////////////////////////////
// Data members
////////////////////////////////////////////////////////////////////////////////

  /**
     * 
     */
    private static final long serialVersionUID = 1L;

private static final int DEF_CAPACITY_INCREMENT = 0;

  private static final int DEF_INITIAL_CAPACITY = 10;

  private static final int NULL_INT_VALUE = 0;

  /**
   * The array buffer into which the components of the vector are stored. The
   * capacity of the vector is the length of this array buffer, and is at least
   * large enough to contain all the vector's elements.<p>
   * Any array elements following the last element in the Vector are left
   * uninitialized.
   */
  protected int[] elementData;

  /**
   * The number of valid components in this <code>Vector</code> object.
   * Components <code>elementData[0]</code> through
   * <code>elementData[elementCount-1]</code> are the actual items.
   */
  protected int elementCount;

  /**
   * The amount by which the capacity of the vector is automatically incremented
   * when its size becomes greater than its capacity. If the capacity increment
   * is smaller than or equal to 0, the capacity of the vector is intd each
   * time it needs to grow.
   */
  protected int capacityIncrement;

////////////////////////////////////////////////////////////////////////////////
// Object construction
////////////////////////////////////////////////////////////////////////////////

  /**
   * Constructs an empty vector with the specified initial capacity and capacity
   * increment.
   *
   * @param   initialCapacity  the initial capacity of the vector.
   * @param   capacityIncrement  the amount by which the capacity is increased when the vector overflows.
   * @exception   IllegalArgumentException  thrown when the specified initial capacity is negative.
   */
  public IntVector(int initialCapacity, int capacityIncrement) throws IllegalArgumentException
  {
    if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal Capacity: "+initialCapacity);

    this.elementData = new int[initialCapacity];
    this.capacityIncrement = capacityIncrement;
    this.elementCount = 0;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Constructs an empty vector with the specified initial capacity and with
   * its capacity increment equal to zero.
   *
   * @param   initialCapacity  the initial capacity of the vector.
   * @exception   IllegalArgumentException  thrown when the specified initial capacity is negative.
   */
  public IntVector(int initialCapacity) throws IllegalArgumentException
  {
    this(initialCapacity, DEF_CAPACITY_INCREMENT);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Constructs a vector containing the elements of the specified array of
   * ints. This method is is the equivalent for the constructor in the
   * <code>Vector</code> that takes a <code>Collection</code> as argument.
   *
   * @param   array  contains the elements from which the vector is constructed.
   */
  public IntVector(int[] array)
  {
    this.elementCount = array.length;
    this.elementData = new int[this.elementCount];
    this.capacityIncrement = DEF_CAPACITY_INCREMENT;
    System.arraycopy(array, 0, this.elementData, 0, this.elementCount);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Constructs an empty vector so that its internal data array has size 10 and
   * its standard capacity increment is zero.
   */
  public IntVector()
  {
    this.elementData = new int[DEF_INITIAL_CAPACITY];
    this.capacityIncrement = DEF_CAPACITY_INCREMENT;
    this.elementCount = 0;
  }

////////////////////////////////////////////////////////////////////////////////
// Core functionality
////////////////////////////////////////////////////////////////////////////////

  /**
   * Copies the components of this vector into the specified array. The item at
   * index k in this vector is copied into component k of <code>anArray</code>.
   * The array must be big enough to hold all the objects in this vector, else
   * an <code>IndexOutOfBoundsException</code> is thrown.
   *
   * @param   anArray  the array into which the components get copied.
   * @exception   ArrayIndexOutOfBoundsException  thrown when the array is too small to hold all the elements of the vector.
   */
  public void copyInto(int[] anArray) throws ArrayIndexOutOfBoundsException
  {
    System.arraycopy(this.elementData, 0, anArray, 0, this.elementCount);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Trims the capacity of this vector to be the vector's current size. If the
   * capacity of this vector is larger than its current size, then the capacity
   * is changed to equal the size by replacing its internal data array, kept in
   * the field <code>elementData</code>, with a smaller one. An application can
   * use this operation to minimize the storage of a vector.
   */
  public void trimToSize()
  {
    if (this.elementCount < this.elementData.length)
    {
      int[] newElementData = new int[this.elementCount];
      System.arraycopy(this.elementData, 0, newElementData, 0, this.elementCount);
      this.elementData = newElementData;
    }
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Increases the capacity of this vector, if necessary, to ensure that it can
   * hold at least the number of components specified by the minimum capacity
   * argument.<br>
   * If the current capacity of this vector is less than minCapacity, then its
   * capacity is increased by replacing its internal data array, kept in the
   * field elementData, with a larger one. The size of the new data array will
   * be the old size plus capacityIncrement, unless the value of
   * <code>capacityIncrement</code> is nonpositive, in which case the new
   * capacity will be twice the old capacity; but if this new size is still
   * smaller than <code>minCapacity</code>, then the new capacity will be
   * <code>minCapacity</code>.
   *
   * @param   minCapacity  the desired minimum capacity.
   */
  public void ensureCapacity(int minCapacity)
  {
    if (this.elementData.length < minCapacity)
    {
      int newCapacity = (this.capacityIncrement > 0)?this.capacityIncrement:this.elementData.length;
      if (newCapacity < minCapacity)
        newCapacity = minCapacity;
      int[] newElementData = new int[newCapacity];
      System.arraycopy(this.elementData, 0, newElementData, 0, this.elementCount);
      this.elementData = newElementData;
    }
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Sets the size of this vector. If the new size is greater than the current
   * size, new null items are added to the end of the vector. If the new size is
   * less than the current size, all components at index <code>newSize</code>
   * and greater are discarded.
   *
   * @param   newSize  the new size of this vector.
   * @exception   ArrayIndexOutOfBoundsException  if new size is negative.
   */
  public void setSize(int newSize) throws ArrayIndexOutOfBoundsException
  {
    if (newSize < 0)
      throw new ArrayIndexOutOfBoundsException(newSize);

    if (newSize < this.elementCount)
      this.elementCount = newSize;
    else
      for (int i = this.elementCount; i != newSize; i++)
        this.add(NULL_INT_VALUE); // This can be done quicker!
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the current capacity of this vector.
   *
   * @return     the current capacity (the length of its internal data arary, kept in the field <code>elementData</code> of this vector.
   */
  public int capacity()
  {
    return this.elementData.length;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the number of components in this vector.
   *
   * @return     the number of components in this vector.
   */
  public int size()
  {
    return this.elementCount;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Tests if this vector has no components.
   *
   * @return     <code>true</code> if and only if this vector has no components, that is, its size is zero; <code>false</code> otherwise.
   */
  public boolean isEmpty()
  {
    return this.elementCount == 0;
  }

////////////////////////////////////////////////////////////////////////////////

  /*
  public Enumeration elements()
  {
    Enumeration enum = new Enumeration();

    // Needs work

    return enum;
  }
  */

////////////////////////////////////////////////////////////////////////////////

  /**
   * Tests if the specified object is a component in this vector.
   *
   * @param   elem  a int.
   * @return     <code>true</code> if and only if the specified object is the same as a component in this vector, as determined by <code>==</code>; <code>false</code> otherwise.
   */
  public boolean contains(int elem)
  {
    for (int i = 0; i != this.elementCount; i++)
      if (this.elementData[i] == elem)
        return true;

    return false;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Searches for the first occurence of the given argument, testing for
   * equality using <code>==</code>.<br>
   * Note that this method does <i>not</i> rely on the next, because of speed
   * optimizations. First of all, an extra procedure call is avoided, and second
   * of all, comparison can be done safely with != instead of <, which is
   * slightly but noticeably faster.
   *
   * @param   elem  a int.
   * @return     the index of the first occurrence of the argument in this vector; returns -1 if the element is not found.
   */
  public int indexOf(int elem)
  {
    for (int i = 0; i != this.elementCount; i++)
      if (this.elementData[i] == elem)
        return i;

    return -1;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Searches for the first occurence of the given argument, beginning the
   * search at index.
   *
   * @param   elem  a int.
   * @param   index  the index to start searching from.
   * @return     the index of the first occurrence of the object argument in this vector at position <code>index</code> or later in the vector; returns -1 if the element is not found.
   */
  public int indexOf(int elem, int index)
  {
    for (int i = index; i < this.elementCount; i++) // < instead of != to avoid infinite loop when index argument > this.elementCount
      if (this.elementData[i] == elem)
        return i;

    return -1;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the index of the last occurrence of the specified int in this
   * vector.
   *
   * @param   elem  the desired component.
   * @return     the index of the last occurrence of the specified element in this vector; returns -1 if the object is not found.
   */
  public int lastIndexOf(int elem)
  {
    for (int i = this.elementCount-1; i != -1; i--)
      if (this.elementData[i] == elem)
        return i;

    return -1;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Searches backwards for the specified object, starting from the specified
   * index, and returns an index to it.
   *
   * @param   elem  the desired component.
   * @param   index  the index to start searching from.
   * @return     the index of the last occurrence of the specified element in this vector; returns -1 if the object is not found.
   */
  public int lastIndexOf(int elem, int index)
  {
    for (int i = index; i > -1; i--)
      if (this.elementData[i] == elem)
        return i;

    return -1;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the component at the specified index.<br>
   * This method is identical in functionality to the <code>get</code> method,
   * but slightly faster since the latter relies upon the former and thus
   * requires an extra function call.
   *
   * @param   index  an index into this vector.
   * @return     the component at the specified index.
   * @exception   ArrayIndexOutOfBoundsException  if the index is negative or not less than the current size of this Vector object. given.
   */
  public int elementAt(int index) throws ArrayIndexOutOfBoundsException
  {
    if (index >= this.elementCount)
      throw new ArrayIndexOutOfBoundsException(index+" >= "+this.elementCount);
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index+" < 0");

    return this.elementData[index];
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the first component of this vector.
   *
   * @return     the first component of this vector.
   * @exception   NoSuchElementException  if this vector has no components.
   */
  public int firstElement() throws NoSuchElementException
  {
    if (this.elementCount == 0)
      throw new NoSuchElementException();

    return this.elementData[0];
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the last component of the vector.
   *
   * @return     the last component of the vector.
   * @exception   NoSuchElementException  if this vector is empty.
   */
  public int lastElement() throws NoSuchElementException
  {
    if (this.elementCount == 0)
      throw new NoSuchElementException();

    return this.elementData[this.elementCount-1];
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Sets the component at the specified <code>index</code> of this vector to be
   * the specified object. The previous component at that position is discarded.
   * The index must be a value >= 0 and < the current size of the vector.<br>
   * This method is identical in functionality to the <code>set</code> method.
   *
   * @param   obj  what the component is to be set to.
   * @param   index  the specified index.
   * @exception   ArrayIndexOutOfBoundsException  if the index was invalid.
   */
  public void setElementAt(int obj, int index) throws ArrayIndexOutOfBoundsException
  {
    if (index >= this.elementCount)
      throw new ArrayIndexOutOfBoundsException(index+" >= "+this.elementCount);
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index+" < 0");

    this.elementData[index] = obj;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Deletes the component at the specified <code>index</code>. Each component
   * in this vector with an index greater or equal to the specified index is
   * shifted downward to have an index one smaller than the value it had
   * previously. The size of this vector is decreased by 1.<br>
   * The <code>index</code> must be a value >= 0 and < the current size of the
   * vector.<br>
   * This method is identical in functionality to the remove method.<br>
   * Note that the remove method returns the old value that was stored at the
   * specified position.
   *
   * @param   index  the index of the object to remove.
   * @exception   ArrayIndexOutOfBoundsException  if the index was invalid.
   */
  public void removeElementAt(int index) throws ArrayIndexOutOfBoundsException
  {
    if (index >= this.elementCount)
      throw new ArrayIndexOutOfBoundsException(index+" >= "+this.elementCount);
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index+" < 0");

    int copyLength = this.elementData.length-index-1;
    if (copyLength != 0) // Dunno what would happen if arraycopy(., ., ., ., 0) is done, just avoiding trouble here
      System.arraycopy(this.elementData, index+1, this.elementData, index, copyLength); // Shifting right part to left
    this.elementCount--;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Inserts the specified object as a component in this vector at the specified
   * <code>index</code>. Each component in this vector with an index greater or
   * equal to the specified index is shifted upward to have an index one greater
   * than the value it had previously.<br>
   * The index must be a value >= 0 and <= the current size of the vector. (If
   * the index is equal to the current size of the vector, the new element is
   * appended to the <code>IntVector</code>.)<br>
   * This method is identical in functionality to the
   * <code>add(Object, int)</code> method. Note that the add method reverses the
   * order of the parameters, to more closely match array usage.
   *
   * @param   obj  the component to insert.
   * @param   index  where to insert the new component.
   * @exception   ArrayIndexOutOfBoundsException  if the index was invalid.
   */
  public void insertElementAt(int obj, int index) throws ArrayIndexOutOfBoundsException
  {
    if (index > this.elementCount)
      throw new ArrayIndexOutOfBoundsException(index+" > "+this.elementCount);
    if (index < 0)
      throw new ArrayIndexOutOfBoundsException(index+" < 0");

    if (this.elementCount == this.elementData.length)
      expand();
    int copyLength = this.elementCount-index;
    if (copyLength != 0) // Dunno what would happen if arraycopy(., ., ., ., 0) is done, just avoiding trouble here
      System.arraycopy(this.elementData, index, this.elementData, index+1, copyLength); // Shifting right part to right
    this.elementData[index] = obj; // Inserting the element at the designated position
    this.elementCount++;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Adds the specified component to the end of this vector, increasing its size
   * by one. The capacity of this vector is increased if its size becomes
   * greater than its capacity.<br>
   * This method is identical in functionality to the <code>add(Object)</code>
   * method.
   *
   * @param   obj  the component to be appended.
   */
  public void addElement(int obj)
  {
    if (this.elementCount == this.elementData.length) // If internal array is full, expand the array
      expand();
    this.elementData[this.elementCount] = obj;
    this.elementCount++;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Removes the first (lowest-indexed) occurrence of the argument from this
   * vector. If the object is found in this vector, each component in the vector
   * with an index greater or equal to the object's index is shifted downward to
   * have an index one smaller than the value it had previously.<br>
   * This method is identical in functionality to the remove(Object) method.
   *
   * @param   obj  the component to be removed.
   * @return     <code>true</code> if the argument was a component of this vector; <code>false</code> otherwise.
   */
  public boolean removeElement(int obj)
  {
    int index = indexOf(obj);

    if (index == -1)
      return false;
    removeElementAt(index);

    return true;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Removes all components from this vector and sets its size to zero.<br>
   * This method is identical in functionality to the <code>clear</code> method.
   * <br>
   * Note that this operation does <i>not</i> reduce the capacity, the capacity
   * remains!
   */
  public void removeAllElements()
  {
    this.elementCount = 0;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns a clone of this vector. The copy will contain a reference to a
   * clone of the internal data array, not a reference to the original internal
   * data array of this Vector object.<br>
   * Note that a clone does <i>not</i> copy the capacity! The initial capacity
   * of a clone is always the same as its size. (This is also the case with
   * regular Java <code>Vector</code>s).
   *
   * @return      a copy of this vector.
   */
  public Object clone()
  {
    IntVector v = new IntVector();
    int[] elementData = new int[this.elementCount];

    System.arraycopy(this.elementData, 0, elementData, 0, this.elementCount);
    v.elementData = elementData;
    v.elementCount = this.elementCount;
    v.capacityIncrement = this.capacityIncrement;

    return v;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns an array of ints containing all of the elements in this
   * <code>IntVector</code> in the correct order.<br>
   * The returned array is <i>not</i> a reference to the internal array, but a
   * freshly created array!
   *
   * @return      contains all the elements of this vector.
   */
  public int[] toArray()
  {
    int[] elementData = new int[this.elementCount];

    System.arraycopy(this.elementData, 0, elementData, 0, this.elementCount);

    return elementData;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      elementAt
   *
   * @param   index  
   * @return     the element at the given index.
   * @exception   ArrayIndexOutOfBoundsException  
   */
  public int get(int index) throws ArrayIndexOutOfBoundsException
  {
    return elementAt(index);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      setElementAt
   *
   * @param   index  
   * @param   element  
   * @return     the element previously at the given index.
   * @exception   ArrayIndexOutOfBoundsException  
   */
  public int set(int index, int element) throws ArrayIndexOutOfBoundsException
  {
    int d = elementAt(index);
    setElementAt(element, index);
    return d;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      addElement
   *
   * @param   o  
   * @return     
   */
  public boolean add(int o)
  {
    addElement(o);

    return true;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      addElement
   *
   * @param   o  
   * @return     
   */
  public void add(int index, int o) throws ArrayIndexOutOfBoundsException
  {
    insertElementAt(o, index);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      removeElement
   *
   * @param   o  
   * @return     
   */
  public boolean remove(int o)
  {
    return removeElement(o);
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      removeElementAt
   *
   * @param   o  
   * @return     the element previously at that position.
   */
  public int removeAt(int index) throws ArrayIndexOutOfBoundsException
  {
    int d = elementAt(index);

    removeElementAt(index);

    return d;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * @see      removeAllElements
   */
  public void clear()
  {
    removeAllElements();
  }

////////////////////////////////////////////////////////////////////////////////

  public int hashCode()
  {
    int hashCode = 29;

    for (int i = 0; i != this.elementCount; i++) 
    {
      hashCode = 47 * hashCode + this.elementData[i];
    }

    return hashCode;
  }

  /**
   * Tests for equality. Two vectors are equal if they contain the same
   * elements.
   *
   * @param    intVector    the vector with which to compare this one.
   * @return      <code>true</code> if both vectors have the same content; <code>false</code> otherwise.
   */
  public boolean equals(Object o)
  {
    boolean b = false;
    
    try
    {
      IntVector v = (IntVector) o;
      if (this.elementCount == v.elementCount)
      {
        int i = 0;
        for (; i != this.elementCount; i++)
        {
          if (this.elementData[i] != v.elementData[i])
            break;
        }
        if (i == this.elementCount)
          b = true;
      }
    }
    catch (Exception e)
    {
      // Do nothing
    }
    
    return b;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns a stringified vector.<br>
   * The string representation uses <code>[]</code> for delimeters and
   * <code>, </code> for separator as <code>Vector</code> does.
   *
   * @return     a string representation of this vector.
   */
  public String toString()
  {
    StringBuffer sb = new StringBuffer("[");

    for (int i = 0; i != this.elementCount; i++)
    {
      if (i != 0)
        sb.append(", ");
      sb.append(Integer.toString(this.elementData[i]));
    }
    sb.append("]");

    return sb.toString();
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns a new vector containing the elements in the specified range. As
   * usual, <code>fromIndex</code> is inclusive, <code>toIndex</code> is
   * exclusive.
   *
   * @param    fromIndex  inclusive.
   * @param    toIndex  exclusive.
   * @return      a new <code>IntVector</code> with the elements of the specified range.
   */
  public IntVector subVector(int fromIndex, int toIndex) throws IndexOutOfBoundsException, IllegalArgumentException
  {
    if (toIndex >= this.elementCount)
      throw new ArrayIndexOutOfBoundsException(toIndex+" >= "+this.elementCount);
    if (fromIndex < 0)
      throw new ArrayIndexOutOfBoundsException(fromIndex+" < 0");
    if (fromIndex > toIndex)
      throw new IllegalArgumentException(fromIndex+" > "+toIndex);

    int copyLength = toIndex-fromIndex;
    int[] subList = new int[copyLength];
    if (copyLength != 0)
      System.arraycopy(this.elementData, fromIndex, subList, 0, copyLength);

    IntVector v = new IntVector(subList);

    return v;
  }

////////////////////////////////////////////////////////////////////////////////

  /**
   * Returns a <code>IntIterator</code> for this <code>IntVector</code>.
   * Such an iterator works in the same way as a normal iterator, except that it
   * returns <i>typed</i> elements (ints) instead of objects. For this
   * reason, it cannot be made to extend <code>Iterator</code>.
   *
   * @return      an iterator...
   */
  public util.IntIterator iterator()
  {
    return new IntIterator(this);
  }

////////////////////////////////////////////////////////////////////////////////
// Helper methods
////////////////////////////////////////////////////////////////////////////////

  private void expand()
  {
    int newCapacity = this.elementData.length+((this.capacityIncrement > 0)?this.capacityIncrement:((this.elementData.length != 0)?this.elementData.length:1));
    int[] newElementData = new int[newCapacity];

    System.arraycopy(this.elementData, 0, newElementData, 0, this.elementCount);
    this.elementData = newElementData;
  }

////////////////////////////////////////////////////////////////////////////////
// Inner classes
////////////////////////////////////////////////////////////////////////////////

  private class IntIterator implements util.IntIterator
  {
    private IntVector v;

    private int iterator;

    private boolean removedFlag;

    /**
     * Constructs an object of the <code>Iterator</code> type from a
     * <code>IntVector</code>.
     *
     * @param   v  the <code>IntVector</code> from which the iterator has to be created.
     */
    public IntIterator(IntVector v)
    {
      this.v = v;
      this.iterator = -1; // next() must be called to get the first element!
      this.removedFlag = true; // Unless next is called at least once, nothing can be removed!
    }

    /**
     * Returns <code>true</code> if the iteration has more elements. (In other
     * words, returns <code>true</code> if <code>next</code> would return an
     * element rather than throwing an exception.)
     *
     * @return      <code>true</code> if the iterator has more elements.
     */
    public boolean hasNext()
    {
      return this.iterator != (this.v.size()-1);
    }

    /**
     * Returns the next element (<code>int</code>) in the interation.<p>
     * Note that the returned element is <i>typed</i>.
     *
     * @return      the next <code>int</code> in the iteration.
     */
    public int next() throws NoSuchElementException
    {
      if (!hasNext())
        throw new NoSuchElementException();

      this.removedFlag = false;

      return this.v.elementAt(++this.iterator);
    }

    /**
     * Removes from the underlying collection the last element returned by the
     * iterator (optional operation). This method can be called only once per call
     * to <code>next</code>. The behavior of an iterator is unspecified if the
     * underlying collection is modified while the iteration is in progress in any
     * way other than by calling this method (this means that strange things can
     * happen).
     *
     * @exception   IllegalStateException
     */
    public void remove() throws IllegalStateException
    {
      if (this.removedFlag)
        throw new IllegalStateException("This element has already been removed, next() was never called, or simply no elements left!");

      v.removeElementAt(this.iterator);
      this.iterator--; // After removal, all other elements have shifted, so adjust iterator accordingly!
      this.removedFlag = true;
    }

  }
  
}
