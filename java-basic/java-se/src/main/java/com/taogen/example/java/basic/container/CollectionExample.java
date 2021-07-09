package com.taogen.example.java.basic.container;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Taogen
 */
public class CollectionExample {
    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> identityHashMap = new HashMap<>();
        identityHashMap.put("a", "a1");
        identityHashMap.put(new String("a"), "a1");
        System.out.println("a" == new String("a"));
        for (String s : identityHashMap.keySet()) {
            System.out.println("key: " + s + ", value: " + identityHashMap.get(s));
        }

        CopyOnWriteArrayList<Integer> linkedList = new CopyOnWriteArrayList<>();
        linkedList.add(1);
        linkedList.add(2);
        Iterator<Integer> iterator = linkedList.iterator();

        while (iterator.hasNext()) {
            int val = iterator.next();
            if (val == 1) {
                linkedList.add(3);
            }
        }
        System.out.println(linkedList);
    }


}
