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

import android.content.res.AssetManager;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utilities for working with assets.
 */
public abstract class AssetsHelper {
	/**
	 * Used during debugging to identify this class.
	 */
	private static final String TAG = "[AssetsHelper]";

	/**
	 * Copies files from assets to another directory.
	 *
	 * @param assetManager
	 * 		an instance of the asset manager for the files to transfer
	 * @param assetFiles
	 * 		the filenames of the files to transfer
	 * @param targetDirectory
	 * 		the directory to copy the files to
	 * @throws IOException
	 * 		if a general IO based error occurs while copying the files
	 */
	public static void copyAssetsToDirectory(final AssetManager assetManager, String[] assetFiles,
			final File targetDirectory) throws IOException {
		if (assetFiles != null) {
			for (String filename : assetFiles) {
				InputStream fromAssets = null;
				OutputStream toTargetDirectory = null;

				try {
					File outFile = new File(targetDirectory, filename);
					toTargetDirectory = new FileOutputStream(outFile);
					fromAssets = assetManager.open(filename);
					copyFile(fromAssets, toTargetDirectory);
				} finally {
					closeStream(fromAssets);
					closeStream(toTargetDirectory);
				}
			}
		}
	}

	/**
	 * Copies a file from the source stream to the target stream.
	 *
	 * @param source
	 * 		the source of the data to copy
	 * @param target
	 * 		the target to copy data to
	 * @throws IOException
	 * 		if a general IO based error occurs while transferring data
	 */
	private static void copyFile(final InputStream source, final OutputStream target) throws
			IOException {
		// Data is moved from the input stream to the output stream through buffer
		byte[] buffer = new byte[1024];

		// Read data into the buffer from the input stream
		int numberOfBytesRead = source.read(buffer);

		// if there was data received, write data to the output stream from the butter
		while (numberOfBytesRead != -1) {
			target.write(buffer, 0, numberOfBytesRead);
			numberOfBytesRead = source.read(buffer);
		}
	}

	/**
	 * Closes a stream. Any any IOExceptions which occur are logged.
	 *
	 * @param stream
	 * 		the stream to close
	 */
	private static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				Log.e(TAG, "[Error closing stream] [Stack trace: " + e.getStackTrace() + "]");
			}
		}
	}
}