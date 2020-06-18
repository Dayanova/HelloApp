package com.example.person;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Класс Человек со свойствами <b>Age</b> , <b>Name</b> и <b>GenderPerson</b>
 * @ author Dayanova
 * @ version 1.0
 */

public class Person {
    /** поле возраст*/
    public int age;
    /** поле имя*/
    public String name;
    /** поле пол*/
    public String genderPerson;

    /**
     * Конструктор - создание нового объекта
     * @see Person#Person(int,String,String)
     */
    public Person(int age,String gender, String name) {
        this.age = age;
        this.genderPerson =gender;
        this.name = name;
    }
    /**
     * Функция получения значения поля {@link Person#name}
     * @return возвращает имя человека
     */
    public String getName() {
        return name;
    }
    /**
     * Функция получения значения поля {@link Person#age}
     * @return возвращает возраст человека
     */
    public int getAge() {
        return age;
    }
    /**
     * Функция получения значения поля {@link Person#genderPerson}
     * @return возвращает пол человека
     */
    public String getGenderPerson() {
        return genderPerson;
    }

    /**
     * Функция сравнения значений {@link Person#genderPerson}
     * @return возвращает 1 если объект больше
     *                   -1 если объект меньше
     *                    0 если объекты равны */

    public int compareTo(Person o) {
        int result = this.genderPerson.compareTo(o.genderPerson);
        if (result!=0) return result;
        result = this.age - o.age;
        if(result != 0) {
            return (int) result / Math.abs(result);
        }
        result = this.getName().compareTo(o.genderPerson);
        return result;
    }

}