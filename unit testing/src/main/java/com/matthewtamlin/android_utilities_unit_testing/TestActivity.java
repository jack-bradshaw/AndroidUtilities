package com.matthewtamlin.android_utilities_unit_testing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

/**
 * An Activity for use in instrumented unit testing.
 */
public class TestActivity extends AppCompatActivity {
	@Override
	protected final void onCreate(final Bundle savedInstanceState) {
		setTheme(R.style.TestTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}

	/**
	 * @return the root view of the activity, null if {@link #onCreate(Bundle)} has not yet run
	 */
	public LinearLayout getRootView() {
		return (LinearLayout) findViewById(R.id.activity_test_root);
	}
}