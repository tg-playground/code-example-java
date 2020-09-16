package com.taogen.example.bestpractice.container;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Taogen
 */
public class CustomSortedContainer {
    Set<String> stringSet = new TreeSet<String>(
            new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

}
