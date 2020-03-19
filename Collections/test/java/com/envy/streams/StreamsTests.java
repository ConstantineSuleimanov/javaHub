package com.envy.streams;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamsTests {
    @Test
    public void creatingStreams() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream1 = Stream.of(new Integer[]{1, 3, 4, 5, 6});

        List<Integer> integers = new LinkedList<>();
        Stream<Integer> stream2 = integers.stream();

        Stream<Object> stream3 = Stream.generate(Object::new);
    }

    @Test
    public void mapTest() {
        Stream<String> stringStream = IntStream.range(0, 100)
                .map(integer -> integer + 1)
                .map(Math::abs)
                .mapToObj(Integer::toString);
    }

    @Test
    public void flatMapTest() {
        IntStream.range(0, 1000)
                .mapToObj(Integer::toString)
                .flatMap(string -> Arrays.stream(string.split("0")))
                .forEach(System.out::println);
    }

    @Test
    public void filterTest() {
        IntStream.range(0, 1000)
                .filter(integer -> integer % 2 == 0)
                .filter(integer -> Math.sin(integer) > 0.5)
                .toArray();
    }


    @Test
    public void sortingTest() {
        IntStream.range(0, 1000)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .sorted((integer1, integer2) -> integer2 - integer1)
                .collect(Collectors.toList());
    }

    @Test
    public void streamTerminalOperations() {
//        used to store elements of the stream as collection
        List<Integer> collect = IntStream.range(0, 1000).boxed()
                .collect(Collectors.toList());

//        also can be used to store custom collection
        IntStream.range(0, 1000).boxed()
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

//        Used when we should perform some operation for each element of the stream
        IntStream.range(0, 10).boxed()
                .forEach(System.out::println);

//        allMatch, anyMatch, noneMatch used to check whether a certain predicate matches the stream
        boolean result = IntStream.range(0, 1000).boxed()
                .allMatch(integer -> integer > 50);
        assertFalse(result);

        result = IntStream.range(0, 1000).boxed()
                .anyMatch(integer -> integer > 50);
        assertTrue(result);

        result = IntStream.range(0, 1000).boxed()
                .noneMatch(integer -> integer > 50);
        assertFalse(result);

//        count() returning number of elements of the stream
        IntStream.range(0, 1000).count();


        OptionalInt sum = IntStream.range(0, 100)
                .reduce((i1, i2) -> i1 + i2);
        assertTrue(sum.isPresent());
    }

}
