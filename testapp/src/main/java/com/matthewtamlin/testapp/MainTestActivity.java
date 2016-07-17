package com.matthewtamlin.testapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainTestActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_selector);
	}

	public void testStatusBarHelper(final View v) {
		final Intent i = new Intent(this, TestStatusBarHelper.class);
		startActivity(i);
	}

	public void runScriptedTests(final View v) {
		TestOutcome assetHelperTestScriptOutcome = AssetHelperTestScript.runTest(this);
		Log.d(TAG, "[Test ")
	}
}
