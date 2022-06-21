package ru.job4j.collection;

import java.util.Iterator;

public interface LinkedList<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
