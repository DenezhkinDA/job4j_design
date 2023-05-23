package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Matrix {
    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                out.write((j == 0 ? "" : "\t").getBytes());
                out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
        }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(9);
    }
}
