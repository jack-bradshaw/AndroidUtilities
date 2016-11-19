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
 * Utility for implementing null guards without boilerplate code.
 *
 * @deprecated functionality has been moved to a separate library: com .matthew-tamlin:java-utilities:1.1.0
 */
@Tested(testMethod = "automated", requiresInstrumentation = false)
public class NullChecker {
	/**
	 * Checks if the supplied object is null. If the object is null, an IllegalArgumentException is
	 * thrown.
	 *
	 * @param object
	 * 		the object to check
	 * @param <T>
	 * 		the type of object being checked
	 * @return the object
	 */
	public static <T> T checkNonNull(T object) {
		return checkNonNull(object, null);
	}

	/**
	 * Checks if the supplied object is null. If the object is null, an IllegalArgumentException is
	 * thrown.
	 *
	 * @param object
	 * 		the object to check
	 * @param exceptionMessage
	 * 		the message to assign to the exception if thrown, null to use no message
	 * @param <T>
	 * 		the type of object being checked
	 * @return the object
	 */
	public static <T> T checkNonNull(final T object, final String exceptionMessage) {
		if (object != null) {
			return object;
		} else {
			throw new IllegalArgumentException(exceptionMessage == null ? "" : exceptionMessage);
		}
	}
}
