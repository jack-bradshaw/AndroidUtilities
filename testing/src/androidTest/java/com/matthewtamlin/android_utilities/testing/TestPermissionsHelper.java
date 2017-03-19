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

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import com.matthewtamlin.android_utilities.library.helpers.PermissionsHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Automated tests for the {@link PermissionsHelper} class. These tests require the following
 * permissions:<ul><li>{@code WRITE_EXTERNAL_STORAGE} granted</li> <li>{@code NFC} granted</li>
 * <li>{@code ACCOUNT_MANAGER} denied</li></ul>
 */
@RunWith(AndroidJUnit4.class)
public class TestPermissionsHelper {
	/**
	 * The test context must have been granted these permissions for the tests to pass.
	 */
	private static final String[] GRANTED_PERMISSIONS = {Manifest.permission
			.WRITE_EXTERNAL_STORAGE, Manifest.permission.NFC};

	/**
	 * The test context must have been denied these permissions for the tests to pass.
	 */
	private static final String[] DENIED_PERMISSIONS = {Manifest.permission.ACCOUNT_MANAGER};

	private Context context;

	@Before
	public void setup() {
		context = InstrumentationRegistry.getContext();

		for (final String permission : GRANTED_PERMISSIONS) {
			final boolean isGranted = (ContextCompat.checkSelfPermission(context, permission) ==
					PERMISSION_GRANTED);
			assertThat("Permission not granted: " + permission, isGranted);
		}

		for (final String permission : DENIED_PERMISSIONS) {
			final boolean isGranted = (ContextCompat.checkSelfPermission(context, permission) ==
					PERMISSION_GRANTED);
			assertThat("Permission granted: " + permission, !isGranted);
		}
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link PermissionsHelper#checkAllPermissionsGranted(Context, String[])} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAllPermissionsGranted_invalidArg_nullContext() {
		PermissionsHelper.checkAllPermissionsGranted(null, GRANTED_PERMISSIONS);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code permissions} argument of
	 * {@link PermissionsHelper#checkAllPermissionsGranted(Context, String[])} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAllPermissionsGranted_invalidArg_nullPermissions() {
		PermissionsHelper.checkAllPermissionsGranted(context, null);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#checkAllPermissionsGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where all of the passed permissions are granted.
	 */
	@Test
	public void testAllPermissionsGranted_validArgs_usingOnlyGrantedPermissions() {
		final boolean permissionsAreGranted = PermissionsHelper.checkAllPermissionsGranted
				(context, GRANTED_PERMISSIONS);
		assertThat("Some permissions have been denied unexpectedly.", permissionsAreGranted);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#checkAllPermissionsGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where all of the passed permissions are denied.
	 */
	@Test
	public void testAllPermissionsGranted_validArgs_usingOnlyDeniedPermissions() {
		final boolean permissionsAreGranted = PermissionsHelper.checkAllPermissionsGranted
				(context, DENIED_PERMISSIONS);
		assertThat("Some permissions have been granted unexpectedly.", !permissionsAreGranted);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#checkAllPermissionsGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where some of the passed permissions are granted and some are denied.
	 */
	@Test
	public void testAllPermissionsGranted_validArgs_usingMixedPermissions() {
		final String[] allPermissions = concatenateArrays(GRANTED_PERMISSIONS, DENIED_PERMISSIONS);

		final boolean permissionsAreGranted = PermissionsHelper.checkAllPermissionsGranted
				(context, allPermissions);
		assertThat("Some permissions have been granted unexpectedly.", !permissionsAreGranted);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountGrantedPermissions_nullContext() {
		PermissionsHelper.countGrantedPermissions(null, GRANTED_PERMISSIONS);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountGrantedPermissions_nullPermissions() {
		PermissionsHelper.countGrantedPermissions(context, null);
	}

	@Test
	public void testCountGrantedPermissions_nonEmptyPermissionsSupplied() {
		final String[] allPerms = concatenateArrays(GRANTED_PERMISSIONS, DENIED_PERMISSIONS);
		final int count = PermissionsHelper.countGrantedPermissions(context, allPerms);
		assertThat(count, is(2));
	}

	@Test
	public void testCountGrantedPermissions_emptyPermissionsSupplied() {
		final int count = PermissionsHelper.countGrantedPermissions(context);
		assertThat(count, is(0));
	}

	/**
	 * Concatenates two String arrays.
	 *
	 * @param arr1
	 * 		the first array to concatenate, not null
	 * @param arr2
	 * 		the second array to concatenate, not null
	 *
	 * @return the concatenated array, effectively [arr1, arr2]
	 */
	private String[] concatenateArrays(final String[] arr1, final String[] arr2) {
		if (arr1 == null || arr2 == null) {
			throw new IllegalArgumentException("both arrays must be non-null");
		}

		final String[] output = new String[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, output, 0, arr1.length);
		System.arraycopy(arr2, 0, output, arr1.length, arr2.length);
		return output;
	}
}