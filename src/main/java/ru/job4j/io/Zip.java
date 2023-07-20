package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check(ArgsName argsName) throws IOException {
        Path dir = Path.of(argsName.get("d"));
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("The directory %s is invalid", dir));
        }
        String ext = argsName.get("e");
        if (ext.matches("\\..+")) {
            throw new IllegalArgumentException(String.format("The extension %s is invalid", ext));
        }
        String arch = argsName.get("o");
        if (Files.exists(Path.of(arch))) {
            throw new IllegalArgumentException(String.format("The file %s already exists", arch));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        zip.check(argsName);
        Path dir = Path.of(argsName.get("d"));
        String ext = argsName.get("e");
        List<Path> sources = Search.search(dir, s -> !s.toFile().getName().endsWith(ext));
        zip.packFiles(sources, new File(argsName.get("o")));
    }
}
