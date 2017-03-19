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
import android.content.res.AssetManager;
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
 * Automated tests for the {@link AssetsHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestAssetsHelper {
	private static final String[] ASSETS_TO_COPY = {"small test file.txt", "large test file.txt"};

	private static final String[] ASSETS_TO_IGNORE = {"image.png"};

	private static final File OUTPUT_DIR = InstrumentationRegistry.getTargetContext().getFilesDir();

	private Context context;

	@Before
	public void setup() throws IOException {
		context = InstrumentationRegistry.getContext();

		final List<String> assets = new ArrayList<>(Arrays.asList(context.getAssets().list("")));
		assertThat("Missing expected asset.", assets.contains(ASSETS_TO_COPY[0]));
		assertThat("Missing expected asset.", assets.contains(ASSETS_TO_COPY[1]));
		assertThat("Missing expected asset.", assets.contains(ASSETS_TO_IGNORE[0]));

		if (!OUTPUT_DIR.exists()) {
			final boolean outputDirWasCreated = OUTPUT_DIR.mkdir();
			assertThat("Output directory could not be created.", outputDirWasCreated);
		}

		for (final File f : OUTPUT_DIR.listFiles()) {
			final boolean fileDeletedSuccessfully = f.delete();
			assertThat("Failed to delete all files in output directory.", fileDeletedSuccessfully);
		}
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link AssetsHelper#copyAssetsToDirectory(AssetManager, File, String...)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCopyAssetsToDirectory_invalidArg_nullContext() throws Exception {
		AssetsHelper.copyAssetsToDirectory(null, OUTPUT_DIR, ASSETS_TO_COPY);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code assets} argument of
	 * {@link AssetsHelper#copyAssetsToDirectory(AssetManager, File, String...)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCopyAssetsToDirectory_invalidArg_nullAssets() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context.getAssets(), OUTPUT_DIR, null);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code targetDirectory} argument
	 * of {@link AssetsHelper#copyAssetsToDirectory(AssetManager, File, String...)} is null.
	 *
	 * @throws Exception
	 * 		should not occur in this test, but declared by signature of called method
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCopyAssetsToDirectory_invalidArg_nullTargetDirectory() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context.getAssets(), null, ASSETS_TO_COPY);
	}

	/**
	 * Test to verify that the
	 * {@link AssetsHelper#copyAssetsToDirectory(AssetManager, File, String...)} method functions
	 * correctly when provided with valid arguments.
	 *
	 * @throws Exception
	 * 		the method under test may throw this exception if some operation fails
	 */
	@Test
	public void testCopyAssetsToDirectory_validArgs() throws Exception {
		AssetsHelper.copyAssetsToDirectory(context.getAssets(), OUTPUT_DIR, ASSETS_TO_COPY);

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