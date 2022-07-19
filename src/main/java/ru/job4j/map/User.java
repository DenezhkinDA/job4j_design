package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Dima", 2, birthday);
        User user2 = new User("Dima", 2, birthday);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("user1 hashCode: " + user1.hashCode());
        System.out.println("user1 hashCode: " + user2.hashCode());
        System.out.println(map);
    }
}
