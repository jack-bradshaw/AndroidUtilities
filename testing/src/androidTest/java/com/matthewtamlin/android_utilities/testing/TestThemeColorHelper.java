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

import android.app.Activity;
import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import com.matthewtamlin.android_utilities.library.helpers.ThemeColorHelper;

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
	 * The expected primary color, as an ARGB hex code.
	 */
	private int primaryColor;

	/**
	 * The expected primary dark color, as an ARGB hex code.
	 */
	private int primaryDarkColor;

	/**
	 * The expected accent color, as an ARGB hex code.
	 */
	private int accentColor;

	/**
	 * The expected default color, as an ARGB hex code.
	 */
	private int defaultColor;

	/**
	 * Hosts an activity with the expected colours as theme properties.
	 */
	@Rule
	public final ActivityTestRule<ThemeColorHelperTestHarness> testActivityRule = new
			ActivityTestRule<>(ThemeColorHelperTestHarness.class);

	/**
	 * The Activity referenced by {@code testActivityRule}.
	 */
	private Activity activity;

	/**
	 * Initialises the testing environment and checks that all preconditions pass.
	 */
	@Before
	public void init() {
		activity = testActivityRule.getActivity();

		assertThat("Precondition 1 failed. The activity is null.", activity, is(notNullValue()));

		primaryColor = ContextCompat.getColor(activity, R.color.colorPrimary);
		primaryDarkColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark);
		accentColor = ContextCompat.getColor(activity, R.color.colorAccent);
		defaultColor = ContextCompat.getColor(activity, R.color.colorDefault);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getPrimaryColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetPrimaryColor_invalidArg_nullContext() {
		ThemeColorHelper.getPrimaryColor(null, defaultColor);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getPrimaryColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetPrimaryColor_validArgs() {
		final int color = ThemeColorHelper.getPrimaryColor(activity, defaultColor);

		assertThat("Incorrect color returned.", color, is(primaryColor));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getPrimaryDarkColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetPrimaryDarkColor_invalidArg_nullContext() {
		ThemeColorHelper.getPrimaryDarkColor(null, defaultColor);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getPrimaryDarkColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetPrimaryDarkColor_validArgs() {
		final int color = ThemeColorHelper.getPrimaryDarkColor(activity, defaultColor);

		assertThat("Incorrect color returned.", color, is(primaryDarkColor));
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link ThemeColorHelper#getAccentColor(Context, int)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAccentColor_invalidArg_nullContext() {
		ThemeColorHelper.getAccentColor(null, defaultColor);
	}

	/**
	 * Test to verify that the {@link ThemeColorHelper#getAccentColor(Context, int)} method
	 * functions correctly when provided with valid arguments.
	 */
	@Test
	public void testGetAccentColor_validArgs() {
		final int color = ThemeColorHelper.getAccentColor(activity, defaultColor);

		assertThat("Incorrect color returned.", color, is(accentColor));
	}
}