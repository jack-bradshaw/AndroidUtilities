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

package com.matthewtamlin.android_utilities_unit_testing;

import android.content.Context;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.matthewtamlin.android_utilities_library.helpers.AudioFocusHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AudioFocusHelper} class.
 * <p/>
 * Unit testing is not able to fully cover the {@link AudioFocusHelper#abandonFocus(Context,
 * OnAudioFocusChangeListener)} method, because Google's implementation of the AudioManager fails to
 * deliver callbacks to the correct OnAudioFocusChangeListener when multiple listeners are used
 * concurrently.
 */
@RunWith(AndroidJUnit4.class)
public class TestAudioFocusHelper {
	/**
	 * Provides access to the Android system resources needed to run the tests.
	 */
	private Context context;

	/**
	 * The target listener for use during testing.
	 */
	private OnAudioFocusChangeListener listener;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 */
	@Before
	public void setup() {
		context = InstrumentationRegistry.getTargetContext();

		// Mockito cannot mock this interface, so just use an instance that does nothing when called
		listener = new OnAudioFocusChangeListener() {
			@Override
			public void onAudioFocusChange(int i) {
				// Do nothing
			}
		};

		assertThat("Precondition 1 failed. Context is null.", context != null);
		assertThat("Precondition 2 failed. Listener is null.", listener != null);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link AudioFocusHelper#requestStreamAlarmFocus(Context, OnAudioFocusChangeListener)} is
	 * null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRequestStreamAlarmFocus_invalidArg_nullContext() {
		AudioFocusHelper.requestStreamAlarmFocus(null, listener);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code listener} argument of
	 * {@link AudioFocusHelper#requestStreamAlarmFocus(Context, OnAudioFocusChangeListener)} is
	 * null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRequestStreamAlarmFocus_invalidArg_nullListener() {
		AudioFocusHelper.requestStreamAlarmFocus(context, null);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamAlarmFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamAlarmFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamAlarmFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamDtmfFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamDtmfFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamDtmfFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamNotificationFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamNotificationFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamNotificationFocus(context,
				listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamMusicFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamMusicFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamMusicFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamRingFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamRingFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamRingFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamSystemFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamSystemFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamSystemFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the {@link AudioFocusHelper#requestStreamVoiceCallFocus(Context,
	 * OnAudioFocusChangeListener)} method functions correctly when provided with valid arguments.
	 */
	@Test
	public void testRequestStreamVoiceCallFocus_validArgs() {
		boolean audioFocusGranted = AudioFocusHelper.requestStreamVoiceCallFocus(context, listener);
		assertThat("Focus was not granted.", audioFocusGranted);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code context} argument of
	 * {@link AudioFocusHelper#abandonFocus(Context, OnAudioFocusChangeListener)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAbandonFocus_invalidArg_nullContext() {
		AudioFocusHelper.abandonFocus(null, listener);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code listener} argument of
	 * {@link AudioFocusHelper#abandonFocus(Context, OnAudioFocusChangeListener)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAbandonFocus_invalidArg_nullListener() {
		AudioFocusHelper.abandonFocus(context, null);
	}
}