package com.taogen.example.bestpractice.statements;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class ForLoopWithStream {
    public void test(List<String> stringList){
        String result = stringList.stream()
                .filter(i -> i.length() > 5)
                .map(i -> i.toLowerCase())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining("❤"));
        System.out.println(result);
    }
}
