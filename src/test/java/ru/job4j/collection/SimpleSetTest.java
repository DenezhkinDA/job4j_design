package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddFive() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(5));
        assertTrue(set.add(6));
        assertTrue(set.add(9));
        assertTrue(set.contains(5));
        assertFalse(set.add(5));
    }

    @Test
    public void whenAddAll() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertTrue(set.add(3));
        assertTrue(set.contains(3));
        assertTrue(set.add(4));
        assertTrue(set.contains(4));
        assertTrue(set.add(7));
        assertTrue(set.contains(7));
        assertTrue(set.add(8));
        assertTrue(set.contains(8));
        assertFalse(set.add(1));
        assertFalse(set.add(3));
        assertFalse(set.add(4));
        assertFalse(set.add(7));
        assertFalse(set.add(8));
    }
}
