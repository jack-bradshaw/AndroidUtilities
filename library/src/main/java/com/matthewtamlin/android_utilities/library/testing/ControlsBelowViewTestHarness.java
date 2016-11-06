package com.matthewtamlin.android_utilities.library.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.matthewtamlin.android_utilities.library.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A TestHarness which displays control buttons below the test view.
 *
 * @param <V>
 * 		the type of view being tested
 */
public abstract class ControlsBelowViewTestHarness<V> extends TestHarness<V, FrameLayout> {
	@Override
	protected void onCreate(final @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controlsbelowview);
		getTestViewContainer().addView((View) getTestView());
		initialiseControlHiding();
	}

	@Override
	public View getRootView() {
		return findViewById(R.id.controlsBelowView_root);
	}

	@Override
	public LinearLayout getControlButtonContainer() {
		return (LinearLayout) findViewById(R.id.controlsBelowView_controlsContainer);
	}

	@Override
	public FrameLayout getTestViewContainer() {
		return (FrameLayout) findViewById(R.id.controlsBelowView_testViewContainer);
	}

	/**
	 * Configures a button to hide/show the controls when clicked.
	 */
	private void initialiseControlHiding() {
		final Button toggleControlVisibilityButton = (Button) findViewById(R.id
				.controlsBelowView_hideShowControlsButton);
		final LinearLayout controlButtonContainer = (LinearLayout) findViewById(R.id
				.controlsBelowView_controlsContainer);

		toggleControlVisibilityButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				controlButtonContainer.setVisibility(controlButtonContainer.getVisibility() ==
						VISIBLE ? GONE : VISIBLE);
			}
		});
	}
}