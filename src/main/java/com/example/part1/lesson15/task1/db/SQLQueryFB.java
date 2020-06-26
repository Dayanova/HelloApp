package com.example.part1.lesson15.task1.db;

import java.sql.*;
import org.apache.logging.log4j.Logger;

public class SQLQueryFB {
    private Logger logger;
    private Connection connection;
    private Statement statement;

    public SQLQueryFB(Logger logger, Connection connection) throws SQLException {
        this.logger = logger;
        this.connection = connection;
        statement = connection.createStatement();
    }

    public boolean deleteAllDataFromTable(String strTableName) {
        String strSQL = "";
        strSQL += "DELETE FROM " + strTableName;
        return execStatement(strSQL);
    }

    public boolean DropFromTable(String strTableName) {
        logger.trace("Drop Table " + strTableName);
        String strSQL = "";
        strSQL += "DROP TABLE " + strTableName;
        return execStatement(strSQL);
    }

    private boolean execStatement(String strSQL) {
      logger.info(strSQL);
        try {
            statement.execute(strSQL);
        } catch (SQLException e) {
            logger.error("Ошибка statement.execute: " + e.getMessage());
            return false;
        }
        return true;
    }
    public void createLogTab(){
        String strSQL = null;
        try {
            strSQL = "CREATE TABLE APP_LOGS" +
                    "   (LOG_ID CHAR (255)   NOT NULL," +
                    "    ENTRY_DATE   DATE           NOT NULL," +
                    "    LOGGER  VARCHAR(50)    NOT NULL," +
                    "    LOG_LEVEL   VARCHAR(10)    NOT NULL," +
                    "    MESSAGE VARCHAR(1000)  NOT NULL," +
                    "    EXCEPTION VARCHAR(1000)  NOT NULL" +
                    "   );";
            execStatement(strSQL);
        }
        catch(Exception e) {
           e.printStackTrace();
        }
    }

    public void createTable() {
        String strSQL = null;
        try {
            logger.trace("Создание таблицы Client");
            if (!isTableExists("Client")) {
                strSQL = "CREATE TABLE Client (" +
                        "id INTEGER PRIMARY KEY," +
                        "Name CHAR (255)," +
                        "Address CHAR (255)" +
                        ")";
                execStatement(strSQL);
              //  strSQL = " CREATE SEQUENCE Client_id;";
               // execStatement(strSQL);
            }

            logger.trace("Создание таблицы Order");
            if (!isTableExists("Order_1")) {
                strSQL = "CREATE TABLE Order_1 (" +
                        "id INTEGER PRIMARY KEY," +
                        "ClientId INTEGER not null," +
                        "IdProduct INTEGER not null," +
                        "OrderDate DATE" +
                        ")";
                execStatement(strSQL);
               // strSQL = " CREATE SEQUENCE Order_1_id;";
              //  execStatement(strSQL);
            }

            logger.trace("Создание таблицы Product");
            if (!isTableExists("Product")) {
                strSQL = "CREATE TABLE Product (" +
                        "id INTEGER PRIMARY KEY," +
                        "Name CHAR (255)," +
                        "Description CHAR (255)," +
                        "Price INTEGER" +
                        ")";
                execStatement(strSQL);
                //strSQL = " CREATE SEQUENCE Product_id;";
                // execStatement(strSQL);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public boolean isTableExists(String tableName) {
        logger.trace("Exists Table " + tableName);
        String strSQL = "SELECT RDB$RELATION_NAME FROM RDB$RELATIONS WHERE RDB$RELATION_NAME = '" + tableName.toUpperCase() + "'";
        ResultSet resultSet;
        try {
            resultSet = statement != null ? statement.executeQuery(strSQL) : null;
            if (resultSet != null) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            logger.error("Ошибка обращения к таблице: " + tableName);
        }
        return false;
    }


}