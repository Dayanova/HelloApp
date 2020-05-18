package com.example.part1.lesson08.task1;

import java.io.*;
/**
 * Класс для тестирования серилизации
 *
 * @ author Dayanova
 * @ version 1.0
 */
public class Serialize<T> implements Serializable {
    public T val;

    public Serialize(T val) {
        this.val = val;
    }
/** Метод для сериализации оюъектов
 * @param object - объект для серилизации
 * @param file - файл куда серилизовать
 * */
    public void serialize(T object, String file){
       try {
           ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream(file));
           os.writeObject(object);
           os.close();

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
/** Метод для десериализации
 * @param file - файл для десерили
 * @return объект десериализации
 * */
   public T deSerialize(String file) {
       T obj = null;
       try {
           ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
           obj= (T) is.readObject();

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return obj;
   }
}
