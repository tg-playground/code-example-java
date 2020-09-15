package com.taogen.example.bestpractice.variables;

import java.util.*;

import static java.util.Map.entry;

/**
 * @author Taogen
 */
public class ConstantDeclarationAndInitialization {
    public static final int NUM = 1;

    public static final int[] MODIFIABLE_ARRAY = {1, 2, 3};

    /**
     * JDK 8 (Don't expose internal_list reference of unmodifiableList(List internale_list)). Arrays.asList() can't increase size, but it can modify its elements.
     */
    public static final List UNMODIFIABLE_LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    /**
     * JDK 9 (Recommend, less space cost)
     */
    public static final List UNMODIFIABLE_LIST_2 = List.of("a", "b", "c");

    /**
     * JDK 8
     */
    public static final Set<String> UNMODIFIABLE_SET = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c")));

    /**
     * JDK 9 (Recommend)
     */
    public static final Set<String> UNMODIFIABLE_SET_2 = Set.of("a", "b", "c");

    /**
     * Immutable Map, JDK 8
     */
    public static final Map<Integer, String> UNMODIFIABLE_MAP = Collections.unmodifiableMap(
            new HashMap<Integer, String>() {
                {
                    put(1, "one");
                    put(2, "two");
                    put(3, "three");
                }
            });

    /**
     * java 9, return ImmutableCollections (Recommend)
     */
    public static final Map<Integer, String> UNMODIFIABLE_MAP_2 = Map.of(1, "one", 2, "two");

    /**
     * java 10, return ImmutableCollections (Recommend)
     */
    public static final Map<Integer, String> UNMODIFIABLE_MAP_3 = Map.ofEntries(
            entry(1, "One"),
            entry(2, "two"),
            entry(3, "three"));

}
