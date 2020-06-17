package com.example.helloworld;

class PrintMess {
    public void printMessage() {
        System.out.println("Hello, World!");
    }
}
/**
 * Класс для вывода ошибки «NullPointerException»
 * @ author Dayanova
 * @ version 1.0
 */
 public class HelloWorld {

    public static void main(String[] args) {
        PrintMess Message1= null;
        Message1.printMessage();
    }
}

/**
 * Класс для вывода ошибки ««ArrayIndexOutOfBoundsException»»
 * @ author Dayanova
 * @ version 1.0
 */
class PrintArray {

    public static void main(String[] args) {
        String[] Customer = new String[] {"Andrey", "Sergey" };
        System.out.println(Customer[2]);
    }

}
/**
 * Класс для вывода своего варианта ошибки через оператор throw
 * @ author Dayanova
 * @ version 1.0
 */
class printHelloWorld {

    public static void main(String[] args) {
        PrintMess Message1= null;
        if (Message1 == null)
            throw new IllegalArgumentException("The argument cannot be null");
    }
}
