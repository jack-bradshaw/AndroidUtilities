package com.matthewtamlin.android_utilities.testing;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.matthewtamlin.android_utilities.library.testing.ControlsOverViewTestHarness;
import com.matthewtamlin.android_utilities.library.testing.EspressoHelper;

import static android.widget.LinearLayout.VERTICAL;

/**
 * A test harness for testing the {@link EspressoHelper} class. The test harness displays three
 * TextViews, each with unique text. The text of each TextView can be accessed using the static
 * variables of the class.
 * <p>
 * This test harness presents no buttons for manually interacting with the view, and it intended to
 * be used with the espresso framework exclusively.
 */
public class EspressoHelperTestHarness extends ControlsOverViewTestHarness {
	/**
	 * The text displayed in the text view returned by {@link #getTextView1()}.
	 */
	public static final String TEXT_1 = "text 1";

	/**
	 * The text displayed in the text view returned by {@link #getTextView2()}.
	 */
	public static final String TEXT_2 = "text 2";

	/**
	 * The text displayed in the text view returned by {@link #getTextView3()}.
	 */
	public static final String TEXT_3 = "text 3";

	/**
	 * Hosts the TextViews.
	 */
	private LinearLayout testView;

	/**
	 * Displays {@link #TEXT_1).
	 */
	private TextView textView1;

	/**
	 * Displays {@link #TEXT_2).
	 */
	private TextView textView2;

	/**
	 * Displays {@link #TEXT_3).
	 */
	private TextView textView3;

	@Override
	public Object getTestView() {
		if (testView == null) {
			testView = new LinearLayout(this);
			testView.setOrientation(VERTICAL);

			testView.addView(getTextView1());
			testView.addView(getTextView2());
			testView.addView(getTextView3());
		}

		return testView;
	}

	/**
	 * @return the TextView which displays {@link #TEXT_1}.
	 */
	public TextView getTextView1() {
		if (textView1 == null) {
			textView1 = new TextView(this);
			textView1.setText(TEXT_1);
		}

		return textView1;
	}

	/**
	 * @return the TextView which displays {@link #TEXT_2}.
	 */
	public TextView getTextView2() {
		if (textView2 == null) {
			textView2 = new TextView(this);
			textView2.setText(TEXT_2);
		}

		return textView2;
	}

	/**
	 * @return the TextView which displays {@link #TEXT_3}.
	 */
	public TextView getTextView3() {
		if (textView3 == null) {
			textView3 = new TextView(this);
			textView3.setText(TEXT_3);
		}

		return textView3;
	}
}