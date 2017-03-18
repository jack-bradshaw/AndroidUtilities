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

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Utilities for quantifying the device screen size.
 */
public class ScreenSizeHelper {
	/**
	 * Gets the approximate screen size.
	 *
	 * @param context
	 * 		a Context which gives access to the current device configuration, not null
	 * @return the size of the device screen according to the current configuration
	 */
	public static ScreenSize getScreenSize(final Context context) {
		checkNotNull(context, "context cannot be null");

		final Resources res = context.getResources();
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (config) {
			case Configuration.SCREENLAYOUT_SIZE_XLARGE: return ScreenSize.EXTRA_LARGE;
			case Configuration.SCREENLAYOUT_SIZE_LARGE: return ScreenSize.LARGE;
			case Configuration.SCREENLAYOUT_SIZE_NORMAL: return ScreenSize.NORMAL;
			case Configuration.SCREENLAYOUT_SIZE_SMALL: return ScreenSize.SMALL;

			default: return ScreenSize.UNDEFINED;
		}
	}

	/**
	 * Gets the screen width of this device.
	 *
	 * @param context
	 * 		a Context which gives access to the current device configuration, not null
	 *
	 * @return the width of the screen, measured in pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getScreenWidthPx(final Context context) {
		checkNotNull(context, "context cannot be null");

		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		return metrics.widthPixels;
	}

	/**
	 * Gets the screen height of this device.
	 *
	 * @param context
	 * 		a Context which gives access to the current device configuration, not null
	 *
	 * @return the height of the screen, measured in pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getScreenHeightPx(final Context context) {
		checkNotNull(context, "context cannot be null");

		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		return metrics.heightPixels;
	}

	/**
	 * The possible screen sizes according to {@link Configuration}.
	 */
	public enum ScreenSize {
		/**
		 * The screen width is approximately 320dp and the screen height is approximately 426dp.
		 */
		SMALL,

		/**
		 * The screen width is approximately 320dp and the screen height is approximately 470dp.
		 */
		NORMAL,

		/**
		 * The screen width is approximately 480dp and the screen height is approximately 640dp.
		 */
		LARGE,

		/**
		 * The screen width is approximately 720dp and the screen height is approximately 960dp.
		 */
		EXTRA_LARGE,

		/**
		 * The screen size is undefined.
		 */
		UNDEFINED
	}
}