package com.example.part1.lesson06.task02;

public class Main {
    public static void main(String[] args) {
        String[] Wordsarray = {"ONE", "TWO", "WORLD", "THERE", "PRIVATE",
                "NO", "YES", "THIS", "AUGUST", "JUST", "JULE", "MONTH", "OK", "RIGHT",
                "KIND", "STAMP", "AVOID", "ACCENT", "CHIC", "ACCIDENT", "SEVERAL", "CHANGE",
                "SPEED", "BE", "GET", "STRESS", "RELAX", "TRAFFIC", "WORSE"};
        GenerateFile Generator = new GenerateFile();
        String path_1 = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson06\\task02\\";
        Generator.getFiles(path_1, 3, 20000, 50, Wordsarray);
    }
}
