package com.example.part1.lesson12.task1;


import com.example.person.Person;
import com.example.person.Sex;
import javassist.ClassPool;
import java.util.ArrayList;
/* ПАРАМЕТРЫ
* -XX:+UseSerialGC -Xmx100m
* -XX:MetaspaceSize=350m
* */
public class Main {
    /**
     * Метод для генерации числа в промежутке min и max
     *
     * @param max - максимальное числа для генерации
     * @param min - минимальныное число для генерации
     * @return возвращает число
     */
    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    /**
     * Метод для возвращает пол из константы
     *
     * @param num - число 1 либо 0
     * @return возвращает пол
     */
    static String GetGender(int num) {
        if (num == 1)
            return Sex.MAN;
        else return Sex.WOMAN;
    }
    static ClassPool classPool = ClassPool.getDefault();
    public static void main(String[] args) {
        String[] wordListOne = {"Фидо", "Зевс", "Максим", "Виталий", "Максим", "Валентин", "Эмиль",
                "Андрей", "Сергей", "Игорь", "Лев", "Борис", "Вадим", "Михаил", "Радик",
                "Радмир", "Динар", "Денис", "Дмитрий", "Анна", "Юлия", "Ирина", "Дина",
                "Наталья", "Евгения", "Лиана", "София", "Регина", "Крестина"};
        int oneLength = wordListOne.length;
        ArrayList<Person> person = new ArrayList<>();

        for (int i = 0; i < 100000000; i++) {

            int rand2 = rnd(1, 100);
            String rand3 = GetGender((int) (Math.random() * 2));
            int rand1 = (int) (Math.random() * oneLength);
            person.add(new Person(rand2, rand3, wordListOne[rand1]));

            int d = rand2;
            if (d == i) {
                person.remove(i - 1);
            }
        }
 // TASK 2
        for (int i = 0; i < 1000000000; i++) {
            Class cl = classPool.makeClass(
                    i + " outofmemory.OutOfMemoryErrorMetaspace ").getClass();
            //Print name of class loaded
            System.out.println(cl.getName());
        }
    }
}