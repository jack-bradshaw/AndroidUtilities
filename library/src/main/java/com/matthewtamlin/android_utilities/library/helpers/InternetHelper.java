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
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.BLUETOOTH;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.ETHERNET;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.MOBILE;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.MOBILE_DUN;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.MOCK;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.UNKNOWN;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.VPN;
import static com.matthewtamlin.android_utilities.library.helpers.InternetHelper.ConnectionType.WIFI;
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
				case TYPE_MOBILE:
					return MOBILE;
				case TYPE_WIFI:
					return WIFI;
				case TYPE_BLUETOOTH:
					return BLUETOOTH;
				case TYPE_DUMMY:
					return MOCK;
				case TYPE_ETHERNET:
					return ETHERNET;
				case TYPE_MOBILE_DUN:
					return MOBILE_DUN;
				case TYPE_VPN:
					return VPN;

				default:
					return UNKNOWN;
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

	/**
	 * The different internet connection types available to an Android device.
	 */
	public enum ConnectionType {
		/**
		 * The device is routing all traffic through a WIFI network.
		 */
		WIFI,

		/**
		 * The device is routing all traffic through the mobile data connection.
		 */
		MOBILE,

		/**
		 * The device is routing all traffic through a bluetooth connection.
		 */
		BLUETOOTH,

		/**
		 * The device is routing all traffic through an ethernet connection.
		 */
		ETHERNET,

		/**
		 * The internet connection is being mocked at a low level. <b>Not for use in production.</b>
		 */
		MOCK,

		/**
		 * The device is routing all traffic through a dial up mobile data connection.
		 */
		MOBILE_DUN,

		/**
		 * The device is routing all traffic through a VPN. The actual nature of the connection is
		 * obscured.
		 */
		VPN,

		/**
		 * The device has an active internet connection, but its nature is unknown.
		 */
		UNKNOWN,
	}
}