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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static com.matthewtamlin.android_utilities.library.R.id.espresso_util_conversion_tag;
import static com.matthewtamlin.android_utilities.library.checkers.NullChecker.checkNonNull;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;
import static org.hamcrest.Matchers.is;

/**
 * Helper class for automated testing with Espresso. Two methods are defined: {@link
 * #viewToViewInteraction(View)} and {@link #viewToViewInteraction(View, String)}. The former can
 * only be used once per view hierarchy, however the latter can be used any number of times. The
 * only restriction is that the {@code uniqueTag} argument must be unique to the view hierarchy.
 */
public class EspressoHelper {
	/**
	 * Takes a View object and returns a ViewInteractor which allows the view to be used with the
	 * Espresso framework. This method can only be used on a single view in the view hierarchy. To
	 * get ViewInteractors for multiple views, use {@link #viewToViewInteraction(View, String)}
	 * instead.
	 *
	 * @param view
	 * 		the view to get a ViewInteractor for, not null
	 * @return the ViewInteractor, not null
	 * @throws IllegalArgumentException
	 * 		if {@code view} is null
	 */
	public static synchronized ViewInteraction viewToViewInteraction(final View view) {
		checkNotNull(view, "view cannot be null");

		// Set the tag to uniquely identify the view
		view.setTag(espresso_util_conversion_tag, "test");

		// Find the view using the tag
		return onView(withTagKey(espresso_util_conversion_tag));
	}

	/**
	 * Takes a View object and returns a ViewInteractor which allows the view to be used with the
	 * Espresso framework. This method must be used instead of {@link #viewToViewInteraction(View)}
	 * if more than one view in the current view hierarchy is being converted. The value passed to
	 * the {@code uniqueTag} parameter may be any value, so long as each value is unique in the
	 * current view hierarchy.
	 *
	 * @param view
	 * 		the view to get a ViewInteractor for, not null
	 * @param uniqueTag
	 * 		a value which is unique for the current view hierarchy
	 * @return the ViewInteractor, not null
	 * @throws IllegalArgumentException
	 * 		if {@code view} is null
	 */
	public static synchronized ViewInteraction viewToViewInteraction(final View view,
			final String uniqueTag) {
		checkNonNull(view, "view cannot be null");

		// Set the tag to uniquely identify the view
		view.setTag(espresso_util_conversion_tag, uniqueTag);

		// Find the view using the tag
		return onView(withTagKey(espresso_util_conversion_tag, is((Object) uniqueTag)));
	}
}