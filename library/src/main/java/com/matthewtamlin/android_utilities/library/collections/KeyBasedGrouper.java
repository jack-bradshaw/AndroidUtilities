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

package com.matthewtamlin.android_utilities.library.collections;

import com.matthewtamlin.android_utilities.library.testing.Tested;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of the Grouper interface which can be configured by passing a {@link
 * Supplier} and an {@link GroupKeyGenerator} to the constructor.
 *
 * @param <I>
 * 		the type of Object to group
 * @param <O>
 * 		the type to use for the groups
 */
@Tested(testMethod = "automated", requiresInstrumentation = false)
public final class KeyBasedGrouper<I, O extends Collection<? super I>> implements Grouper<I, O> {
	/**
	 * Supplies a new group when needed.
	 */
	private final Supplier<? extends O> newGroupSupplier;

	/**
	 * Supplies the keys which indicate which group to place each item in.
	 */
	private final GroupKeyGenerator<I> groupKeyGenerator;

	/**
	 * Constructs a new KeyBasedGrouper.
	 *
	 * @param newGroupSupplier
	 * 		supplied new groups as they are needed, not null
	 * @param groupKeyGenerator
	 * 		identifies which group to place each item in, not null
	 * @throws IllegalArgumentException
	 * 		if {@code newGroupSupplier} or {@code groupKeyGenerator} is null
	 */
	public KeyBasedGrouper(final Supplier<? extends O> newGroupSupplier,
			final GroupKeyGenerator<I> groupKeyGenerator) {
		if (newGroupSupplier == null) {
			throw new IllegalArgumentException("newGroupSupplier cannot be null");
		} else if (groupKeyGenerator == null) {
			throw new IllegalArgumentException("groupKeyGenerator cannot be null");
		}

		this.newGroupSupplier = newGroupSupplier;
		this.groupKeyGenerator = groupKeyGenerator;
	}

	@Override
	public Set<O> group(final Collection<? extends I> items) {
		final Map<Object, O> groups = new HashMap<>();

		for (final I libraryItem : items) {
			final Object groupKey = groupKeyGenerator.getGroupKeyFor(libraryItem);

			if (!groups.containsKey(groupKey)) {
				groups.put(groupKey, newGroupSupplier.supplyNewInstance());
			}

			groups.get(groupKey).add(libraryItem);
		}

		return new HashSet<>(groups.values());
	}

	/**
	 * Supplies new instances of the specified type.
	 *
	 * @param <T>
	 * 		the type supplied by this Supplier
	 */
	public interface Supplier<T> {
		/**
		 * Constructs a new T.
		 *
		 * @return a new T, not null
		 */
		T supplyNewInstance();
	}

	/**
	 * Generates group keys. An Object's group key uniquely identifies the group it belongs to.
	 * Group keys have the following properties: <ul><li>A group key can be any Object.</li><li>If
	 * two Objects are in the same group, then their group keys must be equal.</li><li>If two
	 * Objects are not in the same group, then their group keys must not be equal.</li><li>Group
	 * keys are always compared using the {@link Object#equals(Object)} method.</li></ul>
	 *
	 * @param <T>
	 * 		the type of Object being grouped
	 */
	public interface GroupKeyGenerator<T> {
		/**
		 * @param object
		 * 		the Object to get the group key for, null accepted
		 * @return the group key for the supplied object
		 */
		Object getGroupKeyFor(T object);
	}
}