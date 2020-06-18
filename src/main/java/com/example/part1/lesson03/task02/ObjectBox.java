package com.example.part1.lesson03.task02;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ObjectBox <T> {


    public HashSet<T> sList= new HashSet<>();
    private T value;

    public ObjectBox(T[] o) {
        for (int i = 0; i < o.length; i++) {
            sList.add(o[i]);
        }
    }

    /**
     *
     * @param val
     */
    public void add(T val) {
         sList.add(val);
    }

    public void dump() {
        for (Iterator<T> i = sList.iterator(); i.hasNext(); ) {
            T o = (T) i.next();
            System.out.println(o);
        }
    }

    public void removeDuplicate (T o) {
        Iterator it = sList.iterator();
        int i = 0;
        while (it.hasNext()) {
            ObjectBox lst = (ObjectBox) it.next();
            if (lst.equals(o)) {
               it.remove();
            }
        }
    }
}