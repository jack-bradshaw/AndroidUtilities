package com.matthewtamlin.android_utilities_library.utilities;

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