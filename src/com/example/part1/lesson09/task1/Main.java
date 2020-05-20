package com.example.part1.lesson09.task1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        String str = null;
        String path = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson09\\task1\\SomeCLass.java";
        File fl = new File(path);
        //Проверяем, существует ли такой файл
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            while (str != " ")
            {   Scanner in = new Scanner(System.in);
                str=in.next();
                writer.write(str,9,str.length());
            }
            writer.flush();
            writer.close();

        System.out.println("Компилируем " + fl.getAbsolutePath() + " " + fl.getAbsolutePath());
        Process p1 = Runtime.getRuntime().exec(path+" "+fl.getAbsolutePath());
            }
        catch (IOException e) {
        e.printStackTrace();
             }
        System.out.println("Компиляция завершена");
        MyClassLoader loader = new MyClassLoader();
        Class cl = loader.findClass(path);
        System.out.println(cl);
        Method method = cl.getMethod("doWork");
        System.out.println(method);
        method.invoke(cl.newInstance());

    }
}
