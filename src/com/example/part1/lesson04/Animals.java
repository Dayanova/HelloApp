package com.example.part1.lesson04;

import com.example.person.Person;
import com.example.person.Sex;

import java.util.*;

public class Animals {
    public static void main(String[] args) {
        List<Cat> cats = getCat();
        Map <Integer, Cat> Animals = generateAnimals(cats);
        printMap(Animals);
        Cat newCat = new Cat("Laim",6,new Person(rnd(1,10), Sex.MAN,"Ivan"));
        updateCat(1,Animals,newCat);
        newCat = searchCat("Smurf",Animals);
        System.out.println("Cat:" + newCat.getnickname() + " "+ newCat.getWeight() );

        Map<Integer, Cat> sortedMap = sortByValue(Animals);
        printMap(sortedMap);

    }

static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
    /**
     * Функция генерации котиков @link Cat}
     * @return возвращает List
     */
    private static List<Cat> getCat() {
        return new ArrayList<>(Arrays.asList(
                new Cat("Connor",5,new Person(rnd(1,10), Sex.MAN,"Tim")),
                new Cat( "Smurf",4,new Person(rnd(1,10),Sex.WOMAN,"Alise")),
              //  new Cat( "Smurf",4,new Person(rnd(1,10),Sex.WOMAN,"Alise")),
                new Cat("Tigr",3,new Person(rnd(1,10), Sex.MAN,"Valentin")),
             //   new Cat("Smurf",6,new Person(rnd(1,10),Sex.WOMAN,"Tatiana")),
                new Cat("Dff",7,new Person(rnd(1,10),Sex.MAN,"Anatol")),
                new Cat("Awer",2.5,new Person(rnd(1,10),Sex.MAN,"Micha"))
        ));
    }

    /**
     * Функция обновления значения @link Cat}
     * @return возвращает обновленный Map
     */
    private static void updateCat(Integer uniqueKey,Map<Integer,Cat> Animals,Cat o){
        if (Animals.containsKey(uniqueKey)){
            Animals.put(uniqueKey,o);
        }
    }
    /**
     * Функция Гененарции картотеки животных @link Cat}
     * @return возвращает Map
     */
    private static Map generateAnimals(List<Cat> animals) {
        int key=0;
        Map<Integer, Cat> result = new HashMap<>();
        for (Cat cat : animals) {
            key+=1;
            if (result.containsValue(cat)) {
                throw new ArithmeticException("duplication of values in an array");
            }
            result.put(key, cat);
        }
        return Collections.unmodifiableMap(result);
    }
    /**
     * Функция поиска по кличке в картотеке животных @link Cat}
     * @return возвращает Map
     */
    private static Cat searchCat(String nickname,Map<Integer,Cat> Animals){
        Cat cat;
        Map<String, Cat> cats =new HashMap<>();
        List<Map.Entry<Integer, Cat>> list =
                new LinkedList<Map.Entry<Integer, Cat>>(Animals.entrySet());

        for (Map.Entry<Integer, Cat> entry : list) {
            cats.put(entry.getValue().nickname, entry.getValue());
        }
        cat = cats.get(nickname);
        return cat;
    }
    /**
     * Функция сортировки картотеки животных @link Cat}
     * @return возвращает Map
     */
    private static Map<Integer, Cat> sortByValue(Map<Integer,Cat> unsortMap) {
        List<Map.Entry<Integer, Cat>> list =
                new LinkedList<Map.Entry<Integer, Cat>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Cat>>() {
            public int compare(Map.Entry<Integer, Cat> o1,
                               Map.Entry<Integer, Cat> o2) {
               /* int result = o1.getValue().man.getName().compareTo(o2.getValue().man.getName());
                if (result != 0) {
                    return result;
                }*/
                int result = o1.getValue().getnickname().compareTo(o2.getValue().getnickname());
                if (result != 0) {
                    return result;
                }

                result = (int) (o1.getValue().getWeight() - o2.getValue().getWeight());
                if (result != 0) {
                    return result / Math.abs(result);
                }
                return result;
            }
        });
        Map<Integer, Cat> sortedMap = new HashMap<Integer, Cat>();
        for (Map.Entry<Integer, Cat> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    /**
     * Функция печати картотеки животных @link Cat}
     * @return возвращает Map
     */

    public static void printMap(Map<Integer, Cat> map) {
        for (Map.Entry<Integer, Cat> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value :< Nick= " + entry.getValue().getnickname()
                    + " weight=" + entry.getValue().getWeight()
                    + " Person=" + entry.getValue().man.getName() +">");
        }
    }
}
