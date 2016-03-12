package com.matthewtamlin.android_utilities_library.collections;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An ArrayList which dispatches callback events to registered listeners whenever the {@code
 * Collection} is modified.
 *
 * @param <T>
 * 		the type of objects contained within this {@code collection}
 */
public class ArrayListWithCallbacks<T> extends ArrayList<T> {
	/**
	 * The listeners to receive callbacks when an item is added to this {@code Collection}. The
	 * listeners must be called if a call to one of the following methods results in one or more
	 * elements being added to the {@code Collection}:<p/>
	 * <li>{@link #add(Object)}</li>
	 * <li>{@link #add(int, Object)}</li>
	 * <li>{@link #addAll(Collection)}</li>
	 * <li>{@link #addAll(int, Collection)}</li>
	 * <li>{@link #set(int, Object)}</li>
	 * <p/>
	 * Each added element must have a separate callback invocation.
	 */
	private Collection<OnItemAddedListener> onItemAddedListeners = new HashSet<>();

	/**
	 * The listeners to receive callbacks when an item is removed from this {@code Collection}. The
	 * listeners must be called if a call to one of the following methods results in one or more
	 * elements being removed from the {@code Collection}:<p/>
	 * <li>{@link #remove(int)}</li>
	 * <li>{@link #remove(Object)}</li>
	 * <li>{@link #removeAll(Collection)}</li>
	 * <li>{@link #set(int, Object)}</li>
	 * <li>{@link #retainAll(Collection)}</li>
	 * <p/>
	 * Each removed element must have a separate callback invocation.
	 */
	private Collection<OnItemRemovedListener> onItemRemovedListeners = new HashSet<>();

	/**
	 * The listeners to receive callbacks when this {@code Collection} is cleared. The listeners
	 * must be called each time {@link #clear()} is invoked, even if the {@code Collection} was
	 * empty at the time of invocation.
	 */
	private Collection<OnListClearedListener> onListClearedListeners = new HashSet<>();

	/**
	 * Constructs a new instance of {@code ArrayListWithCallbacks}.
	 */
	public ArrayListWithCallbacks() {
		super();
	}

	/**
	 * Constructs a new instance of {@code ArrayListWithCallbacks} containing the elements of the
	 * specified collection. This method will not invoke callbacks.
	 *
	 * @param collection
	 * 		the collection of elements to add
	 */
	public ArrayListWithCallbacks(Collection<T> collection) {
		super(collection);
	}

	@Override
	public boolean add(T object) {
		super.add(object);
		callOnItemAddedListeners(object, indexOf(object));
		return true;
	}

	@Override
	public void add(int index, T object) {
		super.add(index, object);
		callOnItemAddedListeners(object, index);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		for (T t : collection) {
			add(t);
		}

		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		int workingIndex = index;

		for (T t : collection) {
			add(workingIndex, t);
			workingIndex++;
		}

		return true;
	}

	@Override
	public T remove(int index) {
		T removedItem = super.remove(index);
		callOnItemRemovedListeners(removedItem, index);
		return removedItem;
	}

