/*
 * Copyright 2016 Matthew Tamlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matthewtamlin.android_utilities_library.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * An ArrayList which dispatches callback events to registered listeners whenever the Collection is
 * modified.
 *
 * @param <T>
 * 		the type of objects contained within this Collection
 */
public final class ArrayListWithCallbacks<T> extends ArrayList<T> {
	/**
	 * Callback events are delivered to these listeners whenever one or more items are added to this
	 * Collections.
	 */
	private final Collection<OnItemAddedListener> onItemAddedListeners = new HashSet<>();

	/**
	 * Callback events are delivered to these listeners whenever one or more items are removed from
	 * this Collection.
	 */
	private final Collection<OnItemRemovedListener> onItemRemovedListeners = new HashSet<>();

	/**
	 * Callback events are delivered to these listeners whenever this Collection is cleared
	 * (including cases where the Collection was already empty).
	 */
	private final Collection<OnListClearedListener> onListClearedListeners = new HashSet<>();

	/**
	 * Constructs an empty instance of ArrayListWithCallbacks.
	 */
	public ArrayListWithCallbacks() {
		super();
	}

	/**
	 * Constructs a new instance of ArrayListWithCallbacks containing the elements of the specified
	 * collection. No callbacks are triggered by this constructor. Elements are inserted into the
	 * new Collection in the order defined by the supplied Collection's iterator.
	 *
	 * @param collection
	 * 		the Collection of elements to add
	 */
	public ArrayListWithCallbacks(final Collection<T> collection) {
		super(collection);
	}

	@Override
	public final boolean add(final T object) {
		super.add(object);
		callOnItemAddedListeners(object, indexOf(object));
		return true;
	}

	@Override
	public final void add(final int index, final T object) {
		super.add(index, object);
		callOnItemAddedListeners(object, index);
	}

	@Override
	public final boolean addAll(final Collection<? extends T> collection) {
		for (final T t : collection) {
			add(t); // Handles callbacks
		}

		return true;
	}

	@Override
	public final boolean addAll(final int index, final Collection<? extends T> collection) {
		int insertionPosition = index;

		for (final T t : collection) {
			add(insertionPosition, t);
			insertionPosition++;
		}

		return true;
	}

	@Override
	public final T remove(final int index) {
		final T removedItem = super.remove(index);
		callOnItemRemovedListeners(removedItem, index);
		return removedItem;
	}

	@Override
	public final boolean remove(final Object object) {
		if (contains(object)) {
			final int index = indexOf(object);
			remove(index); // Handles callbacks
			return true;
		} else {
			return false;
		}
	}

	@Override
	public final boolean removeAll(final Collection<?> collection) {
		boolean thisCollectionWasModified = false;
		final Iterator<T> thisIterator = this.iterator();

		// Iterate over the elements of this Collection
		while (thisIterator.hasNext()) {
			final T nextElementInThis = thisIterator.next();

			// Remove any elements which are in both this Collection and the supplied Collection
			if (collection.contains(nextElementInThis)) {
				thisIterator.remove(); // Handles callbacks
				thisCollectionWasModified = true;
			}
		}

		return thisCollectionWasModified;
	}

	@Override
	public final T set(final int index, final T object) {
		final T removedItem = remove(index); // Handles remove callbacks
		add(index, object); // Handles add callbacks
		return removedItem;
	}

	@Override
	public final void clear() {
		super.clear();
		callOnListClearedListeners();
	}

	@Override
	public final boolean retainAll(final Collection<?> collection) {
		boolean thisCollectionWasModified = false;
		final Iterator<T> thisIterator = this.iterator();

		// Iterate over the elements in this Collection
		while (thisIterator.hasNext()) {
			final T nextElementInThis = thisIterator.next();

			// Remove any elements which are not in both this Collection and the supplied Collection
			if (!collection.contains(nextElementInThis)) {
				thisIterator.remove(); // Handles callbacks
				thisCollectionWasModified = true;
			}
		}

		return thisCollectionWasModified;
	}

	/**
	 * Registers an OnItemAddedListener. This method has no effect if null is supplied, or if the
	 * supplied listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public final void addOnItemAddedListener(final OnItemAddedListener listener) {
		if (listener != null) {
			onItemAddedListeners.add(listener);
		}
	}

	/**
	 * Registers an OnItemRemovedListener. This method has no effect if null is supplied, or if the
	 * supplied listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public final void addOnItemRemovedListener(final OnItemRemovedListener listener) {
		if (listener != null) {
			onItemRemovedListeners.add(listener);
		}
	}

	/**
	 * Registers an OnListCleared. This method has no effect if null is supplied or if the supplied
	 * listener is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public final void addOnListClearedListener(final OnListClearedListener listener) {
		if (listener != null) {
			onListClearedListeners.add(listener);
		}
	}

	/**
	 * Registers an OnListChanged. This method has no effect if null is supplied or if the listener
	 * is already registered.
	 *
	 * @param listener
	 * 		the listener to add
	 */
	public final void addOnListChangedListener(final OnListChangedListener listener) {
		addOnItemAddedListener(listener);
		addOnItemRemovedListener(listener);
		addOnListClearedListener(listener);
	}

