package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            StringBuilder builder = new StringBuilder();
            String str = in.readLine();
            boolean check = false;
            while (str != null) {
                String[] s = str.split(" ");
                if (!check && (str.contains("400") || str.contains("500"))
                        || check && (str.contains("200") || str.contains("300"))) {
                    check = !check;
                    builder.append(s[1]).append(check ? ";" : System.lineSeparator());
                }
                str = in.readLine();
            }
            out.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
