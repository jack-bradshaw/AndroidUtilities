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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import com.matthewtamlin.android_testing_tools.library.NoViewTestHarness;
import com.matthewtamlin.android_utilities.library.helpers.StatusBarHelper;

/**
 * Test Harness for testing the {@link StatusBarHelper} class.
 */
@RequiresApi(16) // For client
@TargetApi(16) // For lint
@SuppressLint("SetTextI18n") // Not important during testing
public class StatusBarHelperTestHarness extends NoViewTestHarness {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getControlsContainer().addView(createShowStatusBarButton());
		getControlsContainer().addView(createHideStatusBarButton());
	}

	private Button createShowStatusBarButton() {
		final Button b = new Button(this);
		b.setText("Show status bar button");
		b.setAllCaps(false);

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				StatusBarHelper.showStatusBar(getWindow());
			}
		});

		return b;
	}

	private Button createHideStatusBarButton() {
		final Button b = new Button(this);
		b.setText("Hide status bar button");
		b.setAllCaps(false);

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				StatusBarHelper.hideStatusBar(getWindow());
			}
		});

		return b;
	}
}