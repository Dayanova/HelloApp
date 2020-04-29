package com.example.person;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс Пол с константами  <b>WOMAN</b> , <b>MAN</b>
 * @ author Dayanova
 * @ version 1.0
 */
class Sex {
    public static final String WOMAN = "Woman";
    public static final String MAN = "Man";
}

/**
 * Класс Человек со свойствами <b>Age</b> , <b>Name</b> и <b>GenderPerson</b>
 * @ author Dayanova
 * @ version 1.0
 */

class Person {
    /** поле возраст*/
     public int Age;
     /** поле имя*/
     public String Name;
     /** поле пол*/
     public String GenderPerson;

     /**
     * Конструктор - создание нового объекта
     * @see Person#Person(int,String,String)
     */
    public Person(int age,String gender, String name) {
        this.Age = age;
        this.GenderPerson =gender;
        this.Name = name;
    }
    /**
     * Функция получения значения поля {@link Person#Name}
     * @return возвращает имя человека
     */
    public String getName() {
        return Name;
    }
    /**
     * Функция получения значения поля {@link Person#Age}
     * @return возвращает возраст человека
     */
    public int GetAge() {
        return Age;
    }
    /**
     * Функция получения значения поля {@link Person#GenderPerson}
     * @return возвращает пол человека
     */
    public String getGenderPerson() {
        return GenderPerson;
    }

}
/**
 * Класс Компаратор для сортировки по имени и полу
 * @ author Dayanova
 * @ version 1.0
 */
class SortedByName implements Comparator<Person>
{
/**
 * Метод compareTo для сравнения двух объектов {@link Person}
 * @return возвращает результат сравнения
 */
    public int compare(Person obj1, Person obj2)
    {
        int r = obj1.getName().compareTo( obj2.getName() );
        if (r != 0) return r;
        r = obj1.getGenderPerson().compareTo( obj2.getGenderPerson() );
        if (r != 0) return r;
        return 0;
    }
}
/**
 * Класс Компаратор для сортировки по возрасту
 * @ author Dayanova
 * @ version 1.0
 */

class SortedByAge implements Comparator<Person>
{
 /**
 * Метод compareTo для сравнения двух объектов {@link Person}
 * @return возвращает результат сравнения
 */
    public int compare(Person obj1, Person obj2)
    {
        int price1 = obj1.GetAge();
        int price2 = obj2.GetAge();

        if (price1 > price2) {
            return 1;
        } else if (price1 < price2) {
            return -1;
        } else {
            return 0;
        }
    }
}
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
        Person[] PersonArray = new Person[1000];
        /** заполним массив */
        for (int i = 0; i<1000; i++) {

            int rand2 = rnd(1,100);
            String rand3 = GetGender((int) (Math.random() *2));
            int rand1 = (int) (Math.random() * oneLength);

            PersonArray[i] = new Person(rand2,rand3,wordListOne[rand1]);
        }
        /**Вызов методов сортировки */
        System.out.println("~~~~~ без сортировки");
        for(Person person: PersonArray)
            System.out.println( person.getName()  +" "+ person.GetAge() +" "+ person.getGenderPerson() );

        System.out.println("\n сортировка по имени и полу");
        long start = System.currentTimeMillis();
        Arrays.sort(PersonArray, new SortedByName());
        long finish = System.currentTimeMillis();
        long timeConsumed = finish - start;
        System.out.println("\n Время обработки :"+timeConsumed);

        for(Person person: PersonArray)
            System.out.println( person.getName() + " " + person.GetAge()+" " + person.getGenderPerson() );

        System.out.println("\n сортировка по возрасту");
        start = System.currentTimeMillis();
        Arrays.sort(PersonArray, new SortedByAge());
        finish = System.currentTimeMillis();
        timeConsumed = finish - start;
        System.out.println("\n Время обработки :"+timeConsumed);

        for(Person person: PersonArray)
            System.out.println( person.getName() +" "+ person.GetAge() +" "+ person.getGenderPerson() );
        System.out.println("\n общая соритровка");
        Comparator<Person> pcompare = new SortedByName().thenComparing(new SortedByAge());
        start = System.currentTimeMillis();
        Arrays.sort(PersonArray, pcompare);
        finish = System.currentTimeMillis();
        timeConsumed = finish - start;
        System.out.println("\n Время обработки :"+timeConsumed);

        for(Person person: PersonArray)
            System.out.println( person.getName() +" "+ person.GetAge() +" "+ person.getGenderPerson() );

    }
}
