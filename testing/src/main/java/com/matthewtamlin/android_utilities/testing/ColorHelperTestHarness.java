package com.matthewtamlin.android_utilities.testing;

import android.os.Bundle;

import com.matthewtamlin.android_utilities.library.helpers.ColorHelper;
import com.matthewtamlin.android_utilities.library.testing.NoViewTestHarness;

/**
 * A test harness for testing the {@link ColorHelper} class. The TestTheme is applied to
 * facilitate testing.
 */
public class ColorHelperTestHarness extends NoViewTestHarness {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(R.style.TestTheme);
		super.onCreate(savedInstanceState);
	}
}