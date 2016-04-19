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

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Static utility class for querying the screen size.
 */
public abstract class ScreenSizeHelper {
	/**
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return the size of the device screen
	 */
	public static ScreenSize getScreenSize(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		if (config == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
			return ScreenSize.XLARGE;
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
	 * Determines if the device configuration specifies the screen size as undefined, as specified
	 * in {@link Configuration}.
	 *
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return true if the screen size is undefined, false otherwise
	 */
	public static boolean screenIsUndefined(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		return config == Configuration.SCREENLAYOUT_SIZE_UNDEFINED;
	}

	/**
	 * Determines if the device configuration specifies the screen size as extra-large, as
	 * specified in {@link Configuration}.
	 *
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return true if the screen size is extra-large, false otherwise
	 */
	public static boolean screenIsXLarge(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		return config == Configuration.SCREENLAYOUT_SIZE_XLARGE;
	}

	/**
	 * Determines if the device configuration specifies the screen size as large, as specified in
	 * {@link Configuration}.
	 *
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return true if the screen size is large, false otherwise
	 */
	public static boolean screenIsLarge(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		return config == Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * Determines if the device configuration specifies the screen size as normal, as specified in
	 * {@link Configuration}.
	 *
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return true if the screen size is normal, false otherwise
	 */
	public static boolean screenIsNormal(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		return config == Configuration.SCREENLAYOUT_SIZE_NORMAL;
	}

	/**
	 * Determines if the device configuration specifies the screen size as small, as specified in
	 * {@link Configuration}.
	 *
	 * @param res
	 * 		a {@code Resource} object which allows the device configuration to be queried
	 * @return true if the screen size is small, false otherwise
	 */
	public static boolean screenIsSmall(final Resources res) {
		int config = res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
		return config == Configuration.SCREENLAYOUT_SIZE_SMALL;
	}

	/**
	 * Enumerates the possible screen sizes, as specified in {@link Configuration}.
	 */
	public enum ScreenSize {
		SMALL, NORMAL, LARGE, XLARGE, UNDEFINED;
	}

	/**
	 * Returns the absolute width of this device's screen, measured in pixels.
	 *
	 * @param windowManager
	 * 		the window manager for the calling application
	 * @return the width of the screen, measured in pixels
	 */
	public static int getScreenWidth(final WindowManager windowManager) {
		DisplayMetrics metrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

	/**
	 * Returns the absolute height of this device's screen, measured in pixels.
	 *
	 * @param windowManager
	 * 		the window manager for the calling application
	 * @return the height of the screen in pixels
	 */
	public static int getScreenHeight(final WindowManager windowManager) {
		DisplayMetrics metrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}
}