	@Override
	public boolean remove(Object object) {
		int potentialIndexOfRemoved = indexOf(object); // will be -1 if no object isn't in list

		if (super.remove(object)) {
			callOnItemRemovedListeners(object, potentialIndexOfRemoved);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(@NonNull Collection<?> collection) {
		boolean thisCollectionWasModified = false;

		for (Iterator<T> i = this.iterator(); i.hasNext(); ) {
			T nextElement = i.next();

			if (collection.contains(nextElement)) {
				i.remove();
				thisCollectionWasModified = true;
			}
		}

		return thisCollectionWasModified;
	}

	@Override
	public T set(int index, T object) {
		T removedItem = remove(index);
		add(index, object);
		return removedItem;
	}

	@Override
	public void clear() {
		super.clear();
		callOnListClearedListeners();
	}

	@Override
	public boolean retainAll(@NonNull Collection<?> collection) {
		boolean collectionWasModified = false;

		for (Iterator<T> i = this.iterator(); i.hasNext(); ) {
			T nextElementInThis = i.next();

			if (!collection.contains(nextElementInThis)) {
				i.remove();
				collectionWasModified = true;
			}
		}

		return collectionWasModified;
	}

	/**
	 * Interface definition for callbacks to be invoked whenever an item is added to an {@code
	 * ArrayListWithCallbacks}.
	 */
	public interface OnItemAddedListener {
		/**
		 * Invoked whenever an item is added to an {@code ArrayListWithCallbacks} which this
		 * listener is registered to.
		 *
		 * @param list
		 * 		the {@code ArrayListWithCallbacks} which was modified
		 * @param itemAdded
		 * 		the item which was added
		 * @param index
		 * 		the index of the item which was added
		 */
		void onItemAdded(ArrayListWithCallbacks list, Object itemAdded, int index);
	}

	/**
	 * Interface definition for callbacks to be received whenever an item is removed from an
	 * {@code ArrayListWithCallbacks}.
	 */
	public interface OnItemRemovedListener {
		/**
		 * Invoked whenever an item is removed from an {@code ArrayListWithCallbacks} which this
		 * listener is registered to.
		 *
		 * @param list
		 * 		the {@code ArrayListWithCallbacks} which was modified
		 * @param itemRemoved
		 * 		the item which was removed
		 * @param index
		 * 		the index of the item which was removed
		 */
		void onItemRemoved(ArrayListWithCallbacks list, Object itemRemoved, int index);
	}

	/**
	 * Interface definition for callbacks to be received whenever an {@code ArrayListWithCallbacks}
	 * is cleared.
	 */
	public interface OnListClearedListener {
		/**
		 * Invoked whenever an {@code ArrayListWithCallbacks} which this listener is registered to
		 * is cleared.
		 *
		 * @param list
		 * 		the {@code ArrayListWithCallbacks} which was modified
		 */
		void onListCleared(ArrayListWithCallbacks list);
	}

	/**
	 * Interface definition for callbacks to be received whenever an {@code ArrayListWithCallbacks}
	 * is modified.
	 */
	public interface OnListChangedListener extends OnItemAddedListener, OnItemRemovedListener,
			OnListClearedListener {}

	/**
	 * Adds an {@code OnItemAddedListener} to this {@code ArrayListWithCallbacks}. This method has
	 * no effect if null is supplied, or if the listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public void addOnItemAddedListener(OnItemAddedListener listener) {
		if (listener != null) {
			onItemAddedListeners.add(listener);
		}
	}

	/**
	 * Adds an {@code OnItemRemovedListener} to this {@code ArrayListWithCallbacks}. This method
	 * has no effect if null is supplied, or if the listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public void addOnItemRemovedListener(OnItemRemovedListener listener) {
		if (listener != null) {
			onItemRemovedListeners.add(listener);
		}
	}

	/**
	 * Adds an {@code OnListClearedListener} to this {@code ArrayListWithCallbacks}. This method
	 * has no effect if null is supplied or if the listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public void addOnListClearedListener(OnListClearedListener listener) {
		if (listener != null) {
			onListClearedListeners.add(listener);
		}
	}

	/**
	 * Adds an {@code OnListChangedListener} to this {@code ArrayListWithCallbacks}. this method
	 * has no effect if null is supplied or if the listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public void addOnListChangedListener(OnListChangedListener listener) {
		addOnItemAddedListener(listener);
		addOnItemRemovedListener(listener);
		addOnListClearedListener(listener);
	}

	/**
	 * Removes an {@code OnItemAddedListener} from this {@code ArrayListWithCallbacks}. This method
	 * has no effect if null is supplied, or if the listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public void removeOnItemAddedListener(OnItemAddedListener listener) {
		onItemAddedListeners.remove(listener);
	}

	/**
	 * Removes an {@code OnItemRemovedListener} from this {@code ArrayListWithCallbacks}. This
	 * method has no effect if null is supplied or if the listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public void removeOnItemRemovedListener(OnItemRemovedListener listener) {
		onItemRemovedListeners.remove(listener);
	}

	/**
	 * Removes an {@code OnListClearedListener} from this {@code ArrayListWithCallbacks}. This
	 * method has no effect if null is supplied or if the listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public void removeOnListClearedListener(OnListClearedListener listener) {
		onListClearedListeners.remove(listener);
	}

	/**
	 * Removed an {@code OnListChangedListener} from this {@code ArrayListWithCallbacks}. This
	 * method has no effect if null is supplied or if the listener is not registered.
	 *
	 * @param listener the listener to remove
	 */
	public void removeOnListChangedListener(OnListChangedListener listener) {
		removeOnItemAddedListener(listener);
		removeOnItemRemovedListener(listener);
		removeOnListClearedListener(listener);
	}

	/**
	 * Calls each registered {@code OnItemAddedListener}.
	 *
	 * @param itemAdded
	 * 		the item which was added to the {@code ArrayListWithCallbacks}
	 * @param index
	 * 		the index of the added item
	 */
	private void callOnItemAddedListeners(Object itemAdded, int index) {
		for (OnItemAddedListener l : onItemAddedListeners) {
			l.onItemAdded(this, itemAdded, index);
		}

	}

	/**
	 * Calls each registered {@code OnItemRemovedListener}.
	 *
	 * @param itemRemoved
	 * 		the item which was removed from the {@code ArrayListWithCallbacks}
	 * @param index
	 * 		the index of the removed item
	 */
	private void callOnItemRemovedListeners(Object itemRemoved, int index) {
		for (OnItemRemovedListener l : onItemRemovedListeners) {
			l.onItemRemoved(this, itemRemoved, index);
		}
	}

	/**
	 * Calls each registered {@code OnListClearedListener}.
	 */
	private void callOnListClearedListeners() {
		for (OnListClearedListener l : onListClearedListeners) {
			l.onListCleared(this);
		}
	}
}