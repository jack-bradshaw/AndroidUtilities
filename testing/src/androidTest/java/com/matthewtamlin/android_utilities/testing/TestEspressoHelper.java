package com.matthewtamlin.android_utilities.testing;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.matthewtamlin.android_utilities.library.testing.EspressoHelper.viewToViewInteraction;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class TestEspressoHelper {
	@Rule
	public ActivityTestRule<EspressoHelperTestHarness> rule = new ActivityTestRule<>(
			EspressoHelperTestHarness.class);

	@Test
	public void testViewToViewInteraction_singleView() {
		final EspressoHelperTestHarness activity = rule.getActivity();

		final ViewInteraction tv1ViewInteraction = viewToViewInteraction(activity.getTextView1());

		tv1ViewInteraction.check(hasText(EspressoHelperTestHarness.TEXT_1));
	}

	@Test
	public void testViewToViewInteraction_multipleViews() {
		final EspressoHelperTestHarness activity = rule.getActivity();

		final ViewInteraction tv1ViewInteraction = viewToViewInteraction(activity.getTextView1(),
				"1");
		final ViewInteraction tv2ViewInteraction = viewToViewInteraction(activity.getTextView2(),
				"2");
		final ViewInteraction tv3ViewInteraction = viewToViewInteraction(activity.getTextView3(),
				"3");

		tv1ViewInteraction.check(hasText(EspressoHelperTestHarness.TEXT_1));
		tv2ViewInteraction.check(matches(withText(EspressoHelperTestHarness.TEXT_2)));
		tv3ViewInteraction.check(matches(withText(EspressoHelperTestHarness.TEXT_3)));
	}

	private ViewAssertion hasText(final CharSequence expectedText) {
		return new ViewAssertion() {
			@Override
			public void check(final View view, final NoMatchingViewException noViewFoundException) {
				if (view == null || !(view instanceof TextView)) {
					throw new AssertionError("view must be a non-null instance of TextView");
				} else {
					final TextView tv = (TextView) view;
					assertThat("text view has wrong text.", tv.getText(), is(expectedText));
				}
			}
		};
	}
}
