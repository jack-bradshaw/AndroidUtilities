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

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Utilities for decoding Bitmap images efficiently.
 *
 * @deprecated use {@link BitmapEfficiencyHelper} instead
 */
@Deprecated
public abstract class BitmapHelper {
	/**
	 * Calculates the largest sampling rate which is a power of two and keeps the scaled height and
	 * width greater than the requested height and width.
	 *
	 * @param rawWidth
	 * 		the inherent width of the image before scaling
	 * @param rawHeight
	 * 		the inherent height of the image before scaling
	 * @param desWidth
	 * 		the desired width of the image after scaling
	 * @param desHeight
	 * 		the desired height of the image after scaling
	 * @return the sampling rate which can be used to decode an image from the raw dimensions to the
	 * desired dimensions
	 */
	private static int calculateInSampleSize(final int rawWidth, final int rawHeight,
			final int desWidth, final int desHeight) {
		int inSampleSize = 1;

		if (rawWidth > desWidth || rawHeight > desHeight) {
			final int halfWidth = rawWidth / 2;
			final int halfHeight = rawHeight / 2;

			while ((halfWidth / inSampleSize) > desWidth &&
					(halfHeight / inSampleSize) > desHeight) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	/**
	 * Efficiently loads an image from a resource. The width and height of the returned Bitmap are
	 * guaranteed to be at least as large as the desired values.
	 *
	 * @param res
	 * 		a Resource object which grants access to the resource at {@code resId}
	 * @param resId
	 * 		the resource ID of the image to decode
	 * @param desWidth
	 * 		the desired width of the returned Bitmap
	 * @param desHeight
	 * 		the desired height of the returned Bitmap
	 * @return the scaled Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromResource(final Resources res, final int resId,
			final int desWidth, final int desHeight) {

		// Decode only the boundaries of the bitmap to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Sample the image to produce a bitmap of the required dimensions
		options.inSampleSize =
				calculateInSampleSize(options.outHeight, options.outWidth, desWidth, desHeight);
		options.inJustDecodeBounds = false;
		options.inScaled = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * Efficiently decodes an image from a byte array. The height and width of the returned Bitmap
	 * are guaranteed to be at least as large as the desired values.
	 *
	 * @param data
	 * 		a byte array of compressed image data
	 * @param offset
	 * 		the offset into {@code data} to begin parsing at
	 * @param length
	 * 		the number of bytes at parse, beginning at {@code offset}
	 * @param desWidth
	 * 		the desired width of the returned Bitmap
	 * @param desHeight
	 * 		the desired height of the returned Bitmap
	 * @return the scaled Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromByteArray(final byte[] data, final int offset,
			final int length, final int desWidth, final int desHeight) {
		// Decode only the boundaries of the bitmap to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, offset, length, options);

		// Sample the image to produce a bitmap of the required dimensions
		options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight,
				desWidth, desHeight);
		options.inJustDecodeBounds = false;
		options.inScaled = false;
		return BitmapFactory.decodeByteArray(data, offset, length, options);
	}

	/**
	 * Efficiently decodes an image from a byte array. The height and width of the returned Bitmap
	 * are guaranteed to be at least as large as the desired values.
	 *
	 * @param data
	 * 		a byte array of compressed image data
	 * @param desWidth
	 * 		the desired width of the returned Bitmap
	 * @param desHeight
	 * 		the desired height of the returned Bitmap
	 * @return the scaled Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromByteArray(final byte[] data, final int desWidth,
			final int desHeight) {
		return decodeSampledBitmapFromByteArray(data, 0, data.length, desWidth, desHeight);
	}

	/**
	 * Efficiently decodes an image from a File. The height and width of the returned Bitmap are
	 * guaranteed to be at least as large as the desired values.
	 *
	 * @param file
	 * 		the File to decode
	 * @param desWidth
	 * 		the desired width of the returned Bitmap
	 * @param desHeight
	 * 		the desired height of the returned Bitmap
	 * @return the scaled Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromFile(final File file, final int desWidth,
			final int desHeight) {
		// Decode only the boundaries of the bitmap to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);

		// Sample the image to produce a bitmap of the required dimensions
		options.inSampleSize =
				calculateInSampleSize(options.outHeight, options.outWidth, desWidth, desHeight);
		options.inJustDecodeBounds = false;
		options.inScaled = false;
		return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
	}
}