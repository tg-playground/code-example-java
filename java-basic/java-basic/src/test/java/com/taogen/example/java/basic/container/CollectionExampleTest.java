package com.taogen.example.java.basic.container;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionExampleTest {

    @Test
    public void testHappenBefore() {
    }

    @Test
    public void testContainer() {

        HashMap<Colors, String> enumMap = new HashMap();
        enumMap.put(Colors.RED, "red");
        enumMap.put(Colors.BLUE, "blue");
        for (Colors colors : enumMap.keySet()) {
            System.out.println(enumMap.get(colors));
            if (colors.equals(Colors.BLUE)) {
                enumMap.put(Colors.RED, "yellow new");
            }
        }
        LinkedList linkedList = new LinkedList();
        linkedList.pop();
        ArrayDeque deque = new ArrayDeque();
        deque.poll();
        deque.pop();
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.poll();
        System.out.println(enumMap);

    }

    @Test
    public void testTraversal() {
        PriorityBlockingQueue<String> objects = new PriorityBlockingQueue();
//        objects.add(1);
//        objects.add(2);
//        objects.add(11);
//        objects.add(3);
//        objects.add(5);
//        objects.add(4);
//        Iterator<Integer> iterator = objects.iterator();
//        while (iterator.hasNext()){
//            int val = iterator.next();
//            System.out.print(val + "\t");
//            if (val == 1){
//                objects.remove(3);
//            }
//        }
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Spliterator<Integer> spliterator = list.spliterator();
//        System.out.println(spliterator.hasCharacteristics(Spliterator.SIZED));
//        int expected = Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
//        System.out.println(spliterator.characteristics() == expected);
//        spliterator.forEachRemaining(System.out::println);
        while (spliterator.tryAdvance(System.out::println)) ;


        Spliterator<Integer> spliterator2 = spliterator.trySplit();
        spliterator.forEachRemaining(System.out::println);
        System.out.println("======");
        spliterator2.forEachRemaining(System.out::println);

    }

    @Test
    public void testTemp() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Spliterator<Integer> spliterator = list.spliterator();
        Spliterator<Integer> newPartition = spliterator.trySplit();
        spliterator.forEachRemaining(System.out::println);
        System.out.println("======");
        newPartition.forEachRemaining(System.out::println);
    }

    @Test
    public void testStream() {
        System.out.println(Stream.empty().findAny().isPresent());
    }

    @Test
    public void testStreamTraversing() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 2, 1);
//        stream.sorted((o1, o2) -> {
//            return o1 - o2;
//        }).forEachOrdered(System.out::print);
//        stream.forEach(i -> System.out.println(i));
        System.out.println(stream.reduce(1, (i, j) -> {
            return i * j;
        }));
    }

    @Test
    public void testStreamFilter() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 1);
        System.out.println(stream.peek(System.out::println));
    }

    @Test
    public void testStreamMap() {
        List<String> letters = Arrays.asList("a", "b", "c");
        List<String> collect = letters.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testConsumer() {
        Function<Integer, Double> half = a -> a / 2.0;
        System.out.println(half.apply(10));
        Predicate<Integer> predicate = i -> {
            return i > 1;
        };
        System.out.println(predicate.test(1));
        Consumer consumer = System.out::println;
        consumer.accept("hello");
        Supplier supplier = () -> {
            return "hello Supplier";
        };
        System.out.println(supplier.get());
        assertTrue(testPredicate(2, i -> i > 1));
        assertFalse(testPredicate(1, i -> {
            return i > 1;
        }));
    }

    public boolean testPredicate(int val, Predicate<Integer> predicate) {
        return predicate.test(val);
    }

    @Test
    public void testOptional() {
        Integer i = 1;
        Optional optional = Optional.ofNullable(i);
        System.out.println(optional.isPresent());
//        System.out.println(optional.get());
        System.out.println(optional.orElse(2));
    }

    @Test
    public void testOptional2() {
//        Optional<List<Integer>> optional = Optional.ofNullable(new ArrayList<>());
//        System.out.println(optional.get());
        System.out.println(getNotNullValueOrDefault(1));
    }

    int getNotNullValueOrDefault(Integer obj) {
        int defaultValue = -1;
        Optional<Integer> optional = Optional.ofNullable(obj);
        return optional.orElse(defaultValue);
    }
}