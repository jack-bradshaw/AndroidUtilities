package com.matthewtamlin.android_utilities_library.collections;

import com.matthewtamlin.android_utilities_library.BuildConfig;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnItemAddedListener;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnItemRemovedListener;
import com.matthewtamlin.android_utilities_library.collections.ArrayListWithCallbacks.OnListClearedListener;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.intThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the ArrayListWithCallbacks class.
 */
@SuppressWarnings("ConstantConditions")
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 18, constants = BuildConfig.class)
public class TestArrayListWithCallbacks {
	private static final String FILLER_STRING = "filler";
	private static final String TEST_STRING = "test";
	private static final int FILLER_LENGTH = 10;
	private static final int TEST_INDEX = 3;
	private static final int TEST_LENGTH = 5;

	@Test
	public void add_T_nullArg_shouldAddNullToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.add(null);

		assertThat("list rejected null", list.contains(null), is(true));
	}

	@Test
	public void add_T_nonNullArg_shouldAddElementToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.add(TEST_STRING);

		assertThat("list rejected supplied element", list.contains(TEST_STRING), is(true));
	}

	@Test
	public void add_T_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);

		list.add(null);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(list), eq(null), anyInt());
	}

	@Test
	public void add_T_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);

		list.add(TEST_STRING);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(eq(list), eq(TEST_STRING), anyInt());
	}

	@Test
	public void add_intT_nullArg_shouldAddNullToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.add(TEST_INDEX, null);

		assertThat("list rejected null", list.contains(null), is(true));
		assertThat("null was inserted at the wrong index", list.indexOf(null), is(TEST_INDEX));
	}

	@Test
	public void add_intT_nonNullArg_shouldAddElementToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.add(TEST_INDEX, TEST_STRING);

		assertThat("list rejected supplied element", list.contains(TEST_STRING), is(true));
		assertThat("supplied element was inserted at the wrong index", list.indexOf(TEST_STRING),
				is(TEST_INDEX));
	}

	@Test
	public void add_intT_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);

		list.add(TEST_INDEX, null);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(list, null, TEST_INDEX);
	}

	@Test
	public void add_intT_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);

		list.add(TEST_INDEX, TEST_STRING);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(list, TEST_STRING, TEST_INDEX);
	}

	@Test
	public void addAll_Collection_nullArg_shouldAddNullToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final Collection<String> newItems = getPopulatedList(null, TEST_LENGTH);

		list.addAll(newItems);

		assertThat("list rejected at least one null", Collections.frequency(list, null),
				is(TEST_LENGTH));
	}

	@Test
	public void addAll_Collection_nonNullArg_shouldAddElementToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final Collection<String> newItems = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.addAll(newItems);

		assertThat("list rejected at least one element", Collections.frequency(list, TEST_STRING),
				is(TEST_LENGTH));
	}

	@Test
	public void addAll_Collection_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		final Collection<String> newItems = getPopulatedList(null, TEST_LENGTH);

		list.addAll(newItems);

		verify(mockOnItemAddedListener, times(TEST_LENGTH))
				.onItemAdded(eq(list), eq(null), anyInt());
	}

	@Test
	public void addAll_Collection_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		final Collection<String> newItems = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.addAll(newItems);

		verify(mockOnItemAddedListener, times(TEST_LENGTH))
				.onItemAdded(eq(list), eq(TEST_STRING), anyInt());
	}

	@Test
	public void addAll_intCollection_nullArg_shouldAddNullToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final Collection<String> newItems = getPopulatedList(null, TEST_LENGTH);

		list.addAll(TEST_INDEX, newItems);

		assertThat("list rejected at least one null", Collections.frequency(list, null),
				is(TEST_LENGTH));
		assertThat("at least one null was inserted at the wrong index", list.indexOf(null),
				is(TEST_INDEX));
	}

	@Test
	public void addAll_intCollection_nonNullArg_shouldAddElementToList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final Collection<String> newItems = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.addAll(TEST_INDEX, newItems);

		assertThat("list rejected at least one element", Collections.frequency(list, TEST_STRING),
				is(TEST_LENGTH));
		assertThat("at least one null was inserted at the wrong index", list.indexOf(TEST_STRING),
				is(TEST_INDEX));
	}

	@Test
	public void addAll_intCollection_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		final Collection<String> newItems = getPopulatedList(null, TEST_LENGTH);

		list.addAll(TEST_INDEX, newItems);

		final Matcher<Integer> indexMatcher =
				is(both(greaterThanOrEqualTo(TEST_INDEX)).and(lessThan(TEST_INDEX + TEST_LENGTH)));
		verify(mockOnItemAddedListener, times(TEST_LENGTH))
				.onItemAdded(eq(list), eq(null), intThat(indexMatcher));
	}

	@Test
	public void addAll_intCollection_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		final Collection<String> newItems = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.addAll(TEST_INDEX, newItems);

		final Matcher<Integer> indexMatcher =
				is(both(greaterThanOrEqualTo(TEST_INDEX)).and(lessThan(TEST_INDEX + TEST_LENGTH)));
		verify(mockOnItemAddedListener, times(TEST_LENGTH))
				.onItemAdded(eq(list), eq(TEST_STRING), intThat(indexMatcher));
	}

	@Test
	public void remove_int_nullArg_shouldRemoveOneNullFromList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);

		list.remove(0);

		assertThat("null was not removed from the list", Collections.frequency(list, null),
				is(TEST_LENGTH - 1));
	}

	@Test
	public void remove_int_nonNullArg_shouldRemoveOneElementFromList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.remove(0);

		assertThat("no element was removed from the list", Collections.frequency(list,
						TEST_STRING),
				is(TEST_LENGTH - 1));
	}

	@Test
	public void remove_int_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.remove(0);

		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, null, 0);
	}

	@Test
	public void remove_int_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.remove(0);

		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, TEST_STRING, 0);
	}

	@Test
	public void remove_Object_nullArg_shouldRemoveOneNullFromList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);

		list.remove(null);

		assertThat("null was not removed from the list once", Collections.frequency(list, null),
				is(TEST_LENGTH - 1));
	}

	@Test
	public void remove_Object_nonNullArg_shouldRemoveOneElementFromList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.remove(TEST_STRING);

		assertThat("no element was removed from the list", Collections.frequency(list,
						TEST_STRING),
				is(TEST_LENGTH - 1));
	}

	@Test
	public void remove_Object_nullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.remove(null);

		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, null, 0);
	}

	@Test
	public void remove_Object_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.remove(TEST_STRING);

		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, TEST_STRING, 0);
	}

	@Test(expected = NullPointerException.class)
	public void removeAll_Collection_nullArg_shouldThrowException() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);

		list.removeAll(null); // Should throw exception
	}

	@Test
	public void removeAll_Collection_nonNullArg_shouldRemoveOneElementFromList() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);
		list.addAll(getPopulatedList(FILLER_STRING, FILLER_LENGTH));
		final ArrayList<String> stringsToRemove = new ArrayList<>();
		stringsToRemove.add(TEST_STRING);
		stringsToRemove.add(FILLER_STRING);

		list.removeAll(stringsToRemove);

		assertThat("elements were not removed from the list", list.size(), is(0));
	}

	@Test
	public void removeAll_Collection_nullArg_shouldNotInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(null, TEST_LENGTH);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		try {
			list.removeAll(null);
		} catch (NullPointerException e) {
			// Expected
		}

		verify(mockOnItemRemovedListener, never()).onItemRemoved(eq(list), eq(null), anyInt());
	}

	@Test
	public void removeAll_Collection_nonNullArg_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(TEST_STRING, TEST_LENGTH);
		list.addAll(getPopulatedList(FILLER_STRING, FILLER_LENGTH));
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);
		final ArrayList<String> stringsToRemove = new ArrayList<>();
		stringsToRemove.add(TEST_STRING);
		stringsToRemove.add(FILLER_STRING);

		list.removeAll(stringsToRemove);

		verify(mockOnItemRemovedListener, times(TEST_LENGTH))
				.onItemRemoved(eq(list), eq(TEST_STRING), anyInt());
		verify(mockOnItemRemovedListener, times(FILLER_LENGTH))
				.onItemRemoved(eq(list), eq(FILLER_STRING), anyInt());
	}

	@Test
	public void set_nullArg_shouldReplaceElement() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.set(TEST_INDEX, null);

		assertThat("the element was not set to null", list.get(TEST_INDEX), is(nullValue()));
		assertThat("set increased the length of the set", list.size(), is(FILLER_LENGTH));
	}

	@Test
	public void set_nonNullArg_shouldReplaceElement() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.set(TEST_INDEX, TEST_STRING);

		assertThat("the element was not set to null", list.get(TEST_INDEX), is(TEST_STRING));
		assertThat("set increased the length of the set", list.size(), is(FILLER_LENGTH));
	}

	@Test
	public void set_nullArg_shouldInvokeCallbacks() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.set(TEST_INDEX, null);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(list, null, TEST_INDEX);
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, FILLER_STRING, TEST_INDEX);
	}

	@Test
	public void set_nonNullArg_shouldInvokeCallbacks() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnItemAddedListener mockOnItemAddedListener = mock(OnItemAddedListener.class);
		final OnItemRemovedListener mockOnItemRemovedListener = mock(OnItemRemovedListener.class);
		list.addOnItemAddedListener(mockOnItemAddedListener);
		list.addOnItemRemovedListener(mockOnItemRemovedListener);

		list.set(TEST_INDEX, TEST_STRING);

		verify(mockOnItemAddedListener, times(1)).onItemAdded(list, TEST_STRING, TEST_INDEX);
		verify(mockOnItemRemovedListener, times(1)).onItemRemoved(list, FILLER_STRING, TEST_INDEX);
	}

	@Test
	public void clear_shouldInvokeCallback() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final OnListClearedListener mockOnListClearedListener = mock(OnListClearedListener.class);
		list.addOnListClearedListener(mockOnListClearedListener);

		list.clear();

		verify(mockOnListClearedListener, times(1)).onListCleared(list);
	}

	@Test(expected = NullPointerException.class)
	public void retainAll_nullArg_shouldThrowException() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.retainAll(null); // Should throw exception
	}

	@Test
	public void retainAll_retainContainedElements_shouldRetainAllElements() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final ArrayList<String> toRetain = getPopulatedList(FILLER_STRING, FILLER_LENGTH);

		list.retainAll(toRetain);

		assertThat("elements were removed which should have been retained",
				Collections.frequency(list, FILLER_STRING), is(FILLER_LENGTH));
	}

	@Test
	public void retainAll_retainNonContainedElements_shouldNotRetainAnyElements() {
		final ArrayListWithCallbacks<String> list = getPopulatedList(FILLER_STRING, FILLER_LENGTH);
		final ArrayList<String> toRetain = getPopulatedList(TEST_STRING, TEST_LENGTH);

		list.retainAll(toRetain);

		assertThat("elements were added which should not have been",
				Collections.frequency(list, TEST_STRING), is(0));
	}

	/**
	 * A utility method to create a new non-empty ArrayListWithCallbacks. The list is filled to
	 * the specified length with the specified element.
	 *
	 * @param element the element to fill the list with
	 * @param length  the number of elements to add to the list, greater than 0
	 * @return the filled ArrayList
	 * @throws IllegalArgumentException if length is not greater than 0
	 */
	private ArrayListWithCallbacks<String> getPopulatedList(String element, int length) {
		if (length < 0) {
			throw new IllegalArgumentException("length cannot be less than 1");
		}

		ArrayList<String> list = new ArrayList<>();

		for (int i = 0; i < length; i++) {
			list.add(element);
		}

		return new ArrayListWithCallbacks<String>(list);
	}
}
