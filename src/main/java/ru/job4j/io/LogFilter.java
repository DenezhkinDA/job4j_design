package ru.job4j.io;

import java.io.*;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            list = in.lines().filter(s -> s.contains(" 404 ")).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String i : log) {
                out.printf(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("data/log.txt");
        save(log, "data/404.txt");
    }
}
