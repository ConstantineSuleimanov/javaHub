package com.envy.collections.lists.arrayList;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SetTest {

    @Test
    public void addElements(){
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(1);
        assertEquals(1, set.size());

        set.addAll(getTestSet());
        assertEquals(10, set.size());
    }

    @Test
    public void removeElements(){
        Set<Integer> testSet = getTestSet();
        testSet.remove(1);
        assertFalse(testSet.contains(1));

        testSet.removeAll(getTestSet(5));
        assertEquals(4, testSet.size());
    }

    @Test
    public void findElements(){
        Set<Integer> testSet = getTestSet();
        assertTrue(testSet.contains(5));
        assertTrue(testSet.containsAll(getTestSet(5)));
    }

    @Test
    public void filterElements(){
        Set<Integer> testSet = getTestSet();
        Iterator<Integer> iterator = testSet.iterator();
        while ( iterator.hasNext()){
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }
        }

        assertEquals(5, testSet.size());

        testSet.removeIf(next -> next % 2 == 1);
        assertEquals(0, testSet.size());
    }

    private Set<Integer> getTestSet() {
        return getTestSet(10);
    }

    private Set<Integer> getTestSet(int size) {
        return IntStream.range(0, 10)
                .collect(HashSet::new, HashSet<Integer>::add, HashSet<Integer>::addAll);
    }
}
