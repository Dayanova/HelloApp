package com.example.part1.lesson15.task1.db;

import com.example.part1.lesson15.task1.Model.Client;
import com.example.part1.lesson15.task1.Model.Product;
import com.example.part1.lesson15.task1.Model.Order;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAO implements GeneralDAO<Order> {
    private Logger logger;
    private Connection connection;

    public OrderDAO(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }

    @Override
    public Order getByPK(Integer key) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Order_1 WHERE id = ?")) {
            preparedStatement.setInt(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Order(
                            resultSet.getInt(1),
                            (Client) resultSet.getObject(2),
                            (Product) resultSet.getObject(3),
                            resultSet.getDate(4)

                    );
                }
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.warning("Error during rollback" + ex.getMessage());
            }
            logger.warning(e.getMessage());
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public void insert(Order object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Order_1 values (NEXT VALUE FOR Order_1_id, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(2, object.getClient().getId());
            preparedStatement.setInt(3, object.getProduct().getId());
            preparedStatement.setDate(4, (Date) object.getOrderDate());
            preparedStatement.executeUpdate();
            connection.commit();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedKeys.getLong(1);
                }
            }

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.warning("Error during rollback" + ex.getMessage());
            }
            logger.warning( e.getMessage());
        }

    }

    @Override
    public void update(Order object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Order_1 SET clientId =?, idProduct=? , OrderDate = ? " +
                        "WHERE id=?")) {
            preparedStatement.setInt(1, object.getClient().getId());
            preparedStatement.setInt(2, object.getProduct().getId());
            preparedStatement.setDate(3, (Date) object.getOrderDate());
            preparedStatement.setInt(4, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.warning("Error during rollback" + ex.getMessage());
            }
            logger.warning( e.getMessage());
        }
    }

    @Override
    public void delete(Order object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Delete from Order_1 where id =? ")) {
            preparedStatement.setInt(1, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.warning("Error during rollback" + ex.getMessage());
            }
            logger.warning(e.getMessage());
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> list = null;
        ClientDAO clientDAO = new ClientDAO(this.logger,this.connection);
        ProductDAO productDAO = new ProductDAO(this.logger,this.connection);
        String sql = "SELECT * FROM Order_1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("id"),
                                        clientDAO.getByPK(rs.getInt("ClientId")),
                                        productDAO.getByPK(rs.getInt("IdProduct")) ,
                                        null);
                boolean bool = rs.isClosed();
                if(bool) {
                    System.out.println("ResultSet is closed");
                } else {
                    System.out.println("ResultSet is not closed");
                }
                list.add(order);
            }


        } catch (Exception e) {
            logger.warning( e.getMessage());
        }
        return list;
    }

    @Override
    public void usePreparedStatement(List<Order> orderList) throws SQLException {
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try {
            String insertOrderSQL = "INSERT INTO Order_1 (ID, clientId,idProduct, orderDate) VALUES (NEXT VALUE FOR Order_1_id,?,?,?);";
            logger.info("usePreparedStatement Order");
            ClientDAO clientDAO = new ClientDAO(this.logger,this.connection);
            ProductDAO productDAO = new ProductDAO(this.logger,this.connection);
            PreparedStatement orderStmt = connection.prepareStatement(insertOrderSQL);
            Calendar calendar = new GregorianCalendar(2020, 0 , 01);

            for (Order order: orderList) {
                if (clientDAO.getByPK(order.getClient().getId()) == null) {
                    clientDAO.insert(order.getClient());
                    orderStmt.setInt(1, order.getProduct().getId());
                } else {
                    orderStmt.setInt(1, order.getClient().getId());
                }
                if (productDAO.getByPK(order.getProduct().getId())==null){
                    productDAO.insert(order.getProduct());
                    orderStmt.setInt(2, order.getProduct().getId());
                }
                else {
                    orderStmt.setInt(2, order.getProduct().getId());
                }
                orderStmt.setDate(3, (Date) order.getOrderDate());
                orderStmt.addBatch();
            }
            orderStmt.executeBatch();
            connection.commit();
        } catch (Exception e) {
            connection.rollback(savepointOne);
            logger.warning(e.getMessage());
        }
    }
}
