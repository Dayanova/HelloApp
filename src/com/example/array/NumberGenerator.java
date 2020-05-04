package com.example.array;
import java.util.Random;
import java.util.Scanner;

public class NumberGenerator {
    /**
     * Метод для генерации числа в промежутке min и max
     * @param max - максимальное числа для генерации
     * @param min - минимальныное число для генерации
     * @return возвращает число
     */
    static double rnd(int min, int max)
    {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите целое число N : ");
        int n = in.nextInt();
        Random ran = new Random();

        for (int i = 0 ;i <=n ; i++){
         double a = rnd(1,100);
            if (a<0) {
                throw new IllegalArgumentException("The argument cannot be negative");
            }
            /** Для каждого числа k вычислить квадратный корень q*/
            double q = Math.sqrt(a) ;
             /**Если квадрат целой части q числа рав k, то вывести это числ на экран.*/
            int d =(int) Math.pow( q%10,2);
            if (d == i){
                System.out.println("q2=" + q );
            }
         }
      }
}
