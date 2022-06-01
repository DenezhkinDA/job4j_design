package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Dmitriy"));
        Role result = store.findById("2");
        assertThat(result.getRolename(), is("Dmitriy"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Petr"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Dima"));
        store.add(new Role("3", "Maxim"));
        Role result = store.findById("3");
        assertThat(result.getRolename(), is("Dima"));
    }

    @Test
    public void whenReplaceThenRolenameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Dima"));
        store.replace("3", new Role("3", "Maxim"));
        Role result = store.findById("3");
        assertThat(result.getRolename(), is("Maxim"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Dmitriy"));
        store.replace("3", new Role("3", "Maxim"));
        Role result = store.findById("2");
        assertThat(result.getRolename(), is("Dmitriy"));
    }

    @Test
    public void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Dmitriy"));
        store.delete("2");
        Role result = store.findById("2");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenRolenameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("4", "Dmitriy"));
        store.delete("10");
        Role result = store.findById("4");
        assertThat(result.getRolename(), is("Dmitriy"));
    }
}
