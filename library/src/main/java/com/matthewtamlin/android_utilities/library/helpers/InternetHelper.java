package com.matthewtamlin.android_utilities.library.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.net.ConnectivityManager.TYPE_BLUETOOTH;
import static android.net.ConnectivityManager.TYPE_DUMMY;
import static android.net.ConnectivityManager.TYPE_ETHERNET;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_MOBILE_DUN;
import static android.net.ConnectivityManager.TYPE_VPN;
import static android.net.ConnectivityManager.TYPE_WIFI;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.*;
import static com.matthewtamlin.java_utilities.checkers.NullChecker.checkNotNull;

/**
 * Provides information about the current internet connection.
 */
public class InternetHelper {
	/**
	 * Checks if an internet connection is currently available and returns the type.
	 *
	 * @param context
	 * 		the querying context, not null
	 *
	 * @return the type of the current internet connection, null if there is none
	 */
	public static ConnectionType getInternetConnectionType(final Context context) {
		checkNotNull(context, "context cannot be null.");

		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return null;
		} else {
			switch (info.getType()) {
				case TYPE_MOBILE: return MOBILE;
				case TYPE_WIFI: return WIFI;
				case TYPE_BLUETOOTH: return BLUETOOTH;
				case TYPE_DUMMY: return MOCK;
				case TYPE_ETHERNET: return ETHERNET;
				case TYPE_MOBILE_DUN: return MOBILE_DUN;
			}
		}
	}

	/**
	 * @param context
	 * 		a context, not null
	 *
	 * @return the NetworkInfo for the supplied context
	 */
	private static NetworkInfo getNetworkInfo(final Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		return connectivityManager.getActiveNetworkInfo();
	}

	public enum ConnectionType {
		WIFI,
		MOBILE,
		BLUETOOTH,
		ETHERNET,
		MOCK,
		MOBILE_DUN,
		VPN,
		OTHER,
	}
}