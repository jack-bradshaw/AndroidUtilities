package com.matthewtamlin.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TestSelectorActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_selector);
	}

	public void testStatusBarHelper(View v) {
		final Intent i = new Intent(this, TestStatusBarHelper.class);
		startActivity(i);
	}
}
