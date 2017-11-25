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

import android.content.res.AssetManager;

import com.matthewtamlin.java_utilities.testing.Tested;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Copies files from assets to a directory.
 */
public class AssetsHelper {
	/**
	 * Copies one or more assets to a directory.
	 *
	 * @param assetsManager
	 * 		provides access to the assets, not null
	 * @param targetDirectory
	 * 		the directory to copy the assets to, not null
	 * @param assets
	 * 		the names of the assets to copy (including any extensions), not null
	 *
	 * @throws IOException
	 * 		if an unspecified IO error occurs while writing to the target directory
	 * @throws IllegalArgumentException
	 * 		if {@code assetsManager} is null
	 * @throws IllegalArgumentException
	 * 		if {@code assetFiles} is null
	 * @throws IllegalArgumentException
	 * 		if {@code targetDirectory} is null
	 */
	public static void copyAssetsToDirectory(
			final AssetManager assetsManager,
			final File targetDirectory,
			final String... assets)
			throws IOException {

		checkNotNull(assetsManager, "assetsManager cannot be null");
		checkNotNull(targetDirectory, "targetDirectory cannot be null");
		checkNotNull(assets, "assetFiles cannot be null");

		for (final String filename : assets) {
			final File targetFile = new File(targetDirectory, filename);

			InputStream fromAssets = null;
			OutputStream toTarget = null;

			try {
				fromAssets = assetsManager.open(filename);
				toTarget = new FileOutputStream(targetFile);

				copyData(fromAssets, toTarget);
			} finally {
				if (fromAssets != null) {
					fromAssets.close();
				}

				if (toTarget != null) {
					toTarget.close();
				}
			}
		}
	}

	/**
	 * Copies data from the source stream to the target stream.
	 *
	 * @param source
	 * 		the stream to copy from, not null
	 * @param target
	 * 		the stream to copy to, not null
	 *
	 * @throws IOException
	 * 		if an unspecified IO error occurs while copying data
	 * @throws IllegalArgumentException
	 * 		if {@code source} is null
	 * @throws IllegalArgumentException
	 * 		if {@code target} is null
	 */
	private static void copyData(
			final InputStream source,
			final OutputStream target)
			throws IOException {

		checkNotNull(source, "source cannot be null.");
		checkNotNull(target, "target cannot be null.");

		final byte[] buffer = new byte[1024];

		int numberOfBytesRead = source.read(buffer);

		// While the buffer has data, write to the output stream from the buffer
		while (numberOfBytesRead != -1) {
			target.write(buffer, 0, numberOfBytesRead);
			numberOfBytesRead = source.read(buffer);
		}
	}
}