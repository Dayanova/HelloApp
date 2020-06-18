package com.example.person;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Класс для заполнения и сортировки массивов с персонами
 * @ author Dayanova
 * @ version 1.0
 */
public class OrderByPerson {

    /**
     * Метод для генерации числа в промежутке min и max
     * @param max - максимальное числа для генерации
     * @param min - минимальныное число для генерации
     * @return возвращает число
     */
     static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    /**
     * Метод для возвращает пол из константы
     * @param num - число 1 либо 0
     * @return возвращает пол
     */
    static String GetGender(int num) {
            if (num == 1)
                return Sex.MAN;
            else return Sex.WOMAN;
    }

    public static void main(String[] args) {
        String[] wordListOne = { "Фидо","Зевс","Максим","Виталий","Максим","Валентин","Эмиль",
                                "Андрей","Сергей","Игорь","Лев","Борис","Вадим","Михаил","Радик",
                                "Радмир","Динар","Денис","Дмитрий","Анна","Юлия","Ирина","Дина",
                                "Наталья","Евгения","Лиана","София","Регина","Крестина" };
        int oneLength = wordListOne.length;
        Person[] PersonArray = new Person[10];
        /* заполним массив */
        for (int i = 0; i<10; i++) {

            int rand2 = rnd(1,100);
            String rand3 = GetGender((int) (Math.random() *2));
            int rand1 = (int) (Math.random() * oneLength);

            PersonArray[i] = new Person(rand2,rand3,wordListOne[rand1]);
        }
        /*Вызов методов сортировки */
        System.out.println("~~~~~ без сортировки");
        for(Person person: PersonArray)
            System.out.println( person.getName()  +" "+ person.getAge() +" "+ person.getGenderPerson() );

        Person [] result_sort1 = new Person[PersonArray.length];

        System.out.println("\n сортировка пузырьком");
        long start = System.currentTimeMillis();
        result_sort1 = BubbleSortArray.bubbleSortArray(PersonArray);
        long finish = System.currentTimeMillis();
        long timeConsumed = finish - start;
        System.out.println("\n Время обработки :"+timeConsumed);

        for(Person p: result_sort1)
            System.out.println( p.getName() + " " + p.getAge()+" " + p.getGenderPerson() );

        System.out.println("\n сортировка слиянием");
         start = System.currentTimeMillis();
        Person [] result_sort2 = new Person[PersonArray.length];
        MergeSortArray Merge = new MergeSortArray();
        result_sort2 = Merge.sortMerge(PersonArray);
        finish = System.currentTimeMillis();
        timeConsumed = finish - start;
        System.out.println("\n Время обработки :"+timeConsumed);

        for(Person p: result_sort2)
            System.out.println( p.getName() + " " + p.getAge()+" " + p.getGenderPerson() );

    }
}
