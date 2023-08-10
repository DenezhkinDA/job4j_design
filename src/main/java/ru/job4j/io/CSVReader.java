package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))
                .useDelimiter(argsName.get("delimiter"))) {
            String[] filters = argsName.get("filter").split(",");
            String[] spFilters = scanner.nextLine().split(argsName.get("delimiter"));
            String[] lastFilters = new String[spFilters.length];
            for (int i = 0; i < filters.length; i++) {
                for (String spFilter : spFilters) {
                    if (filters[i].equals(spFilter)) {
                        lastFilters[i] = spFilter;
                        break;
                    }
                }
            }
            List<String> filterCSV = new ArrayList<>();
            filterCSV.add(argsName.get("filter").replace(",", argsName.get("delimiter")));
            while (scanner.hasNextLine()) {
                String[] column = scanner.nextLine().split(argsName.get("delimiter"));
                StringJoiner strings = new StringJoiner(argsName.get("delimiter"));
                for (int i = 0; i < column.length; i++) {
                    if (lastFilters[i] != null) {
                        int index = Arrays.asList(spFilters).indexOf(lastFilters[i]);
                        String print = column[index];
                        strings.add(print);
                    }
                }
                filterCSV.add(strings.toString());
            }
            if ("stdout".equals(argsName.get("out"))) {
                printResult(filterCSV);
            } else {
                printFile(filterCSV, argsName);
            }
        }
    }

    private static void printResult(List<String> forPrint) {
        for (String line : forPrint) {
            System.out.println(line);
        }
    }
    private static void printFile(List<String> forPrint, ArgsName argsName) throws IOException {
        if ("stdout".equals(argsName.get("out"))) {
            for (String string : forPrint) {
                System.out.println(string);
            }
        } else {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(argsName.get("out"), true)))) {
                forPrint.forEach(out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validateArgs(String[] args, ArgsName argsName) {
        if (args.length != 4) {
            throw new IllegalArgumentException(String.format(
                    "4 arguments must be passed to the program"));
        }
        if (!(Files.exists(Path.of(argsName.get("path"))) && argsName.get("path").endsWith(".csv"))) {
            throw new IllegalArgumentException("The file does not exist or has an incorrect extension");
        }
        if (!(args[1].endsWith(",") || args[1].endsWith(";"))) {
            throw new IllegalArgumentException("The separator must be a comma or semicolon");
        }
        if (!("stdout".equals(argsName.get("out")) || argsName.get("out").endsWith(".csv"))) {
            throw new IllegalArgumentException("The arguments for the output of the result are incorrect");
        }
        if (argsName.get("filter").endsWith("=")) {
            throw new IllegalArgumentException("No filter");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(args, argsName);
        handle(argsName);
    }
}
