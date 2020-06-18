package com.example.part1.lesson07.Task1;

import java.util.concurrent.Callable;

/**
 * Класс Factorial
 *
 * @ author Dayanova
 * @ version 1.0
 */

public class Factorial implements Callable<Integer> {
    /**
     * Число n
     */
    private Integer number;

    /**
     * Конструктор - создание нового объекта
     */
    public Factorial(Integer num) {
        this.number = num;
    }

    /**
     * Метод для вычисления факториала
     *
     * @param num - число
     */
    public Integer calcFactorial(Integer num) {
        Integer fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    /**
     * Метод для таска
     */
    public Integer call() throws Exception {

        return calcFactorial(number);

    }
}
