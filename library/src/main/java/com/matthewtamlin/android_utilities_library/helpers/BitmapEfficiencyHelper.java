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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Helper class for efficiently decoding Bitmap images.
 */
public class BitmapEfficiencyHelper {
	/**
	 * Calculates the sampling rate which can be used to decode a Bitmap by sub-sampling, such that
	 * all of the following conditions are satisfied: <ul><li>The sampling rate is a power of
	 * two.</li><li>The height and width of the sub-sampled Bitmap are greater than the desired
	 * height and width respectively.</li><li>The dimensions of the sub-sampled Bitmap are as small
	 * as possible.</li></ul>. If no sub-sampling is possible without violating the above
	 * constraints, a sampling rate of 1 is returned.
	 *
	 * @param rawWidth
	 * 		the inherent width of the image before scaling, measured in pixels, not less than zero
	 * @param rawHeight
	 * 		the inherent height of the image before scaling, measured in pixels, not less than zero
	 * @param desWidth
	 * 		the desired width of the image after scaling, measured in pixels, not less than zero
	 * @param desHeight
	 * 		the desired height of the image after scaling, measured in pixels, not less than zero
	 * @return the sampling rate to sub-sample with
	 * @throws IllegalArgumentException
	 * 		if any argument is less than zero
	 */
	public static int calculateSamplingRate(final int rawWidth, final int rawHeight, final int
			desWidth, final int desHeight) {
		if (rawWidth < 0 || rawHeight < 0 || desWidth < 0 || desHeight < 0) {
			throw new IllegalArgumentException("all dimensions must be greater than zero");
		}

		// Based on the power-of-two requirement
		final boolean scalingIsPossible = (rawWidth / 2 >= desWidth) &&
				(rawHeight / 2 >= desHeight);

		// Recursively double the sampling rate
		if (scalingIsPossible) {
			return 2 * calculateSamplingRate(rawWidth / 2, rawHeight / 2, desWidth, desHeight);
		} else {
			return 1;
		}
	}

	/**
	 * Decodes an image from a resource. The memory consumed by the decoded image is reduced by
	 * matching the image dimensions to the desired dimensions as best as possible. The dimensions
	 * of the returned image always exceeds or matches the supplied dimensions.
	 *
	 * @param context
	 * 		provides access to the resource to decode, not null
	 * @param resId
	 * 		the ID of the resource to decode
	 * @param desWidth
	 * 		the desired width of the decoded image, measured in pixels, not less than zero
	 * @param desHeight
	 * 		the desired height of the decoded image, measured in pixels, not less than zero
	 * @return the decoded image, null if the image could not be decoded
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null, or if either dimension is less than zero
	 */
	public static Bitmap decodeResource(final Context context, final int resId, final int desWidth,
			final int desHeight) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (desWidth < 0 || desHeight < 0) {
			throw new IllegalArgumentException("both dimensions must be greater than zero");
		}

		// Decode only the boundaries of the image to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(context.getResources(), resId, options);

		// Decode the full image using sub-sampling
		final int rawWidth = options.outWidth;
		final int rawHeight = options.outHeight;
		options.inSampleSize = calculateSamplingRate(rawWidth, rawHeight, desWidth, desHeight);
		options.inJustDecodeBounds = false; // Decode the full image
		options.inScaled = false;
		return BitmapFactory.decodeResource(context.getResources(), resId, options);
	}

	/**
	 * Decodes an image from an array of compressed image data. This method provides parameters for
	 * only parsing a subset of the data contained in the array. The memory consumed by the decoded
	 * image is reduced by matching the image dimensions to the desired dimensions as best as
	 * possible. The dimensions of the returned image always exceeds or matches the supplied
	 * dimensions.
	 *
	 * @param data
	 * 		a byte array of compressed image data, not null
	 * @param offset
	 * 		the offset into {@code data} to begin parsing at, counting from zero, not less than zero
	 * @param length
	 * 		the number of bytes at parse, not less than zero, less than {@code data.length - offset}
	 * @param desWidth
	 * 		the desired width of the decoded image, measured in pixels, not less than zero
	 * @param desHeight
	 * 		the desired height of the decoded image, measured in pixels, not less than zero
	 * @return the decoded image, null if the image could not be decoded
	 * @throws IllegalArgumentException
	 * 		if {@code data} is null; if {@code offset} is less than zero; if {@code length} is less
	 * 		than zero or greater than {@code data.length - offset}; or if either dimension is less than
	 * 		zero
	 */
	public static Bitmap decodeByteArray(final byte[] data, final int offset, final int length,
			final int desWidth, final int desHeight) {
		if (data == null) {
			throw new IllegalArgumentException("data cannot be null");
		} else if (offset < 0) {
			throw new IllegalArgumentException("offset cannot be less than zero");
		} else if (length < 0) {
			throw new IllegalArgumentException("length cannot be less than zero");
		} else if (desWidth < 0 || desHeight < 0) {
			throw new IllegalArgumentException("both dimensions must be greater than zero");
		}

		// Decode only the boundaries of the image to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, offset, length, options);

		// Decode the full image using sub-sampling
		final int rawWidth = options.outWidth;
		final int rawHeight = options.outHeight;
		options.inSampleSize = calculateSamplingRate(rawWidth, rawHeight, desWidth, desHeight);
		options.inJustDecodeBounds = false; // Decode the full image
		options.inScaled = false;
		return BitmapFactory.decodeByteArray(data, offset, length, options);
	}

	/**
	 * Decodes an image from an array of compressed image data. The memory consumed by the decoded
	 * image is reduced by matching the image dimensions to the desired dimensions as best as
	 * possible. The dimensions of the returned image always exceeds or matches the supplied
	 * dimensions.
	 *
	 * @param data
	 * 		a byte array of compressed image data, not null
	 * @param desWidth
	 * 		the desired width of the decoded image, measured in pixels, not less than zero
	 * @param desHeight
	 * 		the desired height of the decoded image, measured in pixels, not less than zero
	 * @return the decoded image, null if the image could not be decoded
	 * @throws IllegalArgumentException
	 * 		if {@code data} is null, or if desired dimension is less than zero
	 */
	public static Bitmap decodeByteArray(final byte[] data, final int desWidth,
			final int desHeight) {
		return decodeByteArray(data, 0, data.length, desWidth, desHeight);
	}

	/**
	 * Decodes an image from a File. The memory consumed by the decoded image is reduced by matching
	 * the image dimensions to the desired dimensions as best as possible. The dimensions of the
	 * returned image always exceeds or matches the supplied dimensions.
	 *
	 * @param file
	 * 		a File containing compressed image data, not null
	 * @param desWidth
	 * 		the desired width of the returned image, measured in pixels, not less than zero
	 * @param desHeight
	 * 		the desired height of the returned image, measured in pixels, not less than zero
	 * @return the decoded image, null if the image could not be decoded
	 * @throws IllegalArgumentException
	 * 		if {@code data} is null, or if either dimension is less than zero
	 */
	public static Bitmap decodeFile(final File file, final int desWidth, final int desHeight) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		} else if (desWidth < 0 || desHeight < 0) {
			throw new IllegalArgumentException("both dimensions must be greater than zero");
		}

		// Decode only the boundaries of the image to get its dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);

		// Decode the full image using sub-sampling
		final int rawWidth = options.outWidth;
		final int rawHeight = options.outHeight;
		options.inSampleSize = calculateSamplingRate(rawWidth, rawHeight, desWidth, desHeight);
		options.inJustDecodeBounds = false;
		options.inScaled = false;
		return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
	}
}