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

package com.matthewtamlin.android_utilities.library.helpers;

import com.matthewtamlin.android_utilities.library.testing.Tested;

/**
 * Helper class for working with null.
 */
@Tested(testMethod = "automated", requiresInstrumentation = false)
public class NullHelper {
	/**
	 * Determines whether or not two objects are equal, where one or both could be null. The result
	 * will be true if both objects are null, or if obj1 is not null and obj1.equals(obj2) is true.
	 * In all other cases, the result is false.
	 *
	 * @param obj1
	 * 		an object
	 * @param obj2
	 * 		an object
	 * @return true if the supplied objects are equal
	 */
	public static boolean nullSafeEquals(Object obj1, Object obj2) {
		if (obj1 == null) {
			return obj2 == null;
		} else {
			return obj1.equals(obj2);
		}
	}
}
