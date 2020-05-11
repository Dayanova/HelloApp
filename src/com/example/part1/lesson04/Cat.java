package com.example.part1.lesson04;
import com.example.person.Person;

import java.util.Objects;

/**
 * Класс Кот со свойствами <b>nickname</b> , <b>weight</b> и <b>man</b>
 * @ author Dayanova
 * @ version 1.0
 */

public class Cat {
    /** поле кличка*/
    public String nickname;
    /** поле вес*/
    public double weight;
    /** поле владелец*/
    public Person man;

    /**
     * Конструктор - создание нового объекта
     * @see Cat#Cat(String,double,Person)
     */
    public Cat(String nickname, double weight,Person man ) {
        this.nickname = nickname;
        this.weight = weight;
        this.man= man;
    }
    /**
     * Функция получения значения поля {@link Cat#nickname}
     * @return возвращает кличку животного
     */

    public String getnickname() {
        return nickname;
    }
    /**
     * Функция получения значения поля {@link Cat#weight}
     * @return возвращает вес животного
     */

    public double getWeight(){return weight; }

    public int hashCode(){
        return nickname.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cats = (Cat) o;
        return Objects.equals(nickname, ((Cat) o).nickname);
    }

}
