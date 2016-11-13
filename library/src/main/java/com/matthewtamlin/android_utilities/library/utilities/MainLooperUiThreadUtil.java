package com.matthewtamlin.android_utilities.library.utilities;

import android.os.Handler;
import android.os.Looper;

import com.matthewtamlin.android_utilities.library.checkers.NullChecker;

/**
 * Provides access to the UI thread using the main looper.
 */
public class MainLooperUiThreadUtil implements UiThreadUtil {
	@Override
	public void runOnUiThread(final Runnable runnable) {
		if (runnable != null) {
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.post(runnable);
		}
	}
}