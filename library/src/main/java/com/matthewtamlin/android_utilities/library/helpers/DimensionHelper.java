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
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.matthewtamlin.android_utilities.library.testing.Tested;

/**
 * Helper class for converting complex units to pixels.
 */
public class DimensionHelper {
	/**
	 * Converts a dimension from display-independent pixels (dp) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param dpValue
	 * 		the dimension to convert, measured in display-independent pixels, not less than zero
	 * @return the supplied dimension converted to pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null or if {@code dpValue} is less than zero
	 */
	public static int dpToPx(final Context context, final int dpValue) {
		checkArguments(context, dpValue, "dpValue");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metrics);
	}

	/**
	 * Converts a dimension from scaled pixels (sp) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param spValue
	 * 		the dimension to convert, measured in scaled pixels, not less than zero
	 * @return the supplied dimension converted to pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null or if {@code spValue} is less than zero
	 */
	public static float spToPx(final Context context, final float spValue) {
		checkArguments(context, spValue, "spValue");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics);
	}

	/**
	 * Converts a dimension from inches (in) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param inValue
	 * 		the dimension to convert, measured in inches, not less than zero
	 * @return the supplied dimension converted to pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null or if {@code inValue} is less than zero
	 */
	public static float inToPx(final Context context, final float inValue) {
		checkArguments(context, inValue, "inValue");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, inValue, metrics);
	}

	/**
	 * Converts a dimension from millimetres (mm) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param mmValue
	 * 		the dimension to convert, measured in millimetres, not less than zero
	 * @return the supplied dimension converted to pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null or if {@code mmValue} is less than zero
	 */
	public static float mmToPx(final Context context, final float mmValue) {
		checkArguments(context, mmValue, "mmValue");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mmValue, metrics);
	}

	/**
	 * Converts a dimension from points (pt) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param ptValue
	 * 		the dimension to convert, measured in points, not less than zero
	 * @return the supplied dimension converted to pixels
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null or if {@code ptValue} is less than zero
	 */
	public static float ptToPx(final Context context, final float ptValue) {
		checkArguments(context, ptValue, "ptValue");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, ptValue, metrics);
	}

	/**
	 * Utility for checking the values passed to the static conversion methods of this class. If the
	 * supplied context is null or the supplied dimension is less than zero, an
	 * IllegalArgumentException is thrown. If both arguments pass, the method exists normally. The
	 * dimensionArgName argument of this method allows the exception to specify the name of the
	 * dimension parameter.
	 *
	 * @param context
	 * 		the context to check
	 * @param dimension
	 * 		the dimension to check
	 * @param dimensionParamName
	 * 		the name of the dimension parameter in the calling method
	 */
	private static void checkArguments(final Context context, final float dimension, final String
			dimensionParamName) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (dimension < 0) {
			// If no dimensionParamName was supplied, just use a generic term
			final String dimName = dimensionParamName == null ? "dimension" : dimensionParamName;
			throw new IllegalArgumentException(dimName + " cannot be less than zero");
		}
	}
}
