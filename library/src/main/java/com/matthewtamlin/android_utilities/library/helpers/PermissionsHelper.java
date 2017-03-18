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

import android.content.Context;
import android.support.v4.app.ActivityCompat;

import com.matthewtamlin.java_utilities.testing.Tested;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Checks if permissions have been granted.
 */
@Tested(testMethod = "automated")
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
	public static boolean permissionsAlreadyGranted(final Context context, final String...
			permissions) {
		checkNotNull(context, "context cannot be null.");
		checkNotNull(permissions, "permissions cannot be null.");
		
		for (final String permission : permissions) {
			if (ActivityCompat.checkSelfPermission(context, permission) != PERMISSION_GRANTED) {
				return false;
			}
		}

		return true;
	}

	//TODO put in check for at least one permission
}