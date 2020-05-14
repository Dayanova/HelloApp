package com.example.part1.lesson06.task01;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class ReadTextFile {
    public static void main(String[] args) throws IOException {
        LinkedList<String> list = new LinkedList<>();
        String Path = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson06\\task01\\";
        File file = new File(Path + "notes.txt");
        // Создание файла
        file.createNewFile();
        // Создание объекта FileWriter
        FileWriter write = new FileWriter(file);
        // Запись содержимого в файл
        write.write("Банан\nАрбуз\nГруша\nЯблоко\nВиноград\n");
        write.flush();
        write.close();

        try (FileInputStream fileInput = new FileInputStream(Path + "notes.txt")) {
            BufferedReader b = new BufferedReader(new InputStreamReader(fileInput));
            String readLine = "";

            //Читаем построчно и заносим в список
            while ((readLine = b.readLine()) != null) {
                int count = Collections.frequency(list, readLine);
                if (count > 1) {
                    throw new ArithmeticException("duplication of values in an array");
                }
                list.add(readLine);
            }

            //Сортируем список
            Collections.sort(list);
            //Заносим отсортированный список в новый файл
            String outputFileName = Path + "notes_sort.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
                System.out.println(list.get(i));
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
