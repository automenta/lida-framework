package edu.memphis.ccrg.lida.framework.shared;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread-safe {@link HashSet} based on a {@link ConcurrentHashMap}
 * 
 * @author Ryan J. McCall
 * 
 * @param <E>
 *            The generic type to use in this HashSet
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>,
		Serializable {

	static final long serialVersionUID = 3313091100336870355L;

	private transient ConcurrentMap<E, Object> map;

	private static final Object PRESENT = new Object();

	public ConcurrentHashSet() {
		this.map = new ConcurrentHashMap<E, Object>();
	}

	public ConcurrentHashSet(Collection<? extends E> paramCollection) {
		this.map = new ConcurrentHashMap<E, Object>(Math.max(
				(int) (paramCollection.size() / 0.75F) + 1, 16));
		addAll(paramCollection);
	}

	public ConcurrentHashSet(int paramInt, float paramFloat) {
		this.map = new ConcurrentHashMap<E, Object>(paramInt, paramFloat);
	}

	public ConcurrentHashSet(int paramInt) {
		this.map = new ConcurrentHashMap<E, Object>(paramInt);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		Iterator<? extends E> e = c.iterator();
		while (e.hasNext()) {
			if (add(e.next()))
				modified = true;
		}
		return modified;
	}

	/**
	 * Returns an iterator over the elements in this set. The elements are
	 * returned in no particular order.
	 * 
	 * @return an Iterator over the elements in this set
	 */
	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	/**
	 * Returns the number of elements in this set (its cardinality).
	 * 
	 * @return the number of elements in this set (its cardinality)
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * Returns <tt>true</tt> if this set contains no elements.
	 * 
	 * @return <tt>true</tt> if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this set contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this set contains an
	 * element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 * 
	 * @param o
	 *            element whose presence in this set is to be tested
	 * @return <tt>true</tt> if this set contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	/**
	 * Adds the specified element to this set if it is not already present. More
	 * formally, adds the specified element <tt>e</tt> to this set if this set
	 * contains no element <tt>e2</tt> such that
	 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>. If this
	 * set already contains the element, the call leaves the set unchanged and
	 * returns <tt>false</tt>.
	 * 
	 * @param e
	 *            element to be added to this set
	 * @return <tt>true</tt> if this set did not already contain the specified
	 *         element
	 */
	@Override
	public boolean add(E e) {
		return map.put(e, PRESENT) == null;
	}

	/**
	 * Removes the specified element from this set if it is present. More
	 * formally, removes an element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if this
	 * set contains such an element. Returns <tt>true</tt> if this set contained
	 * the element (or equivalently, if this set changed as a result of the
	 * call). (This set will not contain the element once the call returns.)
	 * 
	 * @param o
	 *            object to be removed from this set, if present
	 * @return <tt>true</tt> if the set contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		return map.remove(o) == PRESENT;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		map.clear();
	}

}