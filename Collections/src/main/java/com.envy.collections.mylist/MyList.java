package com.envy.collections.mylist;

public interface MyList<T> {
    T get(int index);
    boolean contains(T element);
    int size();
}
