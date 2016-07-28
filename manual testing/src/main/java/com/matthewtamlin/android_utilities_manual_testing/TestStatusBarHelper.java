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

package com.matthewtamlin.android_utilities_manual_testing;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.matthewtamlin.android_utilities_library.helpers.StatusBarHelper;

/**
 * Manual tests for the {@link StatusBarHelper} class.
 */
public class TestStatusBarHelper extends AppCompatActivity {
	private View rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_status_bar_helper);
		rootView = findViewById(R.id.test_status_bar_helper_root);
	}

	/**
	 * Tests that the {@link StatusBarHelper#showStatusBar(Window)} method functions correctly. This
	 * test should only be passed if the status bar is shown after the method is run.
	 *
	 * @param v
	 * 		the View which was clicked to initiate the test
	 */
	public void testShowStatusBar(final View v) {
		StatusBarHelper.showStatusBar(getWindow());
		Snackbar.make(rootView, "The status bar should be shown.", Snackbar.LENGTH_LONG).show();
	}

	/**
	 * Tests that the {@link StatusBarHelper#hideStatusBar(Window)} method functions correctly. This
	 * test should only be passed if the status bar is hidden after the method is run.
	 *
	 * @param v
	 * 		the View which was clicked to initiate the test
	 */
	public void testHideStatusBar(final View v) {
		StatusBarHelper.hideStatusBar(getWindow());
		Snackbar.make(rootView, "The status bar should be hidden.", Snackbar.LENGTH_LONG).show();
	}
}