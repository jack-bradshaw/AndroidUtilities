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

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;

/**
 * Helper class for obtaining and abandoning audio focus.
 */
public class AudioFocusHelper {
	/**
	 * Requests stream alarm audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamAlarmFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_ALARM);
	}

	/**
	 * Requests stream DTMF audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamDtmfFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_DTMF);
	}

	/**
	 * Requests stream notification audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamNotificationFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_NOTIFICATION);
	}

	/**
	 * Requests stream music audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamMusicFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_MUSIC);
	}

	/**
	 * Requests stream ring audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamRingFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_RING);
	}

	/**
	 * Requests stream system audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamSystemFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_SYSTEM);
	}

	/**
	 * Requests stream voice call audio-focus for the supplied listener.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener to receive the audio focus, not null
	 * @return true if audio focus is granted, false otherwise
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static boolean requestStreamVoiceCallFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		return requestStreamAudioFocus(context, listener, AudioManager.STREAM_VOICE_CALL);
	}

	/**
	 * Abandons audio focus.
	 *
	 * @param context
	 * 		the Context in which {@code listener} is operating, not null
	 * @param listener
	 * 		the OnAudioFocusChangeListener which will lose audio focus, not null
	 * @throws IllegalArgumentException
	 * 		if {@code context} or {@code listener} is null
	 */
	public static void abandonFocus(final Context context,
			final OnAudioFocusChangeListener listener) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (listener == null) {
			throw new IllegalArgumentException("listener cannot be null");
		}

		final AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		am.abandonAudioFocus(listener);
	}

	private static boolean requestStreamAudioFocus(final Context context, final
			OnAudioFocusChangeListener listener, final int streamType) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null");
		} else if (listener == null) {
			throw new IllegalArgumentException("listener cannot be null");
		}

		final AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		final int result = am.requestAudioFocus(listener, streamType, AudioManager.AUDIOFOCUS_GAIN);
		return (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED);
	}
}