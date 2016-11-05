package com.matthewtamlin.android_utilities_library.helpers;

/**
 * Helper class for working with null.
 */
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
