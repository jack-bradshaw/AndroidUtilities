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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Provides access to the test harnesses.
 */
@SuppressLint("SetTextI18n")
public class LauncherActivity extends AppCompatActivity {
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher_activity);

		final LinearLayout rootView = (LinearLayout) findViewById(R.id.launcher_activity_root);
		rootView.addView(createLaunchStatusBarHelperTestHarnessButton());
	}

	private Button createLaunchStatusBarHelperTestHarnessButton() {
		final Button b = new Button(this);
		b.setText("Launch StatusBarHelper test harness");

		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				startActivity(new Intent(LauncherActivity.this, StatusBarHelperTestHarness.class));
			}
		});

		return b;
	}
}