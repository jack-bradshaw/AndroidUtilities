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

package com.matthewtamlin.android_utilities.library.checkers;

import com.matthewtamlin.android_utilities.library.testing.Tested;

/**
 * Utility for checking integer arguments without boilerplate code.
 */
@SuppressWarnings("SameParameterValue") // Not important as class is part of public API
@Tested(testMethod = "automated", requiresInstrumentation = false)
public class IntChecker {
	/**
	 * The exception message to use if no message is provided.
	 */
	private static final String DEFAULT_MESSAGE = "integer check failed";

	/**
	 * Checks that one integer is less than another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param lessThan
	 * 		the number {@code num} must be less than
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not less than {@code lessThan}
	 */
	public static int checkLessThan(final int num, final int lessThan) {
		return checkLessThan(num, lessThan, DEFAULT_MESSAGE);
	}

	/**
	 * Checks that one integer is less than another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param lessThan
	 * 		the number {@code num} must be less than
	 * @param message
	 * 		the message to add to the exception if thrown
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not less than {@code lessThan}
	 */
	public static int checkLessThan(final int num, final int lessThan, final String message) {
		if (num >= lessThan) {
			throw new IllegalArgumentException(message);
		} else {
			return num;
		}
	}

	/**
	 * Checks that one integer is greater than another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param greaterThan
	 * 		the number {@code num} must be greater than
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not greater than {@code greaterThan}
	 */
	public static int checkGreaterThan(final int num, final int greaterThan) {
		return checkGreaterThan(num, greaterThan, DEFAULT_MESSAGE);
	}

	/**
	 * Checks that one integer is greater than another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param greaterThan
	 * 		the number {@code num} must be greater than
	 * @param message
	 * 		the message to add to the exception if thrown
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not greater than {@code greaterThan}
	 */
	public static int checkGreaterThan(final int num, final int greaterThan, final String message) {
		if (num <= greaterThan) {
			throw new IllegalArgumentException(message);
		} else {
			return num;
		}
	}

	/**
	 * Checks that one integer is equal to another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param equalTo
	 * 		the number {@code num} must be equal to
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not equal to {@code equalTo}
	 */
	public static int checkEqualTo(final int num, final int equalTo) {
		return checkEqualTo(num, equalTo, DEFAULT_MESSAGE);
	}

	/**
	 * Checks that one integer is equal to another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param equalTo
	 * 		the number {@code num} must be equal to
	 * @param message
	 * 		the message to add to the exception if thrown
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not equal to {@code equalTo}
	 */
	public static int checkEqualTo(final int num, final int equalTo, final String message) {
		if (num != equalTo) {
			throw new IllegalArgumentException(message);
		} else {
			return num;
		}
	}

	/**
	 * Checks that one integer is not equal to another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param notEqualTo
	 * 		the number {@code num} must be not equal to
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is equal to {@code notEqualTo}
	 */
	public static int checkNotEqualTo(final int num, final int notEqualTo) {
		return checkNotEqualTo(num, notEqualTo, DEFAULT_MESSAGE);
	}

	/**
	 * Checks that one integer is not equal to another. If the check passes then the integer being
	 * checked is returned, otherwise an exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param notEqualTo
	 * 		the number {@code num} must be not equal to
	 * @param message
	 * 		the message to add to the exception if thrown
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is equal to {@code notEqualTo}
	 */
	public static int checkNotEqualTo(final int num, final int notEqualTo, final String message) {
		if (num == notEqualTo) {
			throw new IllegalArgumentException(message);
		} else {
			return num;
		}
	}

	/**
	 * Checks that one integer is in the interval between two other integers (inclusive of the
	 * bounds). If the check passes then the integer being checked is returned, otherwise an
	 * exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param lower
	 * 		the lower bound of the interval
	 * @param upper
	 * 		the upper bound of the interval
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not in the interval between {@code lower} and {@code upper}
	 */
	public static int checkBetween(final int num, final int lower, final int upper) {
		return checkBetween(num, lower, upper, DEFAULT_MESSAGE);
	}

	/**
	 * Checks that one integer is in the interval between two other integers (inclusive of the
	 * bounds). If the check passes then the integer being checked is returned, otherwise an
	 * exception is thrown.
	 *
	 * @param num
	 * 		the number to check
	 * @param lower
	 * 		the lower bound of the interval
	 * @param upper
	 * 		the upper bound of the interval
	 * @param message
	 * 		the message to add to the exception if thrown
	 * @return num
	 * @throws IllegalArgumentException
	 * 		if {@code num} is not in the interval between {@code lower} and {@code upper}
	 */
	public static int checkBetween(final int num, final int lower, final int upper, final String
			message) {
		if (num < lower || num > upper) {
			throw new IllegalArgumentException(message);
		} else {
			return num;
		}
	}
}