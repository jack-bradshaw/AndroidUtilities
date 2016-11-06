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

package com.matthewtamlin.android_utilities_library;

import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnItemAddedListener;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnItemRemovedListener;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnListChangedListener;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnListClearedListener;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the {@link ArrayListWithCallbacks} class.
 */
public class TestArrayListWithCallbacks {
	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final String STRING_1 = "string1";

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final String STRING_2 = "string2";

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final String STRING_3 = "string3";

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final int LENGTH_1 = 10;

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final int LENGTH_2 = 5;

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final int LENGTH_3 = 12;

	/**
	 * A constant which simplifies test writing and refactoring.
	 */
	private static final int INDEX = 3;

	/**
	 * A list for use in testing. Before use it must be initialised to contain {@code LENGTH_1}
	 * instances of {@code STRING_1}.
	 */
	private ArrayListWithCallbacks<String> listInitString1;

	/**
	 * A list for use in testing. Before use it must be initialised to contain {@code LENGTH_1}
	 * instances of {@code null}.
	 */
	private ArrayListWithCallbacks<String> listInitNull;

	/**
	 * A list for use in testing. Before use it must be initialised to contain {@code LENGTH_1}
	 * instances of {@code STRING_1}, followed by {@code LENGTH_2} instances of {@code STRING_2},
	 * followed by {@code LENGTH_3} instances of {@code STRING_3}.
	 */
	private ArrayListWithCallbacks<String> listInitThreeStrings;

	/**
	 * A list for use in testing. Before use it must be initialised to contain no elements.
	 */
	private ArrayListWithCallbacks<String> listInitEmpty;

	/**
	 * Mock of the {@code OnItemAddedListener} class. This variable must be initialised before
	 * testing, and it must be registered to {@code listInitString1}, {@code listInitNull}, {@code
	 * listInitThreeStrings} and {@code listInitEmpty}.
	 */
	private OnItemAddedListener mockOnItemAddedListener;

	/**
	 * Mock of the {@code OnItemRemovedListener} class. This variable must be initialised before
	 * testing, and it must be registered to {@code listInitString1}, {@code listInitNull}, {@code
	 * listInitThreeStrings} and {@code listInitEmpty}.
	 */
	private OnItemRemovedListener mockOnItemRemovedListener;

	/**
	 * Mock of the {@code OnListClearedListener} class. This variable must be initialised before
	 * testing, and it must be registered to {@code listInitString1}, {@code listInitNull}, {@code
	 * listInitThreeStrings} and {@code listInitEmpty}.
	 */
	private OnListClearedListener mockOnListClearedListener;

