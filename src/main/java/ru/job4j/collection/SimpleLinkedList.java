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
            private int point;
            private final int expectedModCount = modCount;
            private Node<E> eNode;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (eNode != null) {
                    eNode = eNode.next;
                } else {
                    eNode = first;
                }
                point++;
                return eNode.item;
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
