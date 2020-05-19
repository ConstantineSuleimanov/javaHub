package com.envy.collections.mylist;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    @Test
    public void interfaceSegregationTest(){
//        By default we can't modify list
        MyList<Integer> list = generateTestList();
        assertTrue(list.contains(1));
        assertEquals(9, list.size());
        assertEquals(1, (int) list.get(0));


        MyMutableList<Integer> myMutableList = (MyMutableList) list;
        myMutableList.add(15);
        assertTrue(myMutableList.contains(15));

    }

    private MyList<Integer> generateTestList() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        return linkedList;
    }

}