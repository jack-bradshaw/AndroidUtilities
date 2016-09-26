package com.matthewtamlin.android_utilities_library.checkers;

/**
 * Utility for implementing null guards without boilerplate code.
 */
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
