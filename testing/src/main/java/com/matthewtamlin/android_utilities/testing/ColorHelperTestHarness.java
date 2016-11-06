package com.matthewtamlin.android_utilities.testing;

import android.os.Bundle;

import com.matthewtamlin.android_utilities.library.testing.NoViewTestHarness;

public class ColorHelperTestHarness extends NoViewTestHarness {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(com.matthewtamlin.android_utilities.testing.R.style.TestTheme);
		super.onCreate(savedInstanceState);
	}
}