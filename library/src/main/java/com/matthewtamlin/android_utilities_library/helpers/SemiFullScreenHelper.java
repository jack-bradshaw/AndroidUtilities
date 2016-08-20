/*
 * Copyright 2016 Matthew Tamlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matthewtamlin.android_utilities_library.helpers;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * A utility class for drawing behind the status bar.
 *
 * @deprecated use {@link StatusBarHelper} instead.
 */
@Deprecated
@SuppressWarnings("ALL") // It's deprecated, don't bother with warnings
@SuppressLint("ALL") // It's deprecated, don't bother with lint
public final class SemiFullScreenHelper {
	/**
	 * To prevent instantiation, this constructor is private and throws an exception when invoked.
	 */
	private SemiFullScreenHelper() {
		throw new UnsupportedOperationException("SemiFullScreenHelper cannot be instantiated");
	}

	/**
	 * Hides the status bar and allows views to draw behind it. If the API level is 21 or higher,
	 * the status bar will become entirely transparent. If the API level is 19 or 20, the status bar
	 * will become tinted. If the API level is less than 19, this method will have no effect. Note
	 * that views must still specify {@code android:fitsSystemWindows="false"} to draw under the
	 * status bar. This method is deprecated, use {@link StatusBarHelper#hideStatusBar(Window)}
	 * instead.
	 *
	 * @param window
	 * 		the {@code Window} for the calling app
	 */
	@Deprecated
	public static void setSemiFullScreen(final Window window) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			window.setStatusBarColor(Color.TRANSPARENT);
			window.getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		}
	}
}