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

import com.matthewtamlin.java_utilities.testing.Tested;

import java.util.Random;

/**
 * Various helper methods for working with colors.
 */
public class ColorHelper {
	/**
	 * Blends two colors together using the individual ARGB channels. The {@code ratio} argument
	 * controls the proportion of each colour to use in the resulting color. Supplying a ratio of 0
	 * would result in color1 being returned, and supplying a ratio of 1 would result in color2
	 * being returned. The resulting colour varies linearly for ratios between 0 and 1.
	 *
	 * @param color1
	 * 		the first color to blend, as an ARGB hex code
	 * @param color2
	 * 		the second color to blend, as an ARGB hex code
	 * @param ratio
	 * 		the ratio of color1 to color2, as a value between 0 and 1 (inclusive)
	 *
	 * @return the ARGB code for the blended colour
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code ratio} is not between 0 and 1 (inclusive)
	 */
	public static int blendColors(final int color1, final int color2, final float ratio) {
		if (ratio < 0 || ratio > 1) {
			throw new IllegalArgumentException("ratio must be between 0 and 1 (inclusive)");
		}

		final float inverseRatio = 1f - ratio;

		final float a = (Color.alpha(color1) * inverseRatio) + (Color.alpha(color2) * ratio);
		final float r = (Color.red(color1) * inverseRatio) + (Color.red(color2) * ratio);
		final float g = (Color.green(color1) * inverseRatio) + (Color.green(color2) * ratio);
		final float b = (Color.blue(color1) * inverseRatio) + (Color.blue(color2) * ratio);

		return Color.argb((int) a, (int) r, (int) g, (int) b);
	}

	/**
	 * Calculates the text color which maximises readability against a colored background.
	 *
	 * @param backgroundColor
	 * 		the color of the background behind the text, as an ARGB hex code
	 *
	 * @return the text color to use, either white (0xFFFFFF) or black (0x000000)
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

	/**
	 * Creates a random color.
	 *
	 * @param randomiseTransparency
	 * 		true to randomise the alpha channel, false to make the alpha channel always 255
	 *
	 * @return a random color as an ARGB hex code
	 */
	public static int createRandomColor(final boolean randomiseTransparency) {
		final Random random = new Random();

		final int a = randomiseTransparency ? random.nextInt(256) : 255;
		final int r = random.nextInt(256);
		final int g = random.nextInt(256);
		final int b = random.nextInt(256);

		return Color.argb(a, r, g, b);
	}
}