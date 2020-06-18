package com.example.part1.lesson15.task1.db;


import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;


public class AccessToFB {
    private String strConnect;
    private String strUser;
    private String strPass;
    private static final String JDBC = "jdbc:firebirdsql://";
    private static final String JDBC_PORT = ":3050/";

    private Connection connection = null;
    private Logger logger = null;

    private boolean connectToDB() {

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        } catch (ClassNotFoundException e) {
            logger.info("connectToDB: Firebird JCA-JDBC драйвер не найден");
            return false;
        }
        try {
            Properties connInfo = new Properties();

            connInfo.put("user", strUser);
            connInfo.put("password", strPass);
            connInfo.put("charSet", "Cp1251");

            connection = java.sql.DriverManager.getConnection(strConnect, connInfo);
            logger.info("connectToDB: БД подключена через JDBC драйвер");
            // выключим режим автокоммтов
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.info("connectToDB: ошибка подключения к БД через JDBC драйвер " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean closeAccess() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public AccessToFB(Logger log) {
        logger = log;
    }

    public boolean getAccess() {
        if (!setAccessParam()) return false;
        if (!connectToDB()) return false;
        return true;
    }

    private boolean setAccessParam() {
        strConnect ="C:\\Users\\Фаягуль\\IdeaProjects\\HelloApp\\src\\main\\java\\com\\example\\part1\\lesson15\\task1\\db\\STORE.FDB";
        if (!Files.exists(Paths.get(strConnect), LinkOption.NOFOLLOW_LINKS)){
            logger.info("Нет найден файл БД: "+strConnect);
            return false;
        }
        if (Objects.equals(strConnect, "")) {
            logger.info("Нет доступа к БД: отсутствует путь к файлу БД");
            return false;
        }
        strConnect = JDBC + "localhost" + JDBC_PORT + strConnect;
        logger.info("Connect: " + strConnect);

        strUser = "";
        if (Objects.equals(strUser, "")) {
            strUser = "SYSDBA";
        }
        logger.info("User: " + strUser);

        strPass = "";
        if (Objects.equals(strPass, "")) {
            strPass = "masterkey";
        }
        logger.info("Password: " + strPass);
        return true;
    }

    public Connection getConnection() {
        return connection;
    }



}
