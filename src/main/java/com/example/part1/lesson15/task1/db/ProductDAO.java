package com.example.part1.lesson15.task1.db;

import com.example.part1.lesson15.task1.Model.Product;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAO implements GeneralDAO<Product> {
    private Logger logger;
    private Connection connection;


    public ProductDAO(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;
    }


    @Override
    public Product getByPK(Integer key) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Product WHERE id = ?")) {
            preparedStatement.setInt(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                            );
                }
            }
        }
        catch (NullPointerException e){
            return null;
        }
        catch (Exception e) {
            logger.warning(e.getMessage());
            e.printStackTrace(System.out);
        }
        return null;

    }

    @Override
    public void insert(Product object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Product values (NEXT VALUE FOR Product_id, ?, ?, ? )")) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setInt(3, object.getPrice());
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
    public void update(Product object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Product SET Name =?, Address=? " +
                        "WHERE id=?")) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setDouble(3, object.getPrice());
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
    public void delete(Product object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Delete from Product where id =? ")) {
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
    public List<Product> getAll() {
        List<Product> list = null;
        String sql = "SELECT * FROM Product";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"));
                list.add(product);
            }

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        return list;
    }

    @Override
    public void usePreparedStatement(List<Product> products) throws SQLException {
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try {
            String insertProductSQL = "INSERT INTO Product (ID, name, description,price) VALUES (NEXT VALUE FOR Product_id,?,?,?);";
            PreparedStatement productStmt = connection.prepareStatement(insertProductSQL);

            for (Product product: products) {
                productStmt.setString(1, product.getName());
                productStmt.setString(2, product.getDescription());
                productStmt.setDouble(3, product.getPrice());
                productStmt.addBatch();
            }
            productStmt.executeBatch();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback(savepointOne);
            } catch (SQLException ex) {
                logger.warning("Error during rollback" + ex.getMessage());
            }
            logger.warning(e.getMessage());
        }

    }
}
