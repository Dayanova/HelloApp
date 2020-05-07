package com.example.person;

import java.util.Arrays;
/**
 * Класс сортировки слиянием
 * @ author Dayanova
 * @ version 1.0
 */
public class MergeSortArray implements SortIntf  {
    /**
     * рекурсивная функция сортировки частей массива {@link Person#genderPerson}
     * @return возвращает {@link Person}
     * */

    public static Person [] sortMerge (Person [] elements) {
        int len = elements.length;
        if (len<2) return elements;
        int mid = len / 2;
        Person [] l = new Person [mid];
        Person [] r = new Person [len - mid];
        return merge( sortMerge(Arrays.copyOfRange(elements,0,mid)),
                sortMerge(Arrays.copyOfRange(elements,mid,len )) );
        }
    /**
     * слияние двух массивов в один отсортированный {@link Person#genderPerson}
     * @return возвращает {@link Person}
     * */
    private static Person[] merge(Person[] arr_1, Person[] arr_2) {
        int len = arr_1.length + arr_2.length;
        int posA = 0, posB = 0;
        Person[] result = new Person[len];
        for (int i = 0; i < result.length; i++) {
            if (posB < arr_2.length && posA < arr_1.length) {
                if (arr_1[posA].compareTo(arr_2[posB]) ==10)
                     result[i] = arr_2[posB++];
                else result[i] = arr_1[posA++];
            } else if (posB < arr_2.length) {
                result[i] = arr_2[posB++];
            } else {
                result[i] = arr_1[posA++];
            }
        }
        return result;
    }
}
