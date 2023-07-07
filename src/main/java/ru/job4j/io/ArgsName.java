package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
          return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .filter(ArgsName::validate)
                .map(x -> x.substring(1))
                .map(a -> a.split("=", 2))
                .forEach(n -> values.put(n[0], n[1]));
    }

    private static boolean validate(String arg) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", arg));
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", arg));
            }

            if (arg.startsWith("-=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", arg));
            }
            if (arg.indexOf("=") == arg.length() - 1) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", arg));
            }
            return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(String.format("Arguments not passed to program"));
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("out"));
    }
}
