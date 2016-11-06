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
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_utilities.library.helpers.AssetsHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AssetsHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestAssetsHelper {
	/**
	 * Specifies which assets should be copied from the assets space to the output directory.
	 */
	private static final String[] ASSETS_TO_COPY = {"small test file.txt", "large test file.txt"};

	/**
	 * Specifies which assets should not be copied from the asset space to the output directory.
	 */
	private static final String[] ASSETS_TO_IGNORE = {"image.png"};

	/**
	 * The directory to copy the assets to.
	 */
	private static final File OUTPUT_DIR = InstrumentationRegistry.getTargetContext()
			.getExternalFilesDir("/TestAssetsHelper");

	/**
	 * Provides access to the Android system resources needed to run the tests.
	 */
	private Context context;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 *
	 * @throws IOException
	 * 		may be thrown during setup, which will result in tests being aborted
	 */
	@Before
	@SuppressWarnings("ConstantConditions") // Mitigated manually
	public void setup() throws IOException {
		// Use this context instead of the target context so that the assets can be accessed
		context = InstrumentationRegistry.getContext();

		// Check precondition 1: Context is not null
		assertThat("Precondition 1 failed. The target context is null.", context != null);

		// Check precondition 2: The output directory is not a file
		assertThat("Precondition 3 failed. The output directory is a file.", !OUTPUT_DIR.isFile());

		// Check precondition 3: All assets under test are contained within the Context's assets
		final List<String> assets = new ArrayList<>(Arrays.asList(context.getAssets().list("")));
		final List<String> assetsUnderTest = new ArrayList<>();
		System.out.println(assets);
		assetsUnderTest.addAll(Arrays.asList(ASSETS_TO_COPY));
		assetsUnderTest.addAll(Arrays.asList(ASSETS_TO_IGNORE));
		assertThat("Precondition 4 failed. The assets folder does not contain all assets needed " +
				"for the test.", assets.containsAll(assetsUnderTest));

		// Check precondition 4: The output directory exists or can be created
		if (!OUTPUT_DIR.isDirectory()) {
			final boolean outputDirWasCreated = OUTPUT_DIR.mkdir();
			assertThat("Precondition 5 failed. Output directory not created.", outputDirWasCreated);
		}

		// Check precondition 5: All files in the output directory are deleted
		for (final File f : OUTPUT_DIR.listFiles()) {
			final boolean fileDeletedSuccessfully = f.delete();
			assertThat("Precondition 6 failed. Existing files in the output directory could not " +
					"be deleted.", fileDeletedSuccessfully);
		}
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link AssetsHelper#copyAssetsToDirectory(Context, String[], File)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("MissingPermission")
	public void testCopyAssetsToDirectory_invalidArg_nullContext() throws Exception {
		AssetsHelper.copyAssetsToDirectory(null, ASSETS_TO_COPY, OUTPUT_DIR);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code assets} argument of
	 * {@link AssetsHelper#copyAssetsToDirectory(Context, String[], File)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("MissingPermission")
	public void testCopyAssetsToDirectory_invalidArg_nullAssets() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context, null, OUTPUT_DIR);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code targetDirectory} argument
	 * of {@link AssetsHelper#copyAssetsToDirectory(Context, String[], File)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("MissingPermission")
	public void testCopyAssetsToDirectory_invalidArg_nullTargetDirectory() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context, ASSETS_TO_COPY, null);
	}

	/**
	 * Test to verify that the {@link AssetsHelper#copyAssetsToDirectory(Context, String[], File)}
	 * method functions correctly when provided with valid arguments.
	 *
	 * @throws Exception
	 * 		the method under test may throw this exception if some operation fails
	 */
	@Test
	@SuppressWarnings("MissingPermission")
	public void testCopyAssetsToDirectory_validArgs() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context, ASSETS_TO_COPY, OUTPUT_DIR);

		// Get the Files in the output directory as an ArrayList for ease of use (compared to array)
		final List<File> filesInOutputDir = new ArrayList<>(Arrays.asList(OUTPUT_DIR.listFiles()));

		// Post-condition 1: All assets to copy exist in the output directory
		for (final String assetName : ASSETS_TO_COPY) {
			final File potentialOutputFile = new File(OUTPUT_DIR, assetName);
			assertThat("An asset was unexpectedly not copied.", filesInOutputDir.contains
					(potentialOutputFile));
		}

		// Post-condition 2: None of the assets to ignore exist in the output directory
		for (final String assetName : ASSETS_TO_IGNORE) {
			final File potentialOutputFile = new File(OUTPUT_DIR, assetName);
			assertThat("An asset was unexpectedly copied.", !filesInOutputDir.contains
					(potentialOutputFile));
		}
	}
}