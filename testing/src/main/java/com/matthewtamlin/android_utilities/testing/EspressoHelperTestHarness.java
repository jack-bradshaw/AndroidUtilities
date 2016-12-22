package com.matthewtamlin.android_utilities.testing;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.matthewtamlin.android_utilities.library.testing.ControlsOverViewTestHarness;

import org.junit.Test;

import static android.widget.LinearLayout.VERTICAL;

public class EspressoHelperTestHarness extends ControlsOverViewTestHarness {
	public static final String TEXT_1 = "text 1";

	public static final String TEXT_2 = "text 2";

	public static final String TEXT_3 = "text 3";

	private LinearLayout testView;

	private TextView textView1;

	private TextView textView2;

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

	public TextView getTextView1() {
		if (textView1 == null) {
			textView1 = new TextView(this);
			textView1.setText(TEXT_1);
		}

		return textView1;
	}

	public TextView getTextView2() {
		if (textView2 == null) {
			textView2 = new TextView(this);
			textView2.setText(TEXT_2);
		}

		return textView2;
	}

	public TextView getTextView3() {
		if (textView3 == null) {
			textView3 = new TextView(this);
			textView3.setText(TEXT_3);
		}

		return textView3;
	}
}
