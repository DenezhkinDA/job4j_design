package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User userPrev : previous) {
            if (current.contains(userPrev)) {
                map.put(userPrev.getId(), userPrev.getName());
            } else {
                deleted++;
            }
        }
        for (User userCur : current) {
           if (previous.contains(userCur)) {
               if (!map.get(userCur.getId()).equals(userCur.getName())) {
                   changed++;
               }
           } else {
               added++;
           }
        }
        return new Info(added, changed, deleted);
    }
}
