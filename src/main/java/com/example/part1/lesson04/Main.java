package com.example.part1.lesson04;
import com.example.person.Person;
import com.example.person.Sex;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        List<Cat> cats = getCat();
        Animals animals = new Animals(cats);
        animals.printMap();

        Cat newCat = new Cat("Laim", 6, new Person(rnd(1, 10), Sex.MAN, "Ivan"));
        animals.updateCat(1, newCat);
        System.out.println("Print update map:");
        animals.printMap();

        newCat = animals.searchCat("Smurf");
        System.out.println("Cat:" + newCat.getnickname() + " " + newCat.getWeight());
        LinkedHashMap<Integer, Cat> sortedMap = animals.sortByValue();

        System.out.println("Print sort map:");
        for (Map.Entry<Integer, Cat> entry : sortedMap.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value :< Nick= " + entry.getValue().getnickname()
                    + " weight=" + entry.getValue().getWeight()
                    + " Person=" + entry.getValue().man.getName() + ">");
        }


    }

    /**
     * Функция генерации котиков @link Cat}
     *
     * @return возвращает List
     */
    static List<Cat> getCat() {
        return new ArrayList<>(Arrays.asList(
                new Cat("Connor", 5, new Person(rnd(1, 10), Sex.MAN, "Tim")),
                new Cat("Smurf", 4, new Person(rnd(1, 10), Sex.WOMAN, "Alise")),
                //  new Cat( "Smurf",4,new Person(rnd(1,10),Sex.WOMAN,"Alise")),
                new Cat("Tigr", 3, new Person(rnd(1, 10), Sex.MAN, "Valentin")),
                //   new Cat("Smurf",6,new Person(rnd(1,10),Sex.WOMAN,"Tatiana")),
                new Cat("Dff", 7, new Person(rnd(1, 10), Sex.MAN, "Anatol")),
                new Cat("Awer", 2.5, new Person(rnd(1, 10), Sex.MAN, "Micha"))
        ));
    }


    static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}