	/**
	 * Mock of the {@code OnListChangedListener} class. This variable must be initialised before
	 * testing, and it must be registered to {@code listInitString1}, {@code listInitNull}, {@code
	 * listInitThreeStrings} and {@code listInitEmpty}.
	 */
	private OnListChangedListener mockOnListChangedListener;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 */
	@Before
	public void setup() {
		// Create mock callback event listeners
		mockOnItemAddedListener = mock(OnItemAddedListener.class);
		mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		mockOnListClearedListener = mock(OnListClearedListener.class);
		mockOnListChangedListener = mock(OnListChangedListener.class);

		// Create a list populated with one type of String and register the mock listeners
		listInitString1 = getPopulatedList(STRING_1, LENGTH_1);
		listInitString1.addOnItemAddedListener(mockOnItemAddedListener);
		listInitString1.addOnItemRemovedListener(mockOnItemRemovedListener);
		listInitString1.addOnListClearedListener(mockOnListClearedListener);
		listInitString1.addOnItemAddedListener(mockOnListChangedListener);
		listInitString1.addOnItemRemovedListener(mockOnListChangedListener);
		listInitString1.addOnListClearedListener(mockOnListChangedListener);

		// Create a list populated with only null and register the mock listeners
		listInitNull = getPopulatedList(null, LENGTH_1);
		listInitNull.addOnItemAddedListener(mockOnItemAddedListener);
		listInitNull.addOnItemRemovedListener(mockOnItemRemovedListener);
		listInitNull.addOnListClearedListener(mockOnListClearedListener);
		listInitNull.addOnItemAddedListener(mockOnListChangedListener);
		listInitNull.addOnItemRemovedListener(mockOnListChangedListener);
		listInitNull.addOnListClearedListener(mockOnListChangedListener);

		// Create a list populated with three different strings and register the mock listeners
		listInitThreeStrings = new ArrayListWithCallbacks<>();
		listInitThreeStrings.addAll(getPopulatedList(STRING_1, LENGTH_1));
		listInitThreeStrings.addAll(getPopulatedList(STRING_2, LENGTH_2));
		listInitThreeStrings.addAll(getPopulatedList(STRING_3, LENGTH_3));
		listInitThreeStrings.addOnItemAddedListener(mockOnItemAddedListener);
		listInitThreeStrings.addOnItemRemovedListener(mockOnItemRemovedListener);
		listInitThreeStrings.addOnListClearedListener(mockOnListClearedListener);
		listInitThreeStrings.addOnItemAddedListener(mockOnListChangedListener);
		listInitThreeStrings.addOnItemRemovedListener(mockOnListChangedListener);
		listInitThreeStrings.addOnListClearedListener(mockOnListChangedListener);

		// Create an empty list and register the mock listeners
		listInitEmpty = new ArrayListWithCallbacks<>();
		listInitEmpty.addOnItemAddedListener(mockOnItemAddedListener);
		listInitEmpty.addOnItemRemovedListener(mockOnItemRemovedListener);
		listInitEmpty.addOnListClearedListener(mockOnListClearedListener);
		listInitEmpty.addOnItemAddedListener(mockOnListChangedListener);
		listInitEmpty.addOnItemRemovedListener(mockOnListChangedListener);
		listInitEmpty.addOnListClearedListener(mockOnListChangedListener);
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#add(Object)} method functions correctly
	 * when provided with valid arguments. This test considers the case where the value passed to
	 * the {@code object} argument is null. The test will only pass if the list is correctly
	 * modified, and the correct callbacks are delivered.
	 */
	@Test
	public void add1_validArgument_nullObject() {
		listInitString1.add(null);

		// Check that the list was modified correctly
		assertThat("element was rejected", listInitString1.contains(null), is(true));
		assertThat("element was inserted at the wrong index", listInitString1.indexOf(null),
				is(listInitString1.size() - 1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(listInitString1.size() - 1));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(listInitString1.size() - 1));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'cleared' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#add(Object)} method functions correctly
	 * when provided with valid arguments. This test considers the case where the value passed to
	 * the {@code object} argument is not null. The test will only pass if the list is correctly
	 * modified, and the correct callbacks are delivered.
	 */
	@Test
	public void add1_validArgument_nonNullObject() {
		listInitString1.add(STRING_2);

		// Check that the list was modified correctly
		assertThat("element was rejected", listInitString1.contains(STRING_2), is(true));
		assertThat("element was inserted at the wrong index", listInitString1.indexOf(STRING_2),
				is(listInitString1.size() - 1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitString1), eq(STRING_2),
				eq(listInitString1.size() - 1));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitString1), eq(STRING_2),
				eq(listInitString1.size() - 1));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#add(int, Object)} is negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void add2_invalidArgument_negativeIndex() {
		listInitString1.add(-1, STRING_1); // Should throw exception
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#add(int, Object)} exceeds the upper bound.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void add2_invalidArgument_indexTooLarge() {
		listInitString1.add(listInitString1.size() + 1, STRING_1); // Should throw exception
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#add(int, Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void add2_validArguments_nullObject() {
		listInitString1.add(INDEX, null);

		// Check that the list was modified correctly
		assertThat("element was rejected", listInitString1.contains(null), is(true));
		assertThat("element was inserted at the wrong index", listInitString1.indexOf(null),
				is(INDEX));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(INDEX));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#add(int, Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is not null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void add2_validArguments_nonNullObject() {
		listInitNull.add(INDEX, STRING_2);

