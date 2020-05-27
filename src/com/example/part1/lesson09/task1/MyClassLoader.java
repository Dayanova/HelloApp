package com.example.part1.lesson09.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    private final String pathtobin = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson09\\task1\\com\\example\\part1\\lesson09\\task1";

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("SomeCLass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass starts work: " + name);
        Path pathToFile = Paths.get(pathtobin +"\\"+ "SomeCLass.class");
        if (name.equals("SomeCLass")) {
            try {
                byte[] bytes = Files.readAllBytes(pathToFile);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }

}
