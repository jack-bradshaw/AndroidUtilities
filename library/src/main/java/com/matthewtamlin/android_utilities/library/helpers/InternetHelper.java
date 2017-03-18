package com.matthewtamlin.android_utilities.library.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;
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
			return false;
		} else {
			return info.isConnected();
		}
	}

	/**
	 * Checks if an internet connection is currently available via wifi.
	 *
	 * @param context
	 * 		the querying context, not null
	 *
	 * @return true if an internet connection is currently available via wifi, false otherwise
	 */
	public static boolean usingWifi(final Context context) {
		checkNotNull(context, "context cannot be null.");

		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return false;
		} else {
			return info.isConnected() && info.getType() == TYPE_WIFI;
		}
	}

	/**
	 * Checks if an internet connection is currently available via mobile data.
	 *
	 * @param context
	 * 		the querying context, not null
	 *
	 * @return true if an internet connection is currently available via mobile data, false
	 * otherwise
	 */
	public static boolean usingMobileData(final Context context) {
		checkNotNull(context, "context cannot be null.");

		final NetworkInfo info = getNetworkInfo(context);

		if (info == null) {
			return false;
		} else {
			return info.isConnected() && info.getType() == TYPE_MOBILE;
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
		OTHER,
	}
}