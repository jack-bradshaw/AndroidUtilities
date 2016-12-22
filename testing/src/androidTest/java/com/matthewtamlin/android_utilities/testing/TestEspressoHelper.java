package com.matthewtamlin.android_utilities.testing;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.matthewtamlin.android_utilities.library.testing.EspressoHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.matthewtamlin.android_utilities.library.testing.EspressoHelper.viewToViewInteraction;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link EspressoHelper} class.
 */
@RunWith(AndroidJUnit4.class)
public class TestEspressoHelper {
	/**
	 * Hosts three text views, each with unique text (available as static constants of the
	 * activity).
	 */
	@Rule
	public ActivityTestRule<EspressoHelperTestHarness> rule = new ActivityTestRule<>(
			EspressoHelperTestHarness.class);

	/**
	 * Test to ensure that the {@link EspressoHelper#viewToViewInteraction(View)} method functions
	 * correctly when only one view is being converted. The test will only pass if the correct
	 * ViewInteraction is returned, as determined by the text in the TextView it refers to.
	 */
	@Test
	public void testViewToViewInteraction_singleView() {
		final EspressoHelperTestHarness activity = rule.getActivity();

		final ViewInteraction tv1ViewInteraction = viewToViewInteraction(activity.getTextView1());

		tv1ViewInteraction.check(hasText(EspressoHelperTestHarness.TEXT_1));
	}

	/**
	 * Test to ensure that the {@link EspressoHelper#viewToViewInteraction(View, String)} method
	 * functions correctly when multiple view are being converted. The test will only pass if the
	 * correct ViewInteractions are returned, as determined by the text in the TextViews they refer
	 * to.
	 */
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

	/**
	 * Creates a new ViewAssertion which checks that a TextView is displaying the specified text.
	 * The returned ViewAssertion will throw an AssertionError if the view it is applied to is null
	 * or is not a TextView. Furthermore, an AssertionError will be thrown if the TextView is not
	 * displaying the expected text.
	 *
	 * @param expectedText
	 * 		the text which is expected to be shown in the TextView this assertion is applied to, null
	 * 		allowed
	 * @return the ViewAssertion
	 */
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