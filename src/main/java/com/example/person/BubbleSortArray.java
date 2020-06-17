package com.example.person;

/**
* Класс сортировки пузырьком
* @ author Dayanova
* @ version 1.0
 */
public class BubbleSortArray implements SortIntf  {
    /**
     * метод меняет местами пару чисел массива
     * @param pers - массив person
     * @param first - первое число
     * @param second - второе число
     * */
    private static void Swap(Person[] pers, int first, int second){ //
        Person vPers= pers[first];
        pers[first] = pers[second];
        pers[second] = vPers;
    }
    /**
     * МЕТОД ПУЗЫРЬКОВОЙ СОРТИРОВКИ
     * @param personSort - массив person
     * @return - отсортированный массив person
     * */
    public static Person [] bubbleSortArray(Person[] personSort){
        int size = personSort.length;
        for (int out = size - 1; out >= 1; out--){
            for (int in = 0; in < out; in++) {
                if (personSort[in].compareTo(personSort[in + 1]) == 10) {
                    Swap(personSort, in, in + 1);
                }
            }
        }
        return personSort;
    }
}
