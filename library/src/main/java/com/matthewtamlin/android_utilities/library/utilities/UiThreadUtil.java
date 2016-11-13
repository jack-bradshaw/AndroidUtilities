package com.matthewtamlin.android_utilities.library.utilities;

/**
 * Provides access to the UI thread.
 */
public interface UiThreadUtil {
	/**
	 * Runs the supplied runnable on the UI thread of the current app.
	 *
	 * @param runnable
	 * 		the runnable to execute, may be null
	 */
	public void runOnUiThread(Runnable runnable);
}
