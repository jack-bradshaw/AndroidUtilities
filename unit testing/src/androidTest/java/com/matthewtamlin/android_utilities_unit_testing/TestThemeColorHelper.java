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

package com.matthewtamlin.android_utilities_unit_testing;

import android.app.Activity;
import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_utilities_library.helpers.ThemeColorHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Unit tests for the {@link ThemeColorHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestThemeColorHelper {
	/**
	 * The expected primary color in the theme of the test activity.
	 */
	private static final int EXPECTED_PRIMARY_COLOR = 0xFFFF0000;

	/**
	 * The expected primary dark color in the theme of the test activity.
	 */
	private static final int EXPECTED_PRIMARY_DARK_COLOR = 0xFF00FF00;

	/**
	 * The expected accent color in the theme of the test activity.
	 */
	private static final int EXPECTED_ACCENT_COLOR = 0xFF0000FF;

	/**
	 * The default color to use in testing.
	 */
	private static final int DEFAULT_COLOR = 0xFF123456;

	/**
	 * Provides access to an Activity with the expected colors in the theme.
	 */
	@Rule
	public ActivityTestRule<TestActivity> testActivityRule = new ActivityTestRule<>
			(TestActivity.class);

	/**
	 * The Activity referenced by {@code testActivityRule}.
	 */
	private Activity activity;

	@Before
	public void init() {
		activity = testActivityRule.getActivity();

		assertThat("Precondition 1 failed. The activity is null.", activity, is(notNullValue()));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getPrimaryColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetPrimaryColor_invalidArg_nullContext() {
		ThemeColorHelper.getPrimaryColor(null, DEFAULT_COLOR);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getPrimaryColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetPrimaryColor_validArgs() {
		final int primaryColor = ThemeColorHelper.getPrimaryColor(activity, DEFAULT_COLOR);

		assertThat("Incorrect color returned.", primaryColor, is(EXPECTED_PRIMARY_COLOR));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getPrimaryDarkColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetPrimaryDarkColor_invalidArg_nullContext() {
		ThemeColorHelper.getPrimaryDarkColor(null, DEFAULT_COLOR);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getPrimaryDarkColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetPrimaryDarkColor_validArgs() {
		final int primaryDarkColor = ThemeColorHelper.getPrimaryDarkColor(activity,
				DEFAULT_COLOR);

		assertThat("Incorrect color returned.", primaryDarkColor, is(EXPECTED_PRIMARY_DARK_COLOR));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getAccentColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAccentColor_invalidArg_nullContext() {
		ThemeColorHelper.getAccentColor(null, DEFAULT_COLOR);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getAccentColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetAccentColor_validArgs() {
		final int accentColor = ThemeColorHelper.getAccentColor(activity, DEFAULT_COLOR);

		assertThat("Incorrect color returned.", accentColor, is(EXPECTED_ACCENT_COLOR));
	}
}