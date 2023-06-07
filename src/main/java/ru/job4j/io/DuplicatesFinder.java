package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Dublicates searcher = new Dublicates();
        Files.walkFileTree(Path.of("./"), searcher);
        for (Path path : searcher.getDublicates()) {
            System.out.println(path);
        }
    }
}
