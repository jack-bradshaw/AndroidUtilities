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

package com.matthewtamlin.android_utilities.library.testing;

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

	/**
	 * @return true if the tests cannot be run without instrumentation
	 */
	boolean requiresInstrumentation();
}