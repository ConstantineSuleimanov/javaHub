package com.envy.collections.mylist;

import java.util.Collection;

public interface MyMutableList<T> extends MyList<T> {
    void add(T element);
    void addAll(Collection<T> collection);
    void remove(T element);




}
