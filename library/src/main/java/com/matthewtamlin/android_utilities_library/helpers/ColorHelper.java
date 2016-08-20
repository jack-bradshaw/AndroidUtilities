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

/**
 * Helper class for working with colors.
 */
public class ColorHelper {
	/**
	 * Blends two colours to produce a single output colour. No check is done to ensure the provided
	 * colors are valid ARGB hex codes, and providing invalid codes will result in an undefined
	 * result. The blend works individually combining the ARGB components of the supplied colors and
	 * then synthesising the components back into one color.
	 *
	 * @param color1
	 * 		the first colour to blend, as an ARGB hex code
	 * @param color2
	 * 		the second colour to blend, as an ARGB hex code
	 * @param ratio
	 * 		the proportion of {@code color2} to use in the blended result, between 0 and 1
	 * 		(inclusive)
	 * @return the ARGB code for the blended colour
	 * @throws IllegalArgumentException
	 * 		if {@code ratio} is not between 0 and 1 (inclusive)
	 */
	@SuppressWarnings("SameParameterValue")
	public static int blendColors(final int color1, final int color2, final float ratio) {
		if (ratio < 0 || ratio > 1) {
			throw new IllegalArgumentException("ratio must be between 0 and 1 (inclusive)");
		}

		// Calculate the inverse ratio once and cache the result to improve time performance
		final float inverseRatio = 1f - ratio;

		// Combine the colors using the ARGB components
		final float a = (Color.alpha(color1) * inverseRatio) + (Color.alpha(color2) * ratio);
		final float r = (Color.red(color1) * inverseRatio) + (Color.red(color2) * ratio);
		final float g = (Color.green(color1) * inverseRatio) + (Color.green(color2) * ratio);
		final float b = (Color.blue(color1) * inverseRatio) + (Color.blue(color2) * ratio);

		// Compose the result from by combining the ARGB components
		return Color.argb((int) a, (int) r, (int) g, (int) b);
	}
}