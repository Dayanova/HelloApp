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
        System.out.print("Введите целое число K: ");
        int k = in.nextInt();

        Random ran = new Random();

        for (int i = 0 ;i <=n ; i++){

         double a = rnd(-10,10);
            System.out.println("a=" + a );
            if (a<0) {
                throw new IllegalArgumentException("The argument cannot be negative");
            }
            /** Для каждого числа k вычислить квадратный корень q*/
         if ( i == k ){
            double q = Math.sqrt(a) ;
             System.out.println("q=" + q);
             /**Если квадрат целой части q числа рав k, то вывести это числ на экран.*/

            double d = Math.pow( q%10,2);
             System.out.println("d=" + d );

            if (d == k){
                System.out.println("q=" + q );
            }
         }
      }
    }
}
