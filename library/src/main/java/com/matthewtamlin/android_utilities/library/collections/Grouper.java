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

import java.util.Collection;
import java.util.Set;

/**
 * Groups the contents of a single Collection into multiple Collections. The grouping must have a
 * 1:1 mapping, such that every grouped element is present in exactly one group.
 *
 * @param <I>
 * 		the type of Object to group
 * @param <O>
 * 		the type to use for the groups
 */
public interface Grouper<I, O extends Collection<? super I>> {
	/**
	 * Groups the contents of the provided Collection. Each element in the input collection will be
	 * present in exactly one output group. No empty groups will be present in the output.
	 *
	 * @param items
	 * 		the items to group, not null
	 * @return the groups, not null
	 */
	Set<O> group(Collection<? extends I> items);
}