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

import android.os.Bundle;

import com.matthewtamlin.android_utilities.library.helpers.ColorHelper;
import com.matthewtamlin.android_utilities.library.testing.NoViewTestHarness;

/**
 * A test harness for testing the {@link ColorHelper} class. The TestTheme is applied to
 * facilitate testing.
 */
public class ThemeColorHelperTestHarness extends NoViewTestHarness {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(R.style.TestTheme);
		super.onCreate(savedInstanceState);
	}
}