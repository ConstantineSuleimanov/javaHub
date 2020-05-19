package com.envy.collections.mylist;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Implementation of Interface segregation pattern.
 *
 * @param <T>
 */
public class MyLinkedList<T> implements MyList<T>, MyMutableList<T> {
    private LinkedList<T> list;

    public MyLinkedList() {
        list = new LinkedList<T>();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void addAll(Collection<T> collection) {
        list.addAll(collection);
    }

    @Override
    public void remove(T element) {
        list.remove(element);
    }
}
