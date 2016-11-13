package com.matthewtamlin.android_utilities.library.utilities;

import android.os.Handler;
import android.os.Looper;


public class MainLooperUiThreadUtil {
	public void runOnUiThread(final Runnable runnable) {
		final Handler handler = new Handler(Looper.getMainLooper());
		handler.post(runnable);
	}
}
