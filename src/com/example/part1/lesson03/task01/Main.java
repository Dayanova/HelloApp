package com.example.part1.lesson03.task01;
import com.example.part1.lesson03.task02.ObjectBox;

public class Main {

    public static void main(String[] args) {
    Number[] numbers = new Number[10];

        for (int i = 0 ;i <numbers.length ; i++) {
            numbers[i] = (int) (Math.random() * 100) + 1;
        }

    MathBox mathBox = new MathBox (numbers);
    int s= mathBox.summator();
     System.out.println("s=" + s);
    // ObjectBox b=new MathBox (numbers);
    // b.add(s);
   //  b.dump();

    }
}
