package com.taogen.example.bestpractice.variables;

import java.util.*;

/**
 * @author Taogen
 */
public class VariableDeclarationAndInitialization {
    private int number = 1;

    private String str = "hello";

    private int[] myArray = {1, 2, 3};

    private Object myObject = new Object();

    private List<String> myList = new ArrayList(Arrays.asList(1, 2, 3));

    private Set<String> mySet = new HashSet<>(Arrays.asList("a", "b", "c"));

    private Map<Integer, String> myMap = new HashMap<Integer, String>() {
        {
            put(1, "one");
            put(2, "two");
            put(3, "three");
        }
    };


}
