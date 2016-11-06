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

package com.matthewtamlin.android_utilities.library.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.matthewtamlin.android_utilities.library.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A TestHarness which displays control buttons above the test view.
 *
 * @param <V>
 * 		the type of view being tested
 */
public abstract class ControlsAboveViewTestHarness<V> extends TestHarness<V, FrameLayout> {
	@Override
	protected void onCreate(final @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlsaboveview);
		getTestViewContainer().addView((View) getTestView());
		initialiseControlHiding();
	}

	@Override
	public View getRootView() {
		return findViewById(R.id.controlsAboveView_root);
	}

	@Override
	public LinearLayout getControlsContainer() {
		return (LinearLayout) findViewById(R.id.controlsAboveView_controlsContainer);
	}

	@Override
	public FrameLayout getTestViewContainer() {
		return (FrameLayout) findViewById(R.id.controlsAboveView_testViewContainer);
	}

	/**
	 * Configures a button to hide/show the controls when clicked.
	 */
	private void initialiseControlHiding() {
		final Button toggleControlVisibilityButton = (Button) findViewById(R.id
				.controlsAboveView_hideShowControlsButton);
		final LinearLayout controlButtonContainer = (LinearLayout) findViewById(R.id
				.controlsAboveView_controlsContainer);

		toggleControlVisibilityButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				controlButtonContainer.setVisibility(controlButtonContainer.getVisibility() ==
						VISIBLE ? GONE : VISIBLE);
			}
		});
	}
}