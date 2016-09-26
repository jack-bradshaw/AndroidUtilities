package com.matthewtamlin.sliding_intro_screen_unit_testing;

import com.matthewtamlin.android_utilities_library.checkers.NullChecker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link NullChecker} class.
 */
@RunWith(JUnit4.class)
public class TestNullChecker {
	/**
	 * A string for use in testing.
	 */
	private static final String TEST_STRING = "test";

	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object)} method functions correctly
	 * when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNull_1_nullPassed() {
		NullChecker.checkNonNull(null);
	}

	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object)} method functions correctly
	 * when a non-null String is passed for the {@code object} argument. The test will only pass if
	 * the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNull_1_nonNullPassed() {
		final String result = NullChecker.checkNonNull(TEST_STRING);
		assertThat("incorrect object returned", result == TEST_STRING);
	}

	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object, String)} method functions
	 * correctly when null is passed for the {@code object} argument. The test will only pass if an
	 * IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNull_2_nullPassed() {
		NullChecker.checkNonNull(null, "error message");
	}

	/**
	 * Test to verify that the {@link NullChecker#checkNonNull(Object, String)} method functions
	 * correctly when a non-null String is passed for the {@code object} argument. The test will
	 * only pass if the same String instance is returned and no exception is thrown.
	 */
	@SuppressWarnings("StringEquality") // Reference equality is needed for test to pass
	@Test
	public void testCheckNull_2_nonNullPassed() {
		final String result = NullChecker.checkNonNull(TEST_STRING, "error message");
		assertThat("incorrect object returned", result == TEST_STRING);
	}
}