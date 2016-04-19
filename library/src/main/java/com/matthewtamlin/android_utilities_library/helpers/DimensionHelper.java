package com.matthewtamlin.android_utilities_library.helpers;

import android.content.Context;
import android.util.TypedValue;

/**
 * Utilities for converting complex units to pixels.
 */
public abstract class DimensionHelper {
	/**
	 * Converts a dimension from display-independent pixels (dp) to pixels (px).
	 *
	 * @param dpValue
	 * 		the dimension to convert, measured in display-independent pixels
	 * @param context
	 * 		the context of the conversion
	 * @return the supplied dimension converted to pixels
	 */
	public static int dpToPx(final int dpValue, final Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
				context.getResources().getDisplayMetrics());
	}

	/**
	 * Converts a dimension from scaled pixels (sp) to pixels (px).
	 *
	 * @param spValue
	 * 		the dimension to convert, measured in scaled pixels
	 * @param context
	 * 		the context of the conversion
	 * @return the supplied dimension converted to pixels
	 */
	public static float spToPx(final float spValue, final Context context) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
				context.getResources().getDisplayMetrics());
	}

	/**
	 * Converts a dimension from inches (in) to pixels (px).
	 *
	 * @param inValue
	 * 		the dimension to convert, measured in inches
	 * @param context
	 * 		the context of the conversion
	 * @return the supplied dimension converted to pixels
	 */
	public static float inToPx(final float inValue, final Context context) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, inValue,
				context.getResources().getDisplayMetrics());
	}

	/**
	 * Converts a dimension from millimetres (mm) to pixels (px).
	 *
	 * @param mmValue
	 * 		the dimension to convert, measured in millimetres
	 * @param context
	 * 		the context of the conversion
	 * @return the supplied dimension converted to millimetres
	 */
	public static float mmToPx(final float mmValue, final Context context) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mmValue,
				context.getResources().getDisplayMetrics());
	}

	/**
	 * Converts a dimension from points (pt) to pixels (px).
	 *
	 * @param ptValue
	 * 		the dimension to convert, measured in points
	 * @param context
	 * 		the context of the conversion
	 * @return the supplied dimension converted to points
	 */
	public static float ptToPx(final float ptValue, final Context context) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, ptValue,
				context.getResources().getDisplayMetrics());
	}
}
