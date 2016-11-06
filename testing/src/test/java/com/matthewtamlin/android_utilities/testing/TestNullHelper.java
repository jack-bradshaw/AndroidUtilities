package com.matthewtamlin.android_utilities.testing;


import com.matthewtamlin.android_utilities.library.helpers.NullHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.matthewtamlin.android_utilities.library.helpers.NullHelper.nullSafeEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for the {@link NullHelper} class.
 */
@RunWith(JUnit4.class)
public class TestNullHelper {
	@Test
	public void testNullSafeEquals_bothInputsAreNull() {
		final String str1 = null;
		final String str2 = null;

		assertThat("str1 and str2 should be equal", nullSafeEquals(str1, str2), is(true));
	}

	@Test
	public void testNullSafeEquals_argumentOneIsNull() {
		final String str1 = "Skye";
		final String str2 = null;

		assertThat("str1 and str2 should not be equal", nullSafeEquals(str1, str2), is(false));
	}

	@Test
	public void testNullSafeEquals_argumentTwoIsNull() {
		final String str1 = null;
		final String str2 = "Daisy";

		assertThat("str1 and str2 should not be equal", nullSafeEquals(str1, str2), is(false));
	}

	@Test
	public void testNullSafeEquals_argumentsAreNonNullAndTypicallyEqual() {
		final String str1 = "Quake";
		final String str2 = "Quake";

		assertThat("str1 and str2 should be equal", nullSafeEquals(str1, str2), is(true));
	}

	@Test
	public void testNullSafeEquals_argumentsAreNonNullAndTypicallyNotEqual() {
		final String str1 = "Quake";
		final String str2 = "Daisy";

		assertThat("str1 and str2 should not be equal", nullSafeEquals(str1, str2), is(false));
	}
}
