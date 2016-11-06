package com.matthewtamlin.android_utilities.library.testing;

import android.os.Bundle;
import android.view.View;

public class NoViewTestHarness extends ControlsOverViewTestHarness<View> {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View getTestView() {
		return new View(this);
	}
}
