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

package com.matthewtamlin.android_utilities_library;

import com.matthewtamlin.android_utilities_library.collections.Grouper;
import com.matthewtamlin.android_utilities_library.collections.KeyBasedGrouper;
import com.matthewtamlin.android_utilities_library.collections.KeyBasedGrouper.GroupKeyGenerator;
import com.matthewtamlin.android_utilities_library.collections.KeyBasedGrouper.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Unit tests for the {@link KeyBasedGrouper} class.
 */
@RunWith(JUnit4.class)
public class TestKeyBasedGrouper {
	/**
	 * Supplies new instances of {@code HashSet<String>}. This variable must be initialised before
	 * each test.
	 */
	private Supplier<HashSet<String>> stringHashSetSupplier;

	/**
	 * Supplies keys which group Strings by length. This variable must be initialised before each
	 * test.
	 */
	private GroupKeyGenerator<String> stringKeyGenerator;

	/**
	 * A Set of Strings, where each string is of length 4. This variable must be initialised before
	 * each test.
	 */
	private Set<String> fourLetterWords;

	/**
	 * A Set of Strings, where each string is of length 5. This variable must be initialised before
	 * each test.
	 */
	private Set<String> fiveLetterWords;

	/**
	 * A Set of Strings, where each string is of length 6. This variable must be initialised before
	 * each test.
	 */
	private Set<String> sixLetterWords;

	/**
	 * A Set of Strings, containing only null. This variable must be initialised before each test.
	 */
	private Set<String> nullWords;

	/**
	 * Contains all Strings contained in {@code fourLetterWords}, {@code fiveLetterWords} and {@code
	 * sixLetterWords}. This variable must be initialised before each test.
	 */
	private Set<String> allWords;

	/**
	 * Initialises the testing environment, and verifies that all preconditions are satisfied before
	 * testing begins.
	 */
	@Before
	public void setup() {
		fourLetterWords = new HashSet<>();
		fourLetterWords.add("Pear");
		fourLetterWords.add("Lime");

		fiveLetterWords = new HashSet<>();
		fiveLetterWords.add("Apple");
		fiveLetterWords.add("Grape");

		sixLetterWords = new HashSet<>();
		sixLetterWords.add("Orange");
		sixLetterWords.add("Banana");

		nullWords = new HashSet<>();
		nullWords.add(null);

		allWords = new HashSet<>();
		allWords.addAll(fourLetterWords);
		allWords.addAll(fiveLetterWords);
		allWords.addAll(sixLetterWords);
		allWords.addAll(nullWords);

		stringHashSetSupplier = new Supplier<HashSet<String>>() {
			@Override
			public HashSet<String> supplyNewInstance() {
				return new HashSet<>();
			}
		};

		stringKeyGenerator = new GroupKeyGenerator<String>() {
			@Override
			public Object getGroupKeyFor(String object) {
				return (object == null) ? -1 : object.length();
			}
		};
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code newGroupSupplier}
	 * argument of {@link KeyBasedGrouper#KeyBasedGrouper(Supplier, GroupKeyGenerator)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGroup_invalidArg_nullNewGroupSupplier() {
		new KeyBasedGrouper<>(null, stringKeyGenerator);
	}

	/**
	 * Test to verify that the correct exception is thrown when the {@code groupKeyGenerator}
	 * argument of {@link KeyBasedGrouper#KeyBasedGrouper(Supplier, GroupKeyGenerator)} is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGroup_invalidArg_nullGroupKeyGenerator() {
		new KeyBasedGrouper<String, Set<String>>(stringHashSetSupplier, null);
	}

	/**
	 * Test to verify that the {@link KeyBasedGrouper#group(Collection)} method functions correctly
	 * when provided with valid arguments. The test will only pass if KeyBasedGrouper correctly
	 * implements the Grouper interface.
	 */
	@Test
	public void testGroup_validArgs() {
		final Grouper<String, Set<String>> letterCountGrouper = new KeyBasedGrouper<String,
				Set<String>>(stringHashSetSupplier, stringKeyGenerator);

		final Set<Set<String>> groups = letterCountGrouper.group(allWords);

		assertThat("Incorrect number of groups returned.", groups.size(), is(4));
		assertThat("Four letter word group is missing.", groups.contains(fourLetterWords));
		assertThat("Five letter word group is missing.", groups.contains(fiveLetterWords));
		assertThat("Six letter word group is missing.", groups.contains(sixLetterWords));
		assertThat("Null word group is missing", groups.contains(nullWords));
		assertThat("There should be no empty group.", !groups.contains(new HashSet<String>()));
	}
}