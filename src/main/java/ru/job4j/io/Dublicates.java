package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dublicates extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> map = new HashMap<>();
    List<Path> result = new ArrayList<>();
    FileProperty fileProperty;


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!map.containsKey(fileProperty)) {
            map.put(fileProperty, file);
        } else {
            result.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getDublicates() {
        return result;
    }
}
