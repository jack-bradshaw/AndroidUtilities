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

import android.os.Handler;
import android.os.Looper;

import com.matthewtamlin.android_utilities.library.checkers.NullChecker;

import static com.matthewtamlin.android_utilities.library.checkers.NullChecker.checkNonNull;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Provides access to the UI thread using a Looper.
 */
public class LooperUiThreadUtil implements UiThreadUtil {
	private final Looper looper;

	/**
	 * Constructs a new LooperUiThreadUtil which uses the supplied looper.
	 *
	 * @param looper
	 * 		the looper to use, not null
	 * @return the new LooperUiThreadUtil, not null
	 * @throws IllegalArgumentException
	 * 		if {@code looper} is null
	 */
	public static LooperUiThreadUtil createUsingLooper(final Looper looper) {
		return new LooperUiThreadUtil(looper);
	}

	/**
	 * Constructs a new LooperUiThreadUtil which uses the main looper of the calling application.
	 *
	 * @return the new LooperUiThreadUtil, not null
	 */
	public static LooperUiThreadUtil createUsingMainLooper() {
		return new LooperUiThreadUtil(Looper.getMainLooper());
	}

	/**
	 * Constructs a new LooperUiThreadUtil.
	 *
	 * @param looper
	 * 		the looper to use, not null
	 */
	private LooperUiThreadUtil(final Looper looper) {
		this.looper = checkNotNull(looper, "looper cannot be null");
	}

	@Override
	public void runOnUiThread(final Runnable runnable) {
		if (runnable != null) {
			final Handler handler = new Handler(looper);
			handler.post(runnable);
		}
	}
}