package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Dmitriy";
        int age = 37;
        short height = 78;
        long numberPhone = 89109998877L;
        float weight = 77.3f;
        double numberPi = 3.1415926535897932384626433d;
        boolean father = true;
        char firstLetterName = 'D';
        LOG.debug("User info name : {}, age : {}, height : {}, numberPhone : {}"
                        + ", weight : {}, numberPi : {}, father : {}, firstLetterName : {}",
                name, age, height, numberPhone, weight, numberPi, father, firstLetterName);
    }
}
