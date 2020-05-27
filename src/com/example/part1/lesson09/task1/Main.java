package com.example.part1.lesson09.task1;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String str = null;
        String path = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson09\\task1\\";
        File fl = new File(path + "SomeCLass.java");
        String classname = fl.getName().split("\\.")[0];
        str ="package com.example.part1.lesson09.task1;"+ "\n" +"public class SomeCLass implements Worker {" + "\n" + " public void doWork(){" + "\n";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path + "SomeCLass.java"))) {
            writer.write(str);
            while (!str.equals("")) {
                Scanner in = new Scanner(System.in);
                str = in.nextLine();
                writer.write(str);
            }
                writer.write("\n"+ "} }" + "\n");
                writer.flush();
                writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Компилируем " + classname);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = fl.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(fl));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();
        System.out.println("Компиляция завершена1");
        ClassLoader cl = new MyClassLoader();
        Class<?> MyClass = cl.loadClass(classname);
        MyClass.getMethod("doWork").invoke(MyClass.newInstance());

    }
}
