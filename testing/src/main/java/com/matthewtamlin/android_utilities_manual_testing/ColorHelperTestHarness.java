package com.matthewtamlin.android_utilities_manual_testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.matthewtamlin.android_utilities_library.testing.NoViewTestHarness;

public class ColorHelperTestHarness extends NoViewTestHarness {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(R.style.TestTheme);
		super.onCreate(savedInstanceState);
	}
}