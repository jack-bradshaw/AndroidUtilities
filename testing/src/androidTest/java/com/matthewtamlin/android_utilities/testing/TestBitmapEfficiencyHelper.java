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

package com.matthewtamlin.android_utilities.testing;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_utilities.library.helpers.BitmapEfficiencyHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.matthewtamlin.android_utilities.library.helpers.BitmapEfficiencyHelper.decodeResource;
import static com.matthewtamlin.android_utilities.testing.test.R.raw.image;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * Unit tests for the {@link BitmapEfficiencyHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestBitmapEfficiencyHelper {
	/**
	 * The resource ID of a raw image resource for use in testing.
	 */
	private static final int TEST_RES_ID = image;

	/**
	 * Provides access to the Android system resources needed to run the tests.
	 */
	private Context context;

	/**
	 * A full size image (decoded from the raw resource) which can be used to measure the
	 * effectiveness of the class under test.
	 */
	private Bitmap fullSizeImage;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 */
	@Before
	public void setup() {
		// Using the target context provides access to the raw resources
		context = InstrumentationRegistry.getTargetContext();
		fullSizeImage = BitmapFactory.decodeResource(context.getResources(), TEST_RES_ID);

		assertThat("Precondition 1 failed.", context, is(notNullValue()));
		assertThat("Precondition 2 failed.", fullSizeImage, is(notNullValue()));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code rawWidth} argument of
	 * {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSamplingRate_invalidArg_negativeRawWidth() {
		BitmapEfficiencyHelper.calculateSamplingRate(-1, 10, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code rawHeight} argument of
	 * {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSamplingRate_invalidArg_negativeRawHeight() {
		BitmapEfficiencyHelper.calculateSamplingRate(10, -1, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desWidth} argument of
	 * {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSamplingRate_invalidArg_negativeDesWidth() {
		BitmapEfficiencyHelper.calculateSamplingRate(10, 10, -1, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desHeight} argument of
	 * {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSamplingRate_invalidArg_negativeDesHeight() {
		BitmapEfficiencyHelper.calculateSamplingRate(10, 10, 10, -1);
	}

	/**
	 * Test to verify that the {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int,
	 * int)} method functions correctly when provided with valid arguments. This test considers the
	 * case where both desired dimensions are greater than the respective raw dimensions.
	 */
	@Test
	public void testCalculateSamplingRate_validArgs_tryScalingUp() {
		final int rawWidth = fullSizeImage.getWidth();
		final int rawHeight = fullSizeImage.getHeight();
		final int desWidth = rawWidth * 2;
		final int desHeight = rawHeight * 2;

		final int samplingRate = BitmapEfficiencyHelper.calculateSamplingRate(rawWidth, rawHeight,
				desWidth, desHeight);

		assertThat("Sampling rate should be 1.", samplingRate, is(1));
	}

	/**
	 * Test to verify that the {@link BitmapEfficiencyHelper#calculateSamplingRate(int, int, int,
	 * int)} method functions correctly when provided with valid arguments. This test considers the
	 * case where both desired dimensions are less than than the respective raw dimensions.
	 */
	@Test
	public void testCalculateSamplingRate_validArgs_tryScalingDown() {
		final int rawWidth = fullSizeImage.getWidth();
		final int rawHeight = fullSizeImage.getHeight();
		final int desWidth = rawWidth / 2;
		final int desHeight = rawHeight / 2;

		final int samplingRate = BitmapEfficiencyHelper.calculateSamplingRate(rawWidth, rawHeight,
				desWidth, desHeight);

		assertThat("Sampling rate should be 2.", samplingRate, is(2));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link BitmapEfficiencyHelper#decodeResource(Resources, int, int, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeResource_invalidArg_nullContext() {
		decodeResource(null, TEST_RES_ID, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desWidth} argument of
	 * {@link BitmapEfficiencyHelper#decodeResource(Resources, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeResource_invalidArg_negativeWidth() {
		decodeResource(context.getResources(), TEST_RES_ID, -1, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desHeight} argument of
	 * {@link BitmapEfficiencyHelper#decodeResource(Resources, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeResource_invalidArg_negativeHeight() {
		decodeResource(context.getResources(), TEST_RES_ID, 10, -1);
	}

	/**
	 * Test to verify that no image is returned when the {@code redId} argument of {@link
	 * BitmapEfficiencyHelper#decodeResource(Resources, int, int, int)} does not reference an
	 * existing resource.
	 */
	@Test
	public void testDecodeResource_nonExistentResource() {
		final int badResId = 1000;

		final Bitmap image = decodeResource(context.getResources(), badResId, 10, 10);

		assertThat("Somehow a Bitmap was decoded.", image, is(nullValue()));
	}

	/**
	 * Test to verify that the {@link BitmapEfficiencyHelper#decodeResource(Resources, int, int,
	 * int)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testDecodeResource_validArgs() {
		final int testWidth = fullSizeImage.getWidth() / 2;
		final int testHeight = fullSizeImage.getHeight() / 2;

		final Bitmap decodedImage = decodeResource(context.getResources(), TEST_RES_ID,
				testWidth, testHeight);

		assertThat("Decoded image should not be null.", decodedImage, is(notNullValue()));
		assertThat("Width was not reduced.", decodedImage.getWidth(), is(lessThan(
				fullSizeImage.getWidth())));
		assertThat("Height was not reduced.", decodedImage.getHeight(), is(lessThan(
				fullSizeImage.getHeight())));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeByteArray_invalidArg_nullData() {
		BitmapEfficiencyHelper.decodeByteArray(null, 10, 10, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code offset} argument of
	 * {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeByteArray_invalidArg_negativeOffset() {
		BitmapEfficiencyHelper.decodeByteArray(new byte[]{}, -1, 10, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code length} argument of
	 * {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeByteArray_invalidArg_negativeLength() {
		BitmapEfficiencyHelper.decodeByteArray(new byte[]{}, 10, -1, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desWidth} argument of
	 * {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeByteArray_invalidArg_negativeWidth() {
		BitmapEfficiencyHelper.decodeByteArray(new byte[]{}, 10, 10, -1, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desHeight} argument of
	 * {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeByteArray_invalidArg_negativeHeight() {
		BitmapEfficiencyHelper.decodeByteArray(new byte[]{}, 10, 10, 10, -1);
	}

	/**
	 * Test to verify that the {@link BitmapEfficiencyHelper#decodeByteArray(byte[], int, int, int,
	 * int)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testDecodeByteArray_validArgs() {
		// The test artwork is needed as a byte array
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		fullSizeImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
		final byte[] fullSizeImageData = stream.toByteArray();

		final int testWidth = fullSizeImage.getWidth() / 2;
		final int testHeight = fullSizeImage.getHeight() / 2;

		final Bitmap decodedImage = BitmapEfficiencyHelper.decodeByteArray(fullSizeImageData, 0,
				fullSizeImageData.length, testWidth, testHeight);

		assertThat("Decoded image should not be null.", decodedImage, is(notNullValue()));
		assertThat("Width was not reduced.", decodedImage.getWidth(), is(lessThan(fullSizeImage
				.getWidth())));
		assertThat("Height was not reduced.", decodedImage.getHeight(), is(lessThan(fullSizeImage
				.getHeight())));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link BitmapEfficiencyHelper#decodeFile(File, int, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeFile_invalidArg_nullFile() {
		BitmapEfficiencyHelper.decodeFile(null, 10, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desWidth} argument of
	 * {@link BitmapEfficiencyHelper#decodeFile(File, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeFile_invalidArg_negativeWidth() {
		BitmapEfficiencyHelper.decodeFile(new File(""), -1, 10);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code desHeight} argument of
	 * {@link BitmapEfficiencyHelper#decodeFile(File, int, int)} is negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDecodeFile_invalidArg_negativeHeight() {
		BitmapEfficiencyHelper.decodeFile(new File(""), 10, -1);
	}
}