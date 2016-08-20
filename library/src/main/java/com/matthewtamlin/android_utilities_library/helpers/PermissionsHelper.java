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
import android.support.v4.app.ActivityCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Helper class for working with permissions.
 */
public class PermissionsHelper {
	/**
	 * Determines if the supplied Context has already been granted the specified permissions. For a
	 * list of possible permissions, see {@link android.Manifest}.
	 *
	 * @param context
	 * 		the Context to check the permission status of, not null
	 * @param permissions
	 * 		the permissions to check, not null
	 * @return true if all of the specified permissions have been granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} is null, or if {@code permissions} is null
	 */
	public static boolean permissionsAlreadyGranted(final Context context, final String[]
			permissions) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (permissions == null) {
			throw new IllegalArgumentException("permissions cannot be null");
		}

		// Record of how many permission are granted, for comparison with how many were requested
		int countPermissionsGranted = 0;

		// Check each requested permission and record the result
		for (final String permission : permissions) {
			if (ActivityCompat.checkSelfPermission(context, permission) == PERMISSION_GRANTED) {
				countPermissionsGranted++;
			} else {
				// No point checking the rest if one has already failed
				break;
			}
		}

		// Only return true if every requested permission is granted
		return (permissions.length == countPermissionsGranted);
	}
}