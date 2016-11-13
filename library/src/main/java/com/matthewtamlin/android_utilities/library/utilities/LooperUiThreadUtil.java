package com.matthewtamlin.android_utilities.library.utilities;

import android.os.Handler;
import android.os.Looper;

import com.matthewtamlin.android_utilities.library.checkers.NullChecker;

/**
 * Provides access to the UI thread using a Looper.
 */
public class LooperUiThreadUtil implements UiThreadUtil {
	private final Looper looper;

	/**
	 * Constructs a new LooperUiThreadUtil which uses the supplied looper.
	 *
	 * @param looper
	 * 		the looper to use, not null
	 * @return the new LooperUiThreadUtil, not null
	 * @throws IllegalArgumentException
	 * 		if {@code looper} is null
	 */
	public static LooperUiThreadUtil createUsingLooper(final Looper looper) {
		return new LooperUiThreadUtil(looper);
	}

	/**
	 * Constructs a new LooperUiThreadUtil which uses the main looper of the calling application.
	 *
	 * @return the new LooperUiThreadUtil, not null
	 */
	public static LooperUiThreadUtil createUsingMainLooper() {
		return new LooperUiThreadUtil(Looper.getMainLooper());
	}

	/**
	 * Constructs a new LooperUiThreadUtil.
	 *
	 * @param looper
	 * 		the looper to use, not null
	 */
	public LooperUiThreadUtil(final Looper looper) {
		this.looper = NullChecker.checkNonNull(looper, "looper cannot be null");
	}

	@Override
	public void runOnUiThread(final Runnable runnable) {
		if (runnable != null) {
			final Handler handler = new Handler(Looper.getMainLooper());
			handler.post(runnable);
		}
	}
}