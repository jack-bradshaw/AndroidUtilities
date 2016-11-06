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

import android.graphics.Color;

import com.matthewtamlin.android_utilities.library.testing.Tested;

/**
 * Helper class for working with colors.
 */
@Tested(testMethod = "automated", requiresInstrumentation = true)
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
	 * 		the proportion of {@code color2} to use in the blended result, between 0 and 1 (inclusive)
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

	/**
	 * Determines whether text should be black or white, depending on whichever maximises contrast
	 * with the background color.
	 *
	 * @param backgroundColor
	 * 		the color of the background behind the text, as an ARGB hex code
	 * @return white (0xFFFFFF) or black (0x000000)
	 */
	public static int calculateBestTextColor(final int backgroundColor) {
		// sRGB [r, g, b]
		final float[] preConversionValues = {((float) Color.red(backgroundColor)) / 255,
				((float) Color.green(backgroundColor)) / 255,
				((float) Color.blue(backgroundColor)) / 255};

		// linear RGB [r', g', b']
		final float[] postConversionValues = new float[3];

		// sRGB to RGB according to https://goo.gl/vIj7TC
		for (int i = 0; i < 3; i++) {
			final float x = preConversionValues[i];

			if (x <= 0.04045) {
				postConversionValues[i] = (float) (x / 12.92);
			} else {
				postConversionValues[i] = (float) Math.pow((x + 0.055) / 1.055, 2.4);
			}
		}

		// Luminance as derived from https://www.w3.org/TR/WCAG20/
		final float luminance = (float) (0.2126 * postConversionValues[0] +
				0.7152 * postConversionValues[1] +
				0.0722 * postConversionValues[2]);

		if (luminance > 0.179) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
}