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
 * Helper class for working with colors.
 */
public abstract class ColorHelper {
	/**
	 * Blends two colours to produce a single output colour. No check is done to ensure the provided
	 * colors are valid ARGB hex codes, and providing invalid codes will result in an undefined
	 * result.
	 *
	 * @param color1
	 * 		the first colour to blend, as an ARGB hex code
	 * @param color2
	 * 		the second colour to blend, as an ARGB hex code
	 * @param ratio
	 * 		the proportion of {@code color1} to use in the blended result, between 0 and 1 (inclusive)
	 * @return the ARGB code for the blended colour
	 * @throws IllegalArgumentException
	 * 		if {@code ratio} is not between 0 and 1 (inclusive)
	 */
	public static int blendColors(final int color1, final int color2, final float ratio) {
		if (ratio < 0 || ratio > 1) {
			throw new IllegalArgumentException("ratio must be between 0 and 1 (inclusive)");
		}

		// Calculate the inverse ratio once and cache the result to improve time performance
		final float inverseRatio = 1f - ratio;

		// Combine the colors using the ARGB components
		final float a = (Color.alpha(color1) * ratio) + (Color.alpha(color2) * inverseRatio);
		final float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRatio);
		final float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRatio);
		final float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRatio);

		// Compose the result from the combined ARGB components
		return Color.argb((int) a, (int) r, (int) g, (int) b);
	}

	/**
	 * Extracts the primary color from the current theme of a Context.
	 *
	 * @param context
	 * 		the Context containing the theme to query, not null
	 * @param defaultColor
	 * 		the color to return if no primary color is found, as an ARGB hex code
	 * @return the primary color, or the default color if none is found
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getPrimaryColor(final Context context, final int defaultColor) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getColor(context, defaultColor, R.attr.colorPrimary);
	}

	/**
	 * Extracts the primary dark color from the current theme of a Context.
	 *
	 * @param context
	 * 		the Context containing the theme to query, not null
	 * @param defaultColor
	 * 		the color to return if no primary dark color is found, as an ARGB hex code
	 * @return the primary dark color, or the default color if none is found
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getPrimaryDarkColor(final Context context, final int defaultColor) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getColor(context, defaultColor, R.attr.colorPrimaryDark);
	}

	/**
	 * Extracts the accent color from the current theme of a Context.
	 *
	 * @param context
	 * 		the Context containing the theme to query, not null
	 * @param defaultColor
	 * 		the color to return if no accent color is found, as an ARGB hex code
	 * @return the accent color, or the default color if none is found
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	public static int getAccentColor(final Context context, final int defaultColor) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		return getColor(context, defaultColor, R.attr.colorAccent);
	}

	/**
	 * Extracts a color from the current theme of a Context.
	 *
	 * @param context
	 * 		the Context containing the theme to query, not null
	 * @param defaultColor
	 * 		the color to return if no color is found, as an ARGB hex code
	 * @param colorAttr
	 * 		an attribute in the current theme which specifies the color to return
	 * @return the specified color, or the default color if none is found
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 */
	private static int getColor(final Context context, final int defaultColor, final int
			colorAttr) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		}

		final TypedValue v = new TypedValue();
		final TypedArray a = context.obtainStyledAttributes(v.data, new int[]{colorAttr});
		final int color = a.getColor(0, defaultColor);

		a.recycle();
		return color;
	}
}