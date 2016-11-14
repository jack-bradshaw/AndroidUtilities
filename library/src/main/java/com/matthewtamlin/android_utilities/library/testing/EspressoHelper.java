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


import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.matthewtamlin.android_utilities.library.R;
import com.matthewtamlin.android_utilities.library.checkers.NullChecker;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;

/**
 * Helper class for automated testing with Espresso.
 */

public class EspressoHelper {
	/**
	 * Takes a View object and returns a ViewInteractor which allows the View to be manipulated and
	 * queried with the Espresso framework.
	 *
	 * @param view
	 * 		the view to get a ViewInteractor for, not null
	 * @return the ViewInteractor, not null
	 * @throws IllegalArgumentException
	 * 		if {@code view} is null
	 */
	public static ViewInteraction viewToViewInteraction(final View view) {
		NullChecker.checkNonNull(view, "view cannot be null");

		view.setTag(R.id.espresso_util_conversion_tag, "test");
		return onView(withTagKey(R.id.espresso_util_conversion_tag));
	}
}