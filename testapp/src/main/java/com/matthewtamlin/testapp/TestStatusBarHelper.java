package com.matthewtamlin.testapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.matthewtamlin.android_utilities_library.helpers.StatusBarHelper;

/**
 * Activity for testing the functionality of the StatusBarHelper class.
 */
@SuppressLint("SetTextI18n")
public class TestStatusBarHelper extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createTriggerButtons();
	}

	private void createTriggerButtons() {
		final Button hideStatusBar = new Button(this);
		hideStatusBar.setText("Hide status bar");
		hideStatusBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				StatusBarHelper.hideStatusBar(getWindow());
			}
		});

		final Button showStatusBar = new Button(this);
		showStatusBar.setText("Show status bar");
		showStatusBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				StatusBarHelper.hideStatusBar(getWindow());
			}
		});
	}
}
