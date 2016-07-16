package com.matthewtamlin.android_utilities_library.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.matthewtamlin.android_utilities_library.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import static com.matthewtamlin.android_utilities_library.TestingConstants.BASE_PATH;
import static com.matthewtamlin.android_utilities_library.TestingConstants.LARGE_PNG_FILENAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the BitmapHelper class.
 *
 * @deprecated class under test is deprecated
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 18, constants = BuildConfig.class)
@Deprecated
public class TestBitmapHelper {
	@Test
	public void decodeSampledBitmapFromFile_usingQuarterScale_shouldReduceSize() {
		final float testScale = 0.25f;
		final File testFile = new File(BASE_PATH + LARGE_PNG_FILENAME);

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		final Bitmap fullSize = BitmapFactory.decodeFile(testFile.getAbsolutePath(), options);
		final int desiredWidth = (int) testScale * fullSize.getWidth();
		final int desiredHeight = (int) testScale * fullSize.getHeight();

		final Bitmap reducedSize =
				BitmapHelper.decodeSampledBitmapFromFile(testFile, desiredWidth, desiredHeight);

		assertThat("returned Bitmap was not reduced in width",
				reducedSize.getWidth() < fullSize.getWidth(), is(true));
		assertThat("returned Bitmap was not reduced in height",
				reducedSize.getHeight() < fullSize.getHeight(), is(true));
		assertThat("returned Bitmap was reduced in width too much",
				reducedSize.getWidth() >= desiredWidth, is(true));
		assertThat("returned Bitmap was reduced in height too much",
				reducedSize.getHeight() >= desiredHeight, is(true));
	}

	@Test
	public void decodeSampledBitmapFromFile_usingUnityScale_shouldReturnInitialSize() {
		final float testScale = 1f;
		final File testFile = new File(BASE_PATH + LARGE_PNG_FILENAME);

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		final Bitmap fullSize = BitmapFactory.decodeFile(testFile.getAbsolutePath(), options);
		final int desiredWidth = (int) testScale * fullSize.getWidth();
		final int desiredHeight = (int) testScale * fullSize.getHeight();

		final Bitmap reducedSize =
				BitmapHelper.decodeSampledBitmapFromFile(testFile, desiredWidth, desiredHeight);

		assertThat("returned Bitmap was not reduced in width",
				reducedSize.getWidth() == fullSize.getWidth(), is(true));
		assertThat("returned Bitmap was not reduced in height",
				reducedSize.getHeight() == fullSize.getHeight(), is(true));
	}

	@Test
	public void decodeSampledBitmapFromFile_usingDoubleScale_shouldReturnInitialSize() {
		final float testScale = 2f;
		final File testFile = new File(BASE_PATH + LARGE_PNG_FILENAME);

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		final Bitmap fullSize = BitmapFactory.decodeFile(testFile.getAbsolutePath(), options);
		final int desiredWidth = (int) testScale * fullSize.getWidth();
		final int desiredHeight = (int) testScale * fullSize.getHeight();

		final Bitmap reducedSize =
				BitmapHelper.decodeSampledBitmapFromFile(testFile, desiredWidth, desiredHeight);

		assertThat("returned Bitmap was not reduced in width",
				reducedSize.getWidth() == fullSize.getWidth(), is(true));
		assertThat("returned Bitmap was not reduced in height",
				reducedSize.getHeight() == fullSize.getHeight(), is(true));
	}
}