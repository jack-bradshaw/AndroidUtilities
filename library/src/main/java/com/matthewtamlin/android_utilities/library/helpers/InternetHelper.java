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
		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return false;
		} else {
			return info.isConnected();
		}
	}

	public static boolean connectedToWifi(final Context context) {
		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return false;
		} else {
			return info.isConnected() && info.getType() == TYPE_WIFI;
		}
	}

	public static boolean connectedToMobileData(final Context context) {
		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return false;
		} else {
			return info.isConnected() && info.getType() == TYPE_WIFI;
		}
	}

	private static NetworkInfo getNetworkInfo(final Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		return connectivityManager.getActiveNetworkInfo();
	}
}