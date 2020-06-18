package com.example.part1.lesson09.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * */
public class MyClassLoader extends ClassLoader {
    private final String pathtobin = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson09\\task1\\com\\example\\part1\\lesson09\\task1\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class cl = null;
        System.out.println("findClass starts work: " + name);
        Path pathToFile = Paths.get(pathtobin + "SomeCLass.class");
        if (name.equals("SomeCLass")) {
            try {
                byte[] bytes = Files.readAllBytes(pathToFile);
                cl = defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.findClass(name);
       return cl;
    }

}
