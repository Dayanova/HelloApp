package com.example.part1.lesson03.task01;

import com.example.part1.lesson03.task02.ObjectBox;
import com.example.person.Person;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Класс MathBox с колекцией list
 * @ author Dayanova
 * @ version 1.0
 */
public class MathBox extends ObjectBox<Number> {
    /**
     * Конструктор - создание нового объекта
     */
    public MathBox(Number[] numbers) {
        super(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (this.sList.contains(numbers[i])) {
                throw new ArithmeticException("duplication of values in an array");
            }
            this.sList.add(numbers[i]);
        }
    }

    /**
     * метод расчитывающий сумму элементов коллекции
     */
    public int summator () {
        int s = 0;
        ArrayList<Number> ms= new ArrayList<Number>();
         ms.addAll(this.sList);
        for (int i = 0; i < ms.size(); i++){
            s+=(int) ms.get(i);
        }
        return s;
    }
    /**
     * метод расчитывающий сумму элементов коллекции
     */
    public void splitter (Number divisor){
        ArrayList<Number> ms= new ArrayList<Number>();
        ms.addAll(this.sList);
        this.sList.clear();
        for (int i = 1; i < ms.size(); i++)   {
            Double div =(double) ms.get(i) /(double) divisor;
            this.sList.add((Number) div);
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(sList, mathBox.sList);
    }

    public int hashCode() {
        int hash = Objects.hash(this.sList);
        return hash;
    }

    @Override
    public void dump() {
        super.dump();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

