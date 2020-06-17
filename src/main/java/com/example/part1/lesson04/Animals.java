package com.example.part1.lesson04;
import com.example.person.Person;
import com.example.person.Sex;

import java.util.*;

public class Animals {

    public  Map<Integer, Cat> animal =new HashMap<>();

    public Animals(List<Cat> cats) {
        this.animal=generateAnimals(cats);
    }

    /**
     * Функция обновления значения @link Cat}
     * @return возвращает обновленный Map
     */
    public void updateCat(Integer uniqueKey,Cat o){
        if (this.animal.containsKey(uniqueKey)){
            this.animal.put(uniqueKey,o);
        }
    }
    /**
     * Функция Гененарции картотеки животных @link Cat}
     * @return возвращает Map
     */
    public Map generateAnimals(List<Cat> animals) {
        int key=0;
        Map<Integer, Cat> result = new HashMap<>();
        for (Cat cat : animals) {
            key+=1;
            if (result.containsValue(cat)) {
                throw new ArithmeticException("duplication of values in an array");
            }
            result.put(key, cat);
        }
        return result;
    }
    /**
     * Функция поиска по кличке в картотеке животных @link Cat}
     * @return возвращает Map
     */
    public Cat searchCat(String nickname){
        Cat cat;
        Map<String, Cat> cats =new HashMap<>();
        List<Map.Entry<Integer, Cat>> list =
                new LinkedList<Map.Entry<Integer, Cat>>(this.animal.entrySet());

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
    public LinkedHashMap<Integer, Cat> sortByValue() {
        List<Map.Entry<Integer, Cat>> list =
                new LinkedList<Map.Entry<Integer, Cat>>(this.animal.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Cat>>() {
            public int compare(Map.Entry<Integer, Cat> o1,
                               Map.Entry<Integer, Cat> o2) {
                int result = o1.getValue().man.getName().compareTo(o2.getValue().man.getName());
                if (result != 0) {
                    return result;
                }
                     result = o1.getValue().getnickname().compareTo(o2.getValue().getnickname());
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
        LinkedHashMap<Integer, Cat> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Cat> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    /**
     * Функция печати картотеки животных @link Cat}
     * @return возвращает Map
     */

    public void printMap() {
        for (Map.Entry<Integer, Cat> entry : this.animal.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value :< Nick= " + entry.getValue().getnickname()
                    + " weight=" + entry.getValue().getWeight()
                    + " Person=" + entry.getValue().man.getName() +">");
        }
    }
}