	/**
	 * Unregisters an OnItemAddedListener. This method has no effect if null is supplied, or if the
	 * supplied listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public final void removeOnItemAddedListener(final OnItemAddedListener listener) {
		onItemAddedListeners.remove(listener);
	}

	/**
	 * Unregisters an OnItemRemovedListener. This method has no effect if null is supplied or if the
	 * supplied listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public final void removeOnItemRemovedListener(final OnItemRemovedListener listener) {
		onItemRemovedListeners.remove(listener);
	}

	/**
	 * Unregisters an OnListClearedListener. This method has no effect if null is supplied or if the
	 * supplied listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public final void removeOnListClearedListener(final OnListClearedListener listener) {
		onListClearedListeners.remove(listener);
	}

	/**
	 * Unregisters an OnListChangedListener. This method has no effect if null is supplied or if the
	 * supplied listener is not registered.
	 *
	 * @param listener
	 * 		the listener to remove
	 */
	public final void removeOnListChangedListener(final OnListChangedListener listener) {
		removeOnItemAddedListener(listener);
		removeOnItemRemovedListener(listener);
		removeOnListClearedListener(listener);
	}

	/**
	 * Calls each registered OnItemAddedListener.
	 *
	 * @param itemAdded
	 * 		the item which was added to the ArrayListWithCallbacks
	 * @param index
	 * 		the index of the added item
	 */
	private void callOnItemAddedListeners(final Object itemAdded, final int index) {
		for (final OnItemAddedListener l : onItemAddedListeners) {
			l.onItemAdded(this, itemAdded, index);
		}

	}

	/**
	 * Calls each registered OnItemRemovedListener.
	 *
	 * @param itemRemoved
	 * 		the item which was removed from the ArrayListWithCallbacks
	 * @param index
	 * 		the index of the removed item
	 */
	private void callOnItemRemovedListeners(final Object itemRemoved, final int index) {
		for (final OnItemRemovedListener l : onItemRemovedListeners) {
			l.onItemRemoved(this, itemRemoved, index);
		}
	}

	/**
	 * Calls each registered OnListClearedListener.
	 */
	private void callOnListClearedListeners() {
		for (final OnListClearedListener l : onListClearedListeners) {
			l.onListCleared(this);
		}
	}

	/**
	 * Interface definition for callbacks to be delivered whenever an item is added to an
	 * ArrayListWithCallbacks.
	 */
	public interface OnItemAddedListener {
		/**
		 * Invoked when an item is added to an ArrayListWithCallbacks.
		 *
		 * @param list
		 * 		the ArrayListWithCallbacks which was modified, not null
		 * @param itemAdded
		 * 		the item which was added
		 * @param index
		 * 		the index of the item which was added
		 */
		void onItemAdded(ArrayListWithCallbacks list, Object itemAdded, int index);
	}

	/**
	 * Interface definition for callbacks to be received whenever an item is removed from an
	 * ArrayListWithCallbacks.
	 */
	public interface OnItemRemovedListener {
		/**
		 * Invoked when an item is removed from an ArrayListWithCallbacks.
		 *
		 * @param list
		 * 		the ArrayListWithCallbacks which was modified, not null
		 * @param itemRemoved
		 * 		the item which was removed
		 * @param index
		 * 		the index of the item which was removed
		 */
		void onItemRemoved(ArrayListWithCallbacks list, Object itemRemoved, int index);
	}

	/**
	 * Interface definition for callbacks to be received whenever an ArrayListWithCallbacks is
	 * cleared.
	 */
	public interface OnListClearedListener {
		/**
		 * Invoked when an ArrayListWithCallbacks is cleared.
		 *
		 * @param list
		 * 		the ArrayListWithCallbacks which was modified, not null
		 */
		void onListCleared(ArrayListWithCallbacks list);
	}

	/**
	 * Interface definition for callbacks to be received whenever an ArrayListWithCallbacks is
	 * modified.
	 */
	public interface OnListChangedListener extends OnItemAddedListener, OnItemRemovedListener,
			OnListClearedListener {}
}