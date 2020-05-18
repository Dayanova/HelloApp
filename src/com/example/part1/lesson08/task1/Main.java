package com.example.part1.lesson08.task1;

import com.example.person.Person;
import com.example.person.Sex;

public class Main {
    public static void main(String[] args) {
        String Path = "C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\com\\example\\part1\\lesson08\\task1\\tsk.ser";
        int r=1;

        Serialize ser = new Serialize<Integer>(r);
        ser.serialize(ser,Path);

        ser= (Serialize) ser.deSerialize(Path);
        System.out.println("ser.number1=" + ser.val);


    }

}
