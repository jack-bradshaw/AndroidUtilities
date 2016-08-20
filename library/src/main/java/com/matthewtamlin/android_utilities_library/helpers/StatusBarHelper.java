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

package com.matthewtamlin.android_utilities_library.helpers;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;

/**
 * Utilities for hiding and showing the status bar.
 */
@RequiresApi(16) // For client
@TargetApi(16) // For lint
public class StatusBarHelper {
	/**
	 * Passing this value to {@link android.support.v4.view.ViewPager.DecorView#setSystemUiVisibility(int)}
	 * will show the status bar.
	 */
	private static final int SHOW_STATUS_BAR_FLAG = 0;

	/**
	 * Hides the status bar of the supplied Window.
	 *
	 * @param window
	 * 		the Window containing the status bar to hide
	 */
	@RequiresApi(16) // For client
	@TargetApi(16) // For lint
	public static void hideStatusBar(final Window window) {
		if (window == null) {
			throw new IllegalArgumentException("window cannot be null");
		}

		final View decorView = window.getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
	}

	/**
	 * Shows the status bar of the supplied Window.
	 *
	 * @param window
	 * 		the Window containing the status bar to hide, not null
	 */
	@RequiresApi(16) // For client
	@TargetApi(16) // For lint
	public static void showStatusBar(final Window window) {
		if (window == null) {
			throw new IllegalArgumentException("window cannot be null");
		}

		window.getDecorView().setSystemUiVisibility(SHOW_STATUS_BAR_FLAG);
	}
}