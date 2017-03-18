package com.matthewtamlin.android_utilities.library.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * Provides information about the current internet connection.
 */
public class InternetHelper {
	public static boolean connectedToInternet(final Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		if (activeNetworkInfo == null) {
			return false;
		} else {
			return activeNetworkInfo.isConnected();
		}
	}

	public static boolean connectedToWifi(final Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

		if (activeNetworkInfo == null) {
			return false;
		} else {
			return activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == TYPE_WIFI;
		}
	}
}