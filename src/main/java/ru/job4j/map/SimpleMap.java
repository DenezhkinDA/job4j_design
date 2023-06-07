package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int hash = key == null ? 0 : hash(key.hashCode());
        int index = indexFor(hash);
        boolean result = false;
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
                result = true;
            }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = table;
        table = new MapEntry[capacity * 2];
        for (MapEntry<K, V> el : newTable) {
            if (el != null) {
                this.put(el.key, el.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = (key == null) ? 0 : hash(key.hashCode());
        if (table[index] != null && Objects.equals(table[index].key, key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && Objects.equals(table[index].key, key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public boolean containsKey(K fileProperty) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int exModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (exModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
