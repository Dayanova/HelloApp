package com.example.part1.lesson07.Task1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    private static ExecutorService es = Executors.newFixedThreadPool(4);

    static Integer rnd(int min, int max) {
        Integer num;
        max -= min;
        num = (int) (Math.random() * ++max) + min;
        return num;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Integer[] randomArr = new Integer[10];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = rnd(1, 100);
        }
        long start = System.currentTimeMillis();
        System.out.println("Start : " + start);

        for (int i = 0; i < randomArr.length; i++) {
            System.out.println("randomArr[" + i + "]=" + randomArr[i]);

            Future<Integer> factorial = es.submit(new Factorial(randomArr[i]));
            double result = factorial.get();

            System.out.println("result= " + factorial.get());
        }
        long finish = System.currentTimeMillis();
        System.out.println("finish : " + finish);
        System.out.println("resultTime : " + (finish - start));

    }

}