		// Check that the list was modified correctly
		assertThat("element was rejected", listInitNull.contains(STRING_2), is(true));
		assertThat("element was inserted at the wrong index", listInitNull.indexOf(STRING_2),
				is(INDEX));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitNull), eq(STRING_2),
				eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitNull), eq(STRING_2),
				eq(INDEX));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitNull), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitNull), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitNull));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitNull));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code collection} argument of
	 * {@link ArrayListWithCallbacks#addAll(Collection)} is null.
	 */
	@Test(expected = NullPointerException.class)
	public void addAll1_invalidArgument_nullCollection() {
		listInitString1.addAll(null); // Should throw exception
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#addAll(Collection)} method functions
	 * correctly when provided with valid arguments. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void addAll1_validArgument() {
		final ArrayList<String> newElements = new ArrayList<>();
		newElements.addAll(getPopulatedList(STRING_2, LENGTH_2));
		newElements.addAll(getPopulatedList(null, LENGTH_3));

		listInitString1.addAll(newElements);

		// Check that the list was modified correctly
		assertThat("elements were rejected", Collections.frequency(listInitString1, STRING_2),
				is(LENGTH_2));
		assertThat("elements were rejected", Collections.frequency(listInitString1, null),
				is(LENGTH_3));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(LENGTH_2)).onItemAdded(eq(listInitString1),
				eq(STRING_2), intThat(isBetween(LENGTH_1, LENGTH_1 + LENGTH_2)));
		verify(mockOnItemAddedListener, times(LENGTH_3)).onItemAdded(eq(listInitString1), eq(null),
				intThat(isBetween(LENGTH_1 + LENGTH_2, LENGTH_1 + LENGTH_2 + LENGTH_3)));
		verify(mockOnListChangedListener, times(LENGTH_2)).onItemAdded(eq(listInitString1),
				eq(STRING_2), intThat(isBetween(LENGTH_1, LENGTH_1 + LENGTH_2)));
		verify(mockOnListChangedListener, times(LENGTH_3)).onItemAdded(eq(listInitString1),
				eq(null), intThat(isBetween(LENGTH_1 + LENGTH_2, LENGTH_1 + LENGTH_2 + LENGTH_3)));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code collection} argument of
	 * {@link ArrayListWithCallbacks#addAll(int, Collection)} is null.
	 */
	@Test(expected = NullPointerException.class)
	public void addAll2_invalidArgument_nullCollection() {
		listInitString1.addAll(INDEX, null); // Should throw exception
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#addAll(int, Collection)} is negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void addAll2_invalidArgument_negativeIndex() {
		final Collection<String> newItems = getPopulatedList(STRING_2, LENGTH_2);

		listInitString1.addAll(-1, newItems); // Should throw Exception
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#addAll(int, Collection)} exceeds the upper bound.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void addAll2_invalidArgument_indexTooLarge() {
		final Collection<String> newItems = getPopulatedList(STRING_2, LENGTH_2);

		listInitString1.addAll(listInitString1.size() + 1, newItems); // Should throw Exception
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#addAll(int, Collection)} method
	 * functions correctly when provided with valid arguments. The test will only pass if the list
	 * is correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void addAll2_validArguments() {
		final Collection<String> newItems = getPopulatedList(null, LENGTH_2);

		listInitString1.addAll(INDEX, newItems);

		// Check that the list was modified correctly
		assertThat("elements were rejected", Collections.frequency(listInitString1, null),
				is(LENGTH_2));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(LENGTH_2)).onItemAdded(eq(listInitString1), eq(null),
				intThat(isBetween(INDEX, INDEX + LENGTH_2)));
		verify(mockOnListChangedListener, times(LENGTH_2)).onItemAdded(eq(listInitString1),
				eq(null), intThat(isBetween(INDEX, INDEX + LENGTH_2)));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#remove(int)} is negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void remove1_invalidArgument_negativeIndex() {
		listInitString1.remove(-1); // Should throw exception
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#remove(int)} exceeds the upper bound.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void remove1_invalidArgument_indexTooLarge() {
		listInitString1.remove(listInitString1.size() + 1);
	}

	/**
	 * Test to ensure that {@link ArrayListWithCallbacks#remove(int)} functions correctly when a
	 * valid index is passed. The test will only pass if the list is correctly modified, and the
	 * correct callbacks are delivered.
	 */
	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#remove(int)} method functions correctly
	 * when provided with valid arguments. The test will only pass if the list is correctly
	 * modified, and the correct callbacks are delivered.
	 */
	@Test
	public void remove1_validArgument() {
		listInitNull.remove(0);

		// Check that the list was modified correctly
		assertThat("element was retained", Collections.frequency(listInitNull, null),
				is(LENGTH_1 - 1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(eq(listInitNull), eq(null),
				intThat(is(0)));
		verify(mockOnListChangedListener, times(1)).onItemRemoved(eq(listInitNull), eq(null),
				intThat(is(0)));

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitNull));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitNull));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#remove(Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void remove2_validArgument_nullObject() {
		listInitNull.remove(null);

		// Check that the list was modified correctly
		assertThat("element was retained", Collections.frequency(listInitNull, null),
				is(LENGTH_1 - 1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(eq(listInitNull), eq(null),
				intThat(is(0)));
		verify(mockOnListChangedListener, times(1)).onItemRemoved(eq(listInitNull), eq(null),
				intThat(is(0)));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitNull));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitNull));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#remove(Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is not null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void remove2_validArgument_nonNullObject() {
		listInitString1.remove(STRING_1);

		// Check that the list was modified correctly
		assertThat("element was retained", Collections.frequency(listInitString1,
				STRING_1), is(LENGTH_1 - 1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitString1), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitString1), anyString(),
				anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(eq(listInitString1), eq(STRING_1),
				intThat(is(0)));
		verify(mockOnListChangedListener, times(1)).onItemRemoved(eq(listInitString1), eq(STRING_1),
				intThat(is(0)));

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code collection} argument of
	 * {@link ArrayListWithCallbacks#removeAll(Collection)} is null.
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("ConstantConditions")
	public void removeAll_invalidArgument_nullCollection() {
		listInitNull.removeAll(null); // Should throw exception
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#removeAll(Collection)} method functions
	 * correctly when provided with valid arguments. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void removeAll_validArgument() {
		final Collection<String> stringsToRemove = new ArrayList<>();
		stringsToRemove.add(STRING_1);
		stringsToRemove.add(STRING_2);

		listInitThreeStrings.removeAll(stringsToRemove);

		// Check that the list was modified correctly
		assertThat("elements were retained",
				Collections.frequency(listInitThreeStrings, STRING_1), is(0));
		assertThat("elements were retained",
				Collections.frequency(listInitThreeStrings, STRING_2), is(0));
		assertThat("elements were unexpectedly removed",
				Collections.frequency(listInitThreeStrings, STRING_3), is(LENGTH_3));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitThreeStrings), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitThreeStrings),
				anyString(), anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(LENGTH_1 + LENGTH_2)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());
		verify(mockOnListChangedListener, times(LENGTH_1 + LENGTH_2)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitThreeStrings));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitThreeStrings));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#set(int, Object)} is negative.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void set_invalidArgument_negativeIndex() {
		listInitNull.set(-1, STRING_1); // Should throw exception
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code index} argument of {@link
	 * ArrayListWithCallbacks#set(int, Object)} exceeds the upper bound.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void set_invalidArgument_indexTooLarge() {
		listInitNull.set(listInitNull.size() + 1, STRING_1);
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#set(int, Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void set_validArguments_nullObject() {
		listInitString1.set(INDEX, null);

		// Check that the list was modified correctly
		assertThat("element not set", listInitString1.get(INDEX), is(nullValue()));
		assertThat("the Collection size changed", listInitString1.size(), is(LENGTH_1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitString1), eq(null),
				eq(INDEX));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(eq(listInitString1),
				eq(STRING_1), eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemRemoved(eq(listInitString1),
				eq(STRING_1), eq(INDEX));

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#set(int, Object)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code object} argument is not null. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void set_validArguments_nonNullObject() {
		listInitString1.set(INDEX, STRING_2);

		// Check that the list was modified correctly
		assertThat("element not set", listInitString1.get(INDEX), is(STRING_2));
		assertThat("the Collection size changed", listInitString1.size(), is(LENGTH_1));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(listInitString1), eq(STRING_2),
				eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemAdded(eq(listInitString1), eq(STRING_2),
				eq(INDEX));

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(eq(listInitString1), eq(STRING_1),
				eq(INDEX));
		verify(mockOnListChangedListener, times(1)).onItemRemoved(eq(listInitString1), eq(STRING_1),
				eq(INDEX));

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(eq(listInitString1));
		verify(mockOnListChangedListener, times(0)).onListCleared(eq(listInitString1));
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#clear()} method functions correctly.
	 * This test considers the case where the list is already empty before the method is called. The
	 * test will only pass if the list is correctly modified, and the correct callbacks are
	 * delivered.
	 */
	@Test
	public void clear_calledOnEmptyList() {
		listInitEmpty.clear();

		// Check that the list was modified correctly
		assertThat("list is not empty", listInitEmpty.isEmpty(), is(true));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitEmpty), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitEmpty), anyString(),
				anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitEmpty), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitEmpty), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(1)).onListCleared(listInitEmpty);
		verify(mockOnListChangedListener, times(1)).onListCleared(listInitEmpty);
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#clear()} method functions correctly.
	 * This test considers the case where the list is not empty before the method is called. The
	 * test will only pass if the list is correctly modified, and the correct callbacks are
	 * delivered.
	 */
	@Test
	public void clear_calledOnNonEmptyList() {
		listInitNull.clear();

		// Check that the list was modified correctly
		assertThat("list is not empty", listInitNull.isEmpty(), is(true));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitNull), anyString(),
				anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(0)).onItemRemoved(eq(listInitNull), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemRemoved(eq(listInitNull), anyString(),
				anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(1)).onListCleared(listInitNull);
		verify(mockOnListChangedListener, times(1)).onListCleared(listInitNull);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code collection} argument of
	 * {@link ArrayListWithCallbacks#retainAll(Collection)} is null.
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("ConstantConditions")
	public void retainAll_invalidArg_nullCollection() {
		listInitString1.retainAll(null); // Should throw exception
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#retainAll(Collection)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code collection} argument is not empty. The test will only pass if the list
	 * is correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void retainAll_validArg_populatedCollection() {
		final Collection<String> stringsToRetain = new ArrayList<>();
		stringsToRetain.add(STRING_1);
		stringsToRetain.add(null);

		listInitThreeStrings.retainAll(stringsToRetain);

		// Check that the list was modified correctly
		assertThat("elements were unexpectedly added", listInitThreeStrings.contains(null),
				is(false));
		assertThat("elements were unexpectedly removed", listInitThreeStrings.contains(STRING_1),
				is(true));
		assertThat("elements were unexpectedly added", listInitThreeStrings.contains(STRING_2),
				is(false));
		assertThat("elements were unexpectedly added", listInitThreeStrings.contains(STRING_3),
				is(false));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitThreeStrings), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitThreeStrings),
				anyString(), anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(LENGTH_2 + LENGTH_3)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());
		verify(mockOnListChangedListener, times(LENGTH_2 + LENGTH_3)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(listInitThreeStrings);
		verify(mockOnListChangedListener, times(0)).onListCleared(listInitThreeStrings);
	}

	/**
	 * Test to verify that the {@link ArrayListWithCallbacks#retainAll(Collection)} method functions
	 * correctly when provided with valid arguments. This test considers the case where the value
	 * passed to the {@code collection} argument is empty. The test will only pass if the list is
	 * correctly modified, and the correct callbacks are delivered.
	 */
	@Test
	public void retainAll_validArg_emptyCollection() {
		final Collection<String> emptyCollection = new ArrayList<>();

		listInitThreeStrings.retainAll(emptyCollection);

		// Check that the list was modified correctly
		assertThat("elements were unexpectedly added to the list", listInitThreeStrings.size(),
				is(0));

		// Check that the correct 'add' callbacks were delivered
		verify(mockOnItemAddedListener, times(0)).onItemAdded(eq(listInitThreeStrings), anyString(),
				anyInt());
		verify(mockOnListChangedListener, times(0)).onItemAdded(eq(listInitThreeStrings),
				anyString(), anyInt());

		// Check that the correct 'remove' callbacks were delivered
		verify(mockOnItemRemovedListener, times(LENGTH_1 + LENGTH_2 + LENGTH_3)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());
		verify(mockOnListChangedListener, times(LENGTH_1 + LENGTH_2 + LENGTH_3)).onItemRemoved(
				eq(listInitThreeStrings), anyString(), anyInt());

		// Check that the correct 'clear' callbacks were delivered
		verify(mockOnListClearedListener, times(0)).onListCleared(listInitThreeStrings);
		verify(mockOnListChangedListener, times(0)).onListCleared(listInitThreeStrings);
	}

	/**
	 * Creates a new ArrayListWithCallbacks. The list is filled to the specified length with the
	 * specified element.
	 *
	 * @param element
	 * 		the element to fill the list with
	 * @param length
	 * 		the number of elements to add to the list, greater than 0
	 * @return the new list
	 * @throws IllegalArgumentException
	 * 		if {@code length} is less than 0
	 */
	private static ArrayListWithCallbacks<String> getPopulatedList(final String element, final int
			length) {
		if (length < 0) {
			throw new IllegalArgumentException("length cannot be less than 0");
		}

		// Store elements in the prototype list before constructing the ArrayListWithCallbacks
		final ArrayList<String> prototypeList = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			prototypeList.add(element);
		}

		return new ArrayListWithCallbacks<>(prototypeList);
	}

	/**
	 * Creates a Matcher which covers a range of Integers.
	 *
	 * @param lowerBoundInclusive
	 * 		the lower bound of the range, inclusive
	 * @param upperBoundExclusive
	 * 		the upper bound of the range, exclusive
	 * @return a Matcher which covers the provided range
	 */
	private Matcher<Integer> isBetween(int lowerBoundInclusive, int upperBoundExclusive) {
		final Matcher<Integer> lowerBoundMatcher = greaterThanOrEqualTo(lowerBoundInclusive);
		final Matcher<Integer> upperBoundMatcher = lessThan(upperBoundExclusive);
		return is(both(lowerBoundMatcher).and(upperBoundMatcher));
	}
}