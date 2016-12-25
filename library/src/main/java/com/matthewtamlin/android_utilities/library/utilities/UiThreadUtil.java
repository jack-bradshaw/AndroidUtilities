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
 */
@SuppressWarnings("WeakerAccess") // Class is part of public API
public interface UiThreadUtil {
	/**
	 * Runs the supplied runnable on the UI thread of the current app.
	 *
	 * @param runnable
	 * 		the runnable to execute, may be null
	 */
	public void runOnUiThread(Runnable runnable);
}
