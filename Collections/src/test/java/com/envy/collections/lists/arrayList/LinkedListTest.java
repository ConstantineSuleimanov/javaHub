package com.envy.collections.lists.arrayList;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LinkedListTest {

    @Test
    public void linkedListConstructorsTest() {
//        Default constructor. Construct an empty list;
        LinkedList<Integer> emptyList = new LinkedList<>();
        assertEquals(0, emptyList.size());

        /*
          Constructs a list containing the elements of the specified
          collection, in the order they are returned by the collection's
          iterator.
         */
        int elementsCount = 10;
        LinkedList<Integer> copiedList = new LinkedList<>(generateTestLinkedList(elementsCount));
        assertEquals(elementsCount, copiedList.size());
    }

    @Test
    public void linkedListAddingElements() {
        LinkedList<Integer> list = new LinkedList();

//        Default adding operation. Append new element to the and of the list
        list.add(1);
        list.add(2);
        list.add(3);

       /*
         Adding element to a specific index.
       */
        list.add(2, 4);
        list.add(2, 5);
        assertEquals(5, list.size());

        /*
          Also is possible to add element on the last position.
        */
        list.add(5, 6);
        assertEquals(6, list.size());


//        Add all items from the specific collection.
        list.addAll(generateTestLinkedList(3));

//        Add all items from the specific collection on the specific index
        list.addAll(5, generateTestLinkedList(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void linkedListAddingElementsExceptionsTest() {
        /*
         If we try to add element to list on not existing index we will catch exception
        */
        LinkedList<Integer> list = generateTestLinkedList(5);
        list.add(7, 7);
    }

    /**
     * Removing elements from LinkedList
     * Removing through foreach loop will throw ConcurrentModificationException like ArrayList
     */
    @Test
    public void linkedListRemovingElements() {
        LinkedList<Integer> linkedList = generateTestLinkedList(10);

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer % 2 == 0) {
                iterator.remove();
            }
        }
        assertEquals(5, linkedList.size());

//        Since java 1.8 we can use removeIf method
        linkedList.removeIf(integer -> integer < 2);
        assertEquals(4, linkedList.size());
    }

    /**
     * We can check if element or collection contains in LinkedList. Order is not matter.
     */
    @Test
    public void findingElementsInLinkedList() {
        LinkedList<Integer> list = generateTestLinkedList();
        assertTrue(list.contains(5));
        List<Integer> integers = generateTestLinkedList(5)
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        assertTrue(list.containsAll(integers));
    }

    private LinkedList<Integer> generateTestLinkedList() {
        return generateTestLinkedList(10);
    }

    private LinkedList<Integer> generateTestLinkedList(int capacity) {
        return IntStream.range(0, capacity).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
    }
}
