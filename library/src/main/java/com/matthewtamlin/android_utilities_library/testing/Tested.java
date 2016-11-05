package com.matthewtamlin.android_utilities_library.testing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifies classes which have been tested and specifies the testing method.
 */
@Retention(RetentionPolicy.SOURCE) // This class has no effect on execution, it just tracks testing
@Target(ElementType.TYPE)
public @interface Tested {
	/**
	 * @return a String describing the testing method (e.g. manual, automated)
	 */
	String testMethod();
}