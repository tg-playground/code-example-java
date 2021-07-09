package com.taogen.example.java.basic.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Taogen
 */
public class GenericExample {

    public static void main(String[] args) {
        printAnyTypesOfList(new ArrayList<Integer>(Arrays.asList(1, 2)));
    }

    public static void printAnyTypesOfList(List<?> list) {
        for (Object elem : list){
            System.out.println(elem);
        }
    }
}
