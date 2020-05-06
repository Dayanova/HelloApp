package com.example.part1.lesson03.task01;

import com.example.part1.lesson03.task02.ObjectBox;
import com.example.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
/**
 * Класс MathBox с колекцией list
 * @ author Dayanova
 * @ version 1.0
 */
public class MathBox extends ObjectBox<Number> {
    private ArrayList<Number> list= new ArrayList<>();

    /**
     * Конструктор - создание нового объекта
     */
    public MathBox(Number[] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            int count = Collections.frequency(this.list, numbers[i]);

            if (count > 1) {
                throw new ArithmeticException("duplication of values in an array");
            }
            this.list.add(numbers[i]);
        }
    }


    /**
     * метод расчитывающий сумму элементов коллекции
     */
    public int summator () {
        int s = 0;
        for (int i = 0; i < this.list.size(); i++){
            s+=(int) this.list.get(i);
        }
        return s;
    }
    /**
     * метод расчитывающий сумму элементов коллекции
     */
    public void splitter (Number divisor){
        for (int i = 1; i < this.list.size(); i++)   {
            Double div =(double) this.list.get(i) /(double) divisor;
            this.list.set(i,(Number) div);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    public int hashCode() {
        int hash = Objects.hash(list);
        return hash;
    }
}

