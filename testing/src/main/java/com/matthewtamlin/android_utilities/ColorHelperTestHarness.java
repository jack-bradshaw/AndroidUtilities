package com.matthewtamlin.android_utilities;

import android.os.Bundle;

import com.matthewtamlin.android_utilities.testing.NoViewTestHarness;

public class ColorHelperTestHarness extends NoViewTestHarness {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(R.style.TestTheme);
		super.onCreate(savedInstanceState);
	}
}