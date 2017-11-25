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

package com.matthewtamlin.android_utilities.library.utilities;

/**
 * Provides access to the UI thread.
 *
 * @deprecated consider migrating to an RxJava based architecture instead of using this class
 */
@Deprecated
public interface UiThreadUtil {
	/**
	 * Submits the supplied runnable to the UI thread.
	 *
	 * @param runnable
	 * 		the runnable to submit, cannot be null
	 */
	public void runOnUiThread(Runnable runnable);

	/**
	 * Submits the supplied runnable to the UI thread with an initial delay.
	 *
	 * @param runnable
	 * 		the runnable to submit, cannot be null
	 * @param delayMilliseconds
	 * 		the delay in milliseconds
	 */
	public void runOnUiThreadWithDelay(Runnable runnable, long delayMilliseconds);
}
