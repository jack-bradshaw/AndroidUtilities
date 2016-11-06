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
import static org.hamcrest.Matchers.notNullValue;

/**
 * Unit tests for the {@link PermissionsHelper} class. The tests will fail if any of the following
 * prerequisites are not met: <ul><li>The test context has been granted the {@code
 * WRITE_EXTERNAL_STORAGE} permission.</li> <li>The test context has been granted the {@code NFC}
 * permission.</li><li>The test has been denied the {@code ACCOUNT_MANAGER} permission.</li></ul>
 */
//TODO make a gradle task run before unit tests execute
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

	/**
	 * Provides access to the Android system resources needed to run the tests.
	 */
	private Context context;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 */
	@Before
	public void setup() {
		context = InstrumentationRegistry.getContext();

		// Check precondition 1: The context is not null
		assertThat("Precondition 1 failed. Context is null.", context, is(notNullValue()));

		// Check precondition 2: The context has been granted all permissions in GRANTED_PERMISSIONS
		for (final String permission : GRANTED_PERMISSIONS) {
			final boolean isGranted = (ContextCompat.checkSelfPermission(context, permission) ==
					PERMISSION_GRANTED);
			assertThat("Precondition 2 failed. Expected permissions not granted.", isGranted);
		}

		// Check precondition 3: The context has been denied all permissions in DENIED_PERMISSIONS
		for (final String permission : DENIED_PERMISSIONS) {
			final boolean isGranted = (ContextCompat.checkSelfPermission(context, permission) ==
					PERMISSION_GRANTED);
			assertThat("Precondition 3 failed. Expected permissions not denied", !isGranted);
		}
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link PermissionsHelper#permissionsAlreadyGranted(Context, String[])} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPermissionsAlreadyGranted_invalidArg_nullContext() {
		PermissionsHelper.permissionsAlreadyGranted(null, GRANTED_PERMISSIONS);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code permissions} argument of
	 * {@link PermissionsHelper#permissionsAlreadyGranted(Context, String[])} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPermissionsAlreadyGranted_invalidArg_nullPermissions() {
		PermissionsHelper.permissionsAlreadyGranted(context, null);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#permissionsAlreadyGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where all of the passed permissions are granted.
	 */
	@Test
	public void testPermissionsAlreadyGranted_validArgs_usingOnlyGrantedPermissions() {
		final boolean permissionsAreGranted = PermissionsHelper.permissionsAlreadyGranted
				(context, GRANTED_PERMISSIONS);
		assertThat("Some permissions have been denied unexpectedly.", permissionsAreGranted);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#permissionsAlreadyGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where all of the passed permissions are denied.
	 */
	@Test
	public void testPermissionsAlreadyGranted_validArgs_usingOnlyDeniedPermissions() {
		final boolean permissionsAreGranted = PermissionsHelper.permissionsAlreadyGranted
				(context, DENIED_PERMISSIONS);
		assertThat("Some permissions have been granted unexpectedly.", !permissionsAreGranted);
	}

	/**
	 * Test to verify that the {@link PermissionsHelper#permissionsAlreadyGranted(Context,
	 * String[])} method functions correctly when provided with valid arguments. This test
	 * considered the case where some of the passed permissions are granted and some are denied.
	 */
	@Test
	public void testPermissionsAlreadyGranted_validArgs_usingMixedPermissions() {
		final String[] allPermissions = concatenateArrays(GRANTED_PERMISSIONS, DENIED_PERMISSIONS);

		final boolean permissionsAreGranted = PermissionsHelper.permissionsAlreadyGranted
				(context, allPermissions);
		assertThat("Some permissions have been granted unexpectedly.", !permissionsAreGranted);
	}

	/**
	 * Concatenates two String arrays.
	 *
	 * @param arr1
	 * 		the first array to concatenate, not null
	 * @param arr2
	 * 		the second array to concatenate, not null
	 * @return the concatenated array, effectively [arr1, arr2]
	 */
	@SuppressWarnings("SameParameterValue") // Called only once to simplify readability
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