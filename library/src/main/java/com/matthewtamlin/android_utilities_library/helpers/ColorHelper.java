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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.TypedValue;

import com.matthewtamlin.android_utilities_library.R;

/**
 * Utilities for working with colors.
 */
public abstract class ColorHelper {
	/**
	 * Blends two colours to produce a single output colour. No check is done to ensure the provided
	 * colors are valid ARGB hex codes. Providing invalid codes will result in an undefined result.
	 *
	 * @param color1
	 * 		ARGB hex code for the first colour to blend
	 * @param color2
	 * 		ARGB hex code for the second colour to blend
	 * @param ratio
	 * 		the proportion of {@code color1} to use in the blended result, between 0 and 1 (inclusive)
	 * @return the ARGB code for the blended colour
	 * @throws IllegalArgumentException
	 * 		if ratio is not between 0 and 1 (inclusive)
	 */
	public static int blendColors(final int color1, final int color2, final float ratio) {
		if (ratio < 0 || ratio > 1) {
			throw new IllegalArgumentException("ratio must be between 0 and 1 (inclusive)");
		}

		final float a = (Color.alpha(color1) * ratio) + (Color.alpha(color2) * (1f - ratio));
		final float r = (Color.red(color1) * ratio) + (Color.red(color2) * (1f - ratio));
		final float g = (Color.green(color1) * ratio) + (Color.green(color2) * (1f - ratio));
		final float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * (1f - ratio));

		return Color.argb((int) a, (int) r, (int) g, (int) b);
	}

	/**
	 * Extracts the primary color from a theme.
	 *
	 * @param context
	 * 		the context to get the theme of
	 * @param defaultColor
	 * 		the color to return if no primary color is found, as an ARGB hex code
	 * @return the primary color or the default if not found
	 */
	public static int getPrimaryColor(Context context, int defaultColor) {
		return getColor(context, defaultColor, R.attr.colorPrimary);
	}

	/**
	 * Extracts the primary dark color from a theme.
	 *
	 * @param context
	 * 		the context to get the theme of
	 * @param defaultColor
	 * 		the color to return if no primary dark color is found, as an ARGB hex code
	 * @return the primary dark color or the default if not found
	 */
	public static int getPrimaryDarkColor(Context context, int defaultColor) {
		return getColor(context, defaultColor, R.attr.colorPrimaryDark);
	}

	/**
	 * Extracts the accent color from a theme.
	 *
	 * @param context
	 * 		the context to get the theme of
	 * @param defaultColor
	 * 		the color to return if no accent color is found, as an ARGB hex code
	 * @return the accent color or the default if not found
	 */
	public static int getAccentColor(Context context, int defaultColor) {
		return getColor(context, defaultColor, R.attr.colorAccent);
	}

	private static int getColor(Context context, int defaultColor, int colorAttr) {
		final TypedValue v = new TypedValue();
		final TypedArray a = context.obtainStyledAttributes(v.data, new int[]{colorAttr});
		final int color = a.getColor(0, defaultColor);
		a.recycle();
		return color;
	}
}