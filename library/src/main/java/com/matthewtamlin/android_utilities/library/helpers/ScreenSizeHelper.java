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

/**
 * Helper class for working with screen sizes.
 */
public class ScreenSizeHelper {
	/**
	 * Returns the screen size as an enum constant.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return the size of the device screen according to the current configuration
	 */
	public static ScreenSize getScreenSize(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		// Getting the config requires a bitwise AND operation (Wow! Such OOP! Many good design!)
		final Resources res = context.getResources();
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		// Convert the configuration to an enum constant
		if (config == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
			return ScreenSize.EXTRA_LARGE;
		} else if (config == Configuration.SCREENLAYOUT_SIZE_LARGE) {
			return ScreenSize.LARGE;
		} else if (config == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
			return ScreenSize.NORMAL;
		} else if (config == Configuration.SCREENLAYOUT_SIZE_SMALL) {
			return ScreenSize.SMALL;
		} else {
			return ScreenSize.UNDEFINED;
		}
	}

	/**
	 * Determines if the screen size is undefined.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return true if the screen size is undefined, false otherwise
	 */
	public static boolean screenSizeIsUndefined(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getScreenSize(context) == ScreenSize.UNDEFINED;
	}

	/**
	 * Determines if the screen size is extra-large.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return true if the screen size is extra-large, false otherwise
	 */
	public static boolean screenSizeIsExtraLarge(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getScreenSize(context) == ScreenSize.EXTRA_LARGE;
	}

	/**
	 * Determines if the screen size is large.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return true if the screen size is large, false otherwise
	 */
	public static boolean screenSizeIsLarge(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getScreenSize(context) == ScreenSize.LARGE;
	}

	/**
	 * Determines if the screen size is normal.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return true if the screen size is normal, false otherwise
	 */
	public static boolean screenSizeIsNormal(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getScreenSize(context) == ScreenSize.NORMAL;
	}

	/**
	 * Determines if the screen size is small.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return true if the screen size is small, false otherwise
	 */
	public static boolean screenSizeIsSmall(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getScreenSize(context) == ScreenSize.SMALL;
	}

	/**
	 * Returns the approximate width of this device's screen, measured in pixels.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return the width of the screen, measured in pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getScreenWidthPx(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		// Pass a DisplayMetrics object into a WindowManager to receive display information
		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		// Query the DisplayMetrics object to obtain the approximate screen width
		return metrics.widthPixels;
	}

	/**
	 * Returns the approximate height of this device's screen, measured in pixels.
	 *
	 * @param context
	 * 		a Context object which gives access to the current device configuration, not null
	 * @return the height of the screen, measured in pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getScreenHeightPx(final Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		// Pass a DisplayMetrics object into a WindowManager to receive display information
		final DisplayMetrics metrics = new DisplayMetrics();
		final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);

		// Query the DisplayMetrics object to obtain the approximate screen height
		return metrics.heightPixels;
	}

	/**
	 * Enumerates the possible screen sizes, as specified in {@link Configuration}.
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
		 * The screen width is approximately 720dp and the screen height is approximately 960dp.=
		 */
		EXTRA_LARGE,

		/**
		 * The screen size is undefined.
		 */
		UNDEFINED
	}
}