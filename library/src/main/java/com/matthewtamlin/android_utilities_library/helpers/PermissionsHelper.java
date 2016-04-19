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
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Utilities for working with permissions.
 */
public abstract class PermissionsHelper {
	/**
	 * Determines if the supplied {@code Context} has been granted the specified permissions.
	 *
	 * @param context
	 * 		the {@code Context} to query
	 * @param permissions
	 * 		a {@code String} array of permissions, sourced from {@link android.Manifest}.
	 * @return true if all of the specified permissions have been granted, false otherwise
	 */
	public static boolean permissionsAreGranted(final Context context, final String[]
			permissions) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (permissions == null) {
			throw new IllegalArgumentException("permissions cannot be null");
		}

		for (final String requiredPermission : permissions) {
			final boolean permGranted =
					(ActivityCompat.checkSelfPermission(context, requiredPermission) ==
							PackageManager.PERMISSION_GRANTED);

			if (!permGranted) {
				return false;
			}
		}

		// Execution only reaches here if all permissions are granted
		return true;
	}
}
