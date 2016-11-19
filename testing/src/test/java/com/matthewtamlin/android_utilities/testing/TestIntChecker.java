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

package com.matthewtamlin.android_utilities.testing;

import com.matthewtamlin.android_utilities.library.checkers.IntChecker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link IntChecker} class.
 *
 * @deprecated class under test is deprecated
 */
@RunWith(JUnit4.class)
public class TestIntChecker {
	/**
	 * Failure message for use when the wrong integer is returned by the checker.
	 */
	private static final String FAILURE_MESSAGE = "Incorrect value returned";

	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions correctly
	 * when the {@code num} argument is less than the {@code lessThan} argument. The test will only
	 * pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckLessThan_lessThanPassed() {
		final int result = IntChecker.checkLessThan(5, 6);
		assertThat(FAILURE_MESSAGE, result == 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions correctly
	 * when the {@code num} argument is equal to the {@code lessThan} argument. The test will only
	 * pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckLessThan_equalToPassed() {
		IntChecker.checkLessThan(5, 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkLessThan(int, int)} method functions correctly
	 * when the {@code num} argument is greater than the {@code lessThan} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckLessThan_greaterThanPassed() {
		IntChecker.checkLessThan(6, 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code num} argument is less than the {@code greaterThan} argument. The
	 * test will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckGreaterThan_lessThanPassed() {
		IntChecker.checkGreaterThan(5, 6);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code num} argument is equal to the {@code greaterThan} argument. The
	 * test will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckGreaterThan_equalToPassed() {
		IntChecker.checkGreaterThan(5, 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkGreaterThan(int, int)} method functions
	 * correctly when the {@code num} argument is greater than the {@code greaterThan} argument. The
	 * test will only pass if the correct integer is returned by the check and no exception is
	 * thrown.
	 */
	@Test
	public void testCheckGreaterThan_greaterThanPassed() {
		final int result = IntChecker.checkGreaterThan(6, 5);
		assertThat(FAILURE_MESSAGE, result == 6);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int)} method functions correctly
	 * when the {@code num} argument is less than the {@code equalTo} argument. The test will only
	 * pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEqualTo_lessThanPassed() {
		IntChecker.checkEqualTo(5, 6);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int, String)} method functions
	 * correctly when the {@code num} argument is equal to the {@code equalTo} argument. The test
	 * will only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckEqualTo_equalToPassed() {
		final int result = IntChecker.checkEqualTo(5, 5);
		assertThat(FAILURE_MESSAGE, result == 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkEqualTo(int, int)} method functions correctly
	 * when the {@code num} argument is greater than the {@code equalTo} argument. The test will
	 * only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEqualTo_greaterThanPassed() {
		IntChecker.checkEqualTo(6, 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code num} argument is less than the {@code notEqualTo} argument. The
	 * test will only pass if the correct integer is returned by the check and no exception is
	 * thrown.
	 */
	@Test
	public void testCheckNotEqualTo_lessThanPassed() {
		final int result = IntChecker.checkNotEqualTo(5, 6);
		assertThat(FAILURE_MESSAGE, result == 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code num} argument is equal to the {@code notEqualTo} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckNotEqualTo_equalToPassed() {
		IntChecker.checkNotEqualTo(5, 5);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkNotEqualTo(int, int)} method functions
	 * correctly when the {@code num} argument is greater than the {@code notEqualTo} argument. The
	 * test will only pass if the correct integer is returned by the check and no exception is
	 * thrown.
	 */
	@Test
	public void testCheckNotEqualTo_greaterThanPassed() {
		final int result = IntChecker.checkNotEqualTo(6, 5);
		assertThat(FAILURE_MESSAGE, result == 6);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code num} argument is less than the {@code lower} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckBetween_lessThanLowerPassed() {
		IntChecker.checkBetween(5, 6, 8);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code num} argument is equal to the {@code lower} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckBetween_equalToLowerPassed() {
		final int result = IntChecker.checkBetween(6, 6, 8);
		assertThat(FAILURE_MESSAGE, result == 6);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code num} argument is between the {@code lower} argument and the {@code
	 * upper} argument. The test will only pass if the correct integer is returned by the check and
	 * no exception is thrown.
	 */
	@Test
	public void testCheckBetween_betweenPassed() {
		final int result = IntChecker.checkBetween(7, 6, 8);
		assertThat(FAILURE_MESSAGE, result == 7);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code num} argument is equal to the {@code upper} argument. The test will
	 * only pass if the correct integer is returned by the check and no exception is thrown.
	 */
	@Test
	public void testCheckBetween_equalToUpperPassed() {
		final int result = IntChecker.checkBetween(8, 6, 8);
		assertThat(FAILURE_MESSAGE, result == 8);
	}

	/**
	 * Test to verify that the {@link IntChecker#checkBetween(int, int, int)} method functions
	 * correctly when the {@code num} argument is greater than the {@code upper} argument. The test
	 * will only pass if an IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCheckBetween_greaterThanUpperPassed() {
		IntChecker.checkBetween(9, 6, 8);
	}
}
