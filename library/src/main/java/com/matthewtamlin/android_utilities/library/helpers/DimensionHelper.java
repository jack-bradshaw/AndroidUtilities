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

import static com.matthewtamlin.java_utilities.checkers.IntChecker.checkGreaterThanOrEqualTo;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Converts complex units such as DP and SP to pixels.
 */
public class DimensionHelper {
	/**
	 * Converts a dimension from display-independent pixels (dp) to pixels (px).
	 *
	 * @param context
	 * 		a Context object containing the display metrics to base the conversion on, not null
	 * @param dpValue
	 * 		the dimension to convert, measured in display-independent pixels, not less than zero
	 *
	 * @return the supplied dimension converted to pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 * @throws IllegalArgumentException
	 * 		if {@code dpValue} is less than zero
	 */
	public static float dpToPx(final Context context, final float dpValue) {
		checkNotNull(context, "context cannot be null.");
		checkGreaterThanOrEqualTo((int) dpValue, 0, "dpValue must be at least 0.");

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
	 *
	 * @return the supplied dimension converted to pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 * @throws IllegalArgumentException
	 * 		if {@code spValue} is less than zero
	 */
	public static float spToPx(final Context context, final float spValue) {
		checkNotNull(context, "context cannot be null.");
		checkGreaterThanOrEqualTo((int) spValue, 0, "spValue must be at least 0.");

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
	 *
	 * @return the supplied dimension converted to pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 * @throws IllegalArgumentException
	 * 		if {@code inValue} is less than zero
	 */
	public static float inToPx(final Context context, final float inValue) {
		checkNotNull(context, "context cannot be null.");
		checkGreaterThanOrEqualTo((int) inValue, 0, "inValue must be at least 0.");

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
	 *
	 * @return the supplied dimension converted to pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 * @throws IllegalArgumentException
	 * 		if {@code mmValue} is less than zero
	 */
	public static float mmToPx(final Context context, final float mmValue) {
		checkNotNull(context, "context cannot be null.");
		checkGreaterThanOrEqualTo((int) mmValue, 0, "mmValue must be at least 0.");

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
	 *
	 * @return the supplied dimension converted to pixels
	 *
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null
	 * @throws IllegalArgumentException
	 * 		if {@code ptValue} is less than zero
	 */
	public static float ptToPx(final Context context, final float ptValue) {
		checkNotNull(context, "context cannot be null.");
		checkGreaterThanOrEqualTo((int) ptValue, 0, "ptValue must be at least 0.");

		final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, ptValue, metrics);
	}
}
