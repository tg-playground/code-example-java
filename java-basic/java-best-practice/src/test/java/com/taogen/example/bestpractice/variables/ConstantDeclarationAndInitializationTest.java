package com.taogen.example.bestpractice.variables;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConstantDeclarationAndInitializationTest {
    ConstantDeclarationAndInitialization constantObj = new ConstantDeclarationAndInitialization();

    @Test
    public void testArray() {
        int[] arr = constantObj.MODIFIABLE_ARRAY;
        arr[0] = 1;
    }

    @Test
    public void testArrayList() {
        List<Integer> list = constantObj.UNMODIFIABLE_LIST;
        try {
            list.set(1, 123);
        } catch (UnsupportedOperationException e) {
        }

        list = constantObj.UNMODIFIABLE_LIST_2;
        try {
            list.set(1, 123);
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testMap() {
        Map<Integer, String> map = constantObj.UNMODIFIABLE_MAP;
        try {
            map.put(1, "hello");
        } catch (UnsupportedOperationException e) {
        }

        map = constantObj.UNMODIFIABLE_MAP_2;
        try {
            map.put(1, "hello2");
        } catch (UnsupportedOperationException e) {
        }

        map = constantObj.UNMODIFIABLE_MAP_3;
        try {
            map.put(1, "hello3");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testSet() {
        Set<String> set = constantObj.UNMODIFIABLE_SET;
        try {
            set.add("hello-set");
        } catch (UnsupportedOperationException e) {
        }
        set = constantObj.UNMODIFIABLE_SET_2;
        try {
            set.add("hello-set2");
        } catch (UnsupportedOperationException e) {
        }
    }
}