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

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Utilities for hiding and showing the status bar.
 */
public abstract class StatusBarHelper {
	/**
	 * Flag for showing the status bar.
	 */
	private static final int UI_VISIBILITY_FLAG_SHOW = 0;

	/**
	 * Flag for hiding the status bar.
	 */
	private static final int UI_VISIBILITY_FLAG_HIDE =
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

	/**
	 * Hides the status bar and allows views to draw behind it. If the API level is 21 or higher,
	 * the status bar will become entirely transparent. If the API level is 19 or 20, the status bar
	 * will become tinted. Note that views must still specify {@code android:fitsSystemWindows="false"}
	 * to draw under the status bar.
	 *
	 * @param window
	 * 		the Window containing the status bar to hide
	 */
	public static void hideStatusBar(final Window window) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}

		window.getDecorView().setSystemUiVisibility(UI_VISIBILITY_FLAG_HIDE);
	}

	/**
	 * Shows the status bar and prevents view from drawing behind it. The color of the status bar
	 * can be set if the API level is greater than 20.
	 *
	 * @param window
	 * 		the Window containing the status bar to hide
	 * @param statusBarColor
	 * 		the color to make the status bar (has no effect if API level is less than 21)
	 */
	public static void showStatusBar(final Window window, int statusBarColor) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(statusBarColor);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}

		window.getDecorView().setSystemUiVisibility(UI_VISIBILITY_FLAG_SHOW);
	}

	/**
	 * Shows the status bar and prevents view from drawing behind it. The status bar will be black.
	 *
	 * @param window
	 * 		the Window containing the status bar to hide
	 */
	public static void showStatusBar(final Window window) {
		showStatusBar(window, Color.BLACK);
	}
}
