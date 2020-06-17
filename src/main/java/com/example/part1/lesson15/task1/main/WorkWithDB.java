package com.example.part1.lesson15.task1.main;

import com.example.part1.lesson15.task1.Model.Client;
import com.example.part1.lesson15.task1.Model.Order;
import com.example.part1.lesson15.task1.Model.Product;
import com.example.part1.lesson15.task1.db.AccessToFB;
import com.example.part1.lesson15.task1.db.OrderDAO;
import com.example.part1.lesson15.task1.db.SQLQueryFB;

import java.util.Calendar;
import java.util.GregorianCalendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class WorkWithDB {

    public static void main(String[] args) throws SQLException {
        Logger log = Logger.getGlobal();
        AccessToFB dbs = new AccessToFB(log) ;
        Boolean s = dbs.getAccess();
        SQLQueryFB qr = new SQLQueryFB(log, dbs.getConnection());
      //  qr.deleteAllDataFromTable("Order_1");
      //  qr.deleteAllDataFromTable("Client");
      //  qr.deleteAllDataFromTable("Product");
      //  qr.DropFromTable("Order_1");
     //   qr.DropFromTable("Client");
    //    qr.DropFromTable("Product");
        qr.createTable();
        List<Order> ORDER = getOrder();
       OrderDAO orderDAO = new OrderDAO(log, dbs.getConnection());
       orderDAO.usePreparedStatement(ORDER);
    /*  List<Order> result =orderDAO.getAll();

       for (Order order: result){
            System.out.println(order.toString());
        }
*/
    }

    static List<Order> getOrder() {
        Calendar calendar = new GregorianCalendar(2020, 0 , 1);
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return new ArrayList<>(Arrays.asList(
                new Order(1,new Client(1,"Alis","Moscow"),
                                new Product(1,"tea","El",15),sqlDate),
                new Order(2,new Client(2,"Tea","London"),
                        new Product(2,"Car","El",100), sqlDate),
                new Order(3,new Client(3,"Mary","Paris"),
                        new Product(3,"Coffie","El",5), sqlDate),
                new Order(4,new Client(4,"Andy","Berlin"),
                        new Product(4,"Apple","El",3),sqlDate),
                new Order(5,new Client(5,"Andy","New Work"),
                        new Product(5,"tomat","El",2),sqlDate)
        ));
    }
}
