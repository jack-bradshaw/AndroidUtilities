package com.matthewtamlin.android_utilities_library.collections;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * An ArrayList which dispatches callback events to registered listeners when the Collection is
 * modified.
 *
 * @param <T> the type of objects contained within this collection
 */
public class ArrayListWithCallbacks<T> extends ArrayList<T> {
	/**
	 * Used to identify this class during debugging.
	 */
	private static final String TAG = "[ArrayListWithCallb...]";

	/**
	 * The listener to receive callbacks when an item is added to this Collection. This
	 * listener may be called when any of the following methods are invoked:
	 * <li>{@link #add(Object)}</li>
	 * <li>{@link #add(int, Object)}</li>
	 * <li>{@link #addAll(Collection)}</li>
	 * <li>{@link #addAll(int, Collection)}</li>
	 * <li>{@link #set(int, Object)}</li>
	 */
	private List<OnItemAddedListener> onItemAddedListeners = new ArrayList<>();

	/**
	 * The listener to receive callbacks when an item is removed from this Collection. This
	 * listener may be called when the following methods are invoked:
	 * <li>{@link #remove(int)}</li>
	 * <li>{@link #remove(Object)}</li>
	 * <li>{@link #removeAll(Collection)}</li>
	 * <li>{@link #set(int, Object)}</li>
	 * <li>{@link #retainAll(Collection)}</li>
	 */
	private List<OnItemRemovedListener> onItemRemovedListeners = new ArrayList<>();

	/**
	 * The listener to receive callbacks when the Collection is cleared. This listener will be
	 * called each time {@link #clear()} is invoked, even if the Collection was empty at the time
	 * of invocation.
	 */
	private List<OnListClearedListener> onListClearedListeners = new ArrayList<>();

	/**
	 * Constructs a new instance of {@code ArrayListWithCallbacks}.
	 */
	public ArrayListWithCallbacks() {
		super();
	}

	/**
	 * Constructs a new instance of {@code ArrayListWithCallbacks} containing the elements of the
	 * specified collection.
	 *
	 * @param collection the collection of elements to add
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

	public interface OnItemAddedListener {
		void onItemAdded(ArrayList list, Object itemAdded, int index);
	}

	public interface OnItemRemovedListener {
		void onItemRemoved(ArrayList list, Object itemRemoved, int index);
	}

	public interface OnListClearedListener {
		void onListCleared(ArrayList list);
	}

	public void addOnItemAddedListener(OnItemAddedListener listener) {
		if (listener != null) {
			onItemAddedListeners.add(listener);
		}
	}

	public void removeOnItemAddedListener(OnItemAddedListener listener) {
		onItemAddedListeners.remove(listener);
	}

	public void addOnItemRemovedListener(OnItemRemovedListener listener) {
		if (listener != null) {
			onItemRemovedListeners.add(listener);
		}
	}

	public void removeOnItemRemovedListener(OnItemRemovedListener listener) {
		onItemRemovedListeners.remove(listener);
	}

	public void addOnListClearedListener(OnListClearedListener listener) {
		if (listener != null) {
			onListClearedListeners.add(listener);
		}
	}

	public void removeOnListClearedListener(OnListClearedListener listener) {
		onListClearedListeners.remove(listener);
	}

	private void callOnItemAddedListeners(Object itemAdded, int index) {
		for (OnItemAddedListener l : onItemAddedListeners) {
			l.onItemAdded(this, itemAdded, index);
		}

	}

	private void callOnItemRemovedListeners(Object itemRemoved, int index) {
		for (OnItemRemovedListener l : onItemRemovedListeners) {
			l.onItemRemoved(this, itemRemoved, index);
		}
	}

	private void callOnListClearedListeners() {
		for (OnListClearedListener l : onListClearedListeners) {
			l.onListCleared(this);
		}
	}
}
