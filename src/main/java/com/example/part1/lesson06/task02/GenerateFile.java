package com.example.part1.lesson06.task02;

import java.util.Random;
import java.io.*;

/**
 * Класс Генератор файлов
 *
 * @ author Dayanova
 * @ version 1.0
 */
public class GenerateFile {
    /**
     * Метод генератор файлов
     *
     * @param n           -кол-во файлов
     * @param path        - каталог
     * @param size        - размер файла
     * @param probability - вероятность
     * @param words       - массив слов
     */

    public void getFiles(String path, int n, int size, int probability, String[] words) {
        for (int i = 0; i < n; i++) {
            try (FileWriter writer = new FileWriter(path + "note" + i + ".txt")) {
                String text = getText(size, probability, words);
                writer.write(text);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Метод генератор случайных чисел в заданном диапазоне
     *
     * @param min - минимальное число
     * @param max - максимальное число
     * @return число
     */
    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    /**
     * Метод генератор текста для файла
     *
     * @param size        - размер файла
     * @param probability - вероятность
     * @param words       - массив слов
     * @return текст
     */
    private String getText(int size, int probability, String[] words) {
        String text = "";
        while (text.length() < size) {
            text += makeParagraph(probability, words);
        }
        return text;
    }

    /**
     * Метод генератор слов
     *
     * @return сгенерированное слово
     */
    private String makeWord() {
        String ABC = "Aabcdefghijklmnopqrstuvwxyz";
        String word = "";
        Random random = new Random();
        int randomLen = rnd(1, 15);
        for (int i = 0; i < randomLen; i++) {
            char c = ABC.charAt(random.nextInt(ABC.length()));
            word += c;
        }
        return word;
    }

    /**
     * Метод для создания абзаца с текстом
     *
     * @param probability - вероятность
     * @param words       - массив слов
     * @return сгенерированное абзац
     */
    private String makeParagraph(int probability, String[] words) {
        int Len = rnd(1, 20);
        String paragraph = "";
        for (int i = 0; i < Len; i++) {
            paragraph += makeSentence(probability, words) + " ";
        }
        return paragraph + "\n";
    }

    /**
     * Метод принятия решения в зависимости от вероятности
     *
     * @param probability - вероятность
     * @return true/false
     */
    private Boolean accepted(int probability) {
        return Math.random() < 1 / probability ? true : false;
    }

    /**
     * Метод для возвращает случайное слово из массива
     *
     * @param words - массив слов
     * @return случайное слово
     */
    private String getWordArray(String[] words) {
        int n = (int) Math.random() * words.length;
        return words[n];
    }

    /**
     * Метод для добавления случайного слова из массива
     *
     * @param sentence - предложение
     * @param words    - массив слов
     * @return предложение
     */
    private String replaceWord(String sentence, String[] words) {
        String[] wordGenerate = sentence.split(" ");
        int n = (int) Math.random() * wordGenerate.length;
        wordGenerate[n] = getWordArray(words);
        StringBuilder builder = new StringBuilder();
        for (String s : wordGenerate) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

    /**
     * Метод для генерации предложений
     *
     * @param probability - вероятность
     * @param words       - массив слов
     * @return сгенерированное предложение
     */
    private String makeSentence(int probability, String[] words) {
        int Len = rnd(1, 15);
        String sentence = "";
        for (int i = 0; i < Len; i++) {
            String[] space = new String[]{" ", ", "};
            int n = (int) Math.random() * space.length;
            sentence += makeWord() + space[n];
        }
        String[] punctuation = new String[]{".", "!", "?", "..."};
        int n = (int) Math.random() * punctuation.length;

        if (accepted(probability)) {
            sentence = replaceWord(sentence, words);
        }

        if ((sentence.charAt(sentence.length() - 2)) == ',') {
            sentence = sentence.substring(0, sentence.length() - 1);
        }
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1, sentence.length() - 1) + punctuation[n];
    }

}
