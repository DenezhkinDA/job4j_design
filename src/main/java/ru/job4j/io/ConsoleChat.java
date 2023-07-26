package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean running = true;
            System.out.println("Задавайте вопрос: ");
            String answer = br.readLine();
            log.add(answer);
            while (!OUT.equals(answer)) {
                if (!STOP.equals(answer) && running) {
                    String textBot = phrases.get(new Random().nextInt(phrases.size()));
                    System.out.printf("Ответ Бота: %s\n", textBot);
                    log.add(textBot);
                }
                System.out.println("Задавайте вопрос: ");
                answer = br.readLine();
                log.add(answer);
                running = running ? !STOP.equals(answer) : CONTINUE.equals(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            while (br.ready()) {
                list.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter(path))) {
            for (String str : log) {
                bw.append(str).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/ConsoleChat/log.txt", "./src/data/ConsoleChat/answers.txt");
        cc.run();
    }
}
