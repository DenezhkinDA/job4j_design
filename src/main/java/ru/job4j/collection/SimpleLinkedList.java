package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> lastAdd = last;
        Node<E> newNode = new Node<>(value, lastAdd, null);
        last = newNode;
        if (lastAdd == null) {
            first = newNode;
        } else {
            lastAdd.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 1; i <= index; i++) {
            node = first.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> eNode = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return eNode != null;
            }

            @Override
            public E next() {
                E item = eNode.item;
                eNode = eNode.next;
                return item;
            }
        };
    }

    private class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        private Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }
}
