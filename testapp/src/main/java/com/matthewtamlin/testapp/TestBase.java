package com.matthewtamlin.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public abstract class TestBase extends AppCompatActivity {
	private LinearLayout rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_base);
		rootView = (LinearLayout) findViewById(R.id.activity_testBase_root);
	}

	protected LinearLayout getRootView() {
		return rootView;
	}
}
