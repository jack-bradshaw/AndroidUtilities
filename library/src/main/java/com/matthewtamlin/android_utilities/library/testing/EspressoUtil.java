package com.matthewtamlin.android_utilities.library.testing;


import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.matthewtamlin.android_utilities.library.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;

public class EspressoUtil {
	public static ViewInteraction viewToViewInteraction(final View view) {
		view.setTag(R.id.espresso_util_conversion_tag, "test");
		return onView(withTagKey(R.id.espresso_util_conversion_tag));
	}
}
