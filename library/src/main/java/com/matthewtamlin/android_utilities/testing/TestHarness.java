package com.matthewtamlin.android_utilities.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * An activity which hosts a view to be tested, as well as buttons for interacting with the view.
 *
 * @param <V>
 * 		the type of view being tested
 * @param <C>
 * 		the type of view which contains the test view
 */
public abstract class TestHarness<V, C> extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * @return the root view of this Activity's layout, not null
	 */
	public abstract View getRootView();

	/**
	 * @return the view which contains the control buttons, not null
	 */
	public abstract LinearLayout getControlButtonContainer();

	/**
	 * @return the view under test, not null
	 */
	public abstract V getTestView();

	/**
	 * @return the view which contains the test view
	 */
	public abstract C getTestViewContainer();
}