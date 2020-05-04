package com.example.person;

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
     * Функция сравнения значений {@link Person#name}
     * @return возвращает 1 если объект больше
     *                   -1 если объект меньше
     *                    0 если объекты равны */
    public int compareToName(Person o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
          if (this.age == o.age) {
                throw new ArithmeticException("duplication of values in an array");

            }
        }
        return result;
    }
    /**
     * Функция сравнения значений {@link Person#genderPerson}
     * @return возвращает 1 если объект больше
     *                   -1 если объект меньше
     *                    0 если объекты равны */

    public int compareToGender(Person o) {
        int result = this.genderPerson.compareTo(o.genderPerson);
        return result;
    }
    /**
     * Функция сравнения значений {@link Person#age}
     * @return возвращает 1 если объект больше
     *                   -1 если объект меньше
     *                    0 если объекты равны */

    public int compareToAge(Person o) {
        if (this.age >o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        } else {
            return 0;
        }
    }

}