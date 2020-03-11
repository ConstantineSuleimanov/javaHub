package com.envy.collections.lists.arrayList;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ArrayListTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void arrayListCopyConstructorTest(){
        ArrayList<Integer> arrayList = generateTestArrayList();
        ArrayList<Integer> arrayListCopy = new ArrayList<>(arrayList);

        arrayList.removeAll(generateTestArrayList(5));

        assertEquals(5, arrayList.size());
        assertEquals(10, arrayListCopy.size());
    }

    @Test
    public void arrayListShouldIncrementInitialCapacity(){
        int initialCapacity = 2;
        ArrayList<Integer> list = generateTestArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertTrue(initialCapacity < list.size());
    }

    /**
     * For iterating through elements ArrayList used iterator.
     * Every time when it.next() called there is check that size of array has not been changed
     */
    @Test(expected = ConcurrentModificationException.class)
    public void elementsOfArrayListCanNotBeRemovedThroughLoop(){
        ArrayList<Integer> list = generateTestArrayList();
        for (Integer integer : list) {
            if (integer == 5){
                list.remove(integer);
            }
        }
    }

    @Test
    public void elementsOfArrayListCanBeRemovedThroughIterator(){
        ArrayList<Integer> list = generateTestArrayList();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if ( i == 5){
                iterator.remove();
            }
        }
    }

    @Test
    public void findingElement(){
        ArrayList<Integer> list = generateTestArrayList();
        boolean contains = list.contains(5);
        assertTrue(contains);
    }

    @Test
    public void filtering(){
        ArrayList<Integer> list = generateTestArrayList();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        assertFalse(list.contains(2));
        assertFalse(list.contains(4));
        assertFalse(list.contains(6));
        assertFalse(list.contains(8));
    }

    @Test
    public void filteringWithCreationNewCollection(){
        ArrayList<Integer> list = generateTestArrayList();
        ArrayList<Integer> newList = new ArrayList<>(list.size());
        for (Integer integer : list) {
            if (integer % 2 != 0 ) {
                newList.add(integer);
            }
        }

        assertFalse(newList.contains(2));
        assertFalse(newList.contains(4));
        assertFalse(newList.contains(6));
        assertFalse(newList.contains(8));

        assertTrue(list.contains(2));
        assertTrue(list.contains(4));
        assertTrue(list.contains(6));
        assertTrue(list.contains(8));
    }

    private ArrayList<Integer> generateTestArrayList() {
        return generateTestArrayList(10);
    }

    private ArrayList<Integer> generateTestArrayList(int capacity) {
        return IntStream.range(0, capacity).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}