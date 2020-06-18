package com.example.part1.lesson15.task1.db;

import com.example.part1.lesson15.task1.Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;

public class ClientDAO implements GeneralDAO<Client> {
    private Logger logger;
    private Connection connection;


    public ClientDAO(Logger logger, Connection connection) {
        this.logger = logger;
        this.connection = connection;

    }

    @Override
    public Client getByPK(Integer key) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Client WHERE id = ?")) {
            preparedStatement.setInt(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3));
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
    public void insert(Client object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Client values (NEXT VALUE FOR Client_id, ?, ?)")) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
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
    public void update(Client object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Client SET Name =?, Address=? " +
                        "WHERE id=?")) {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
            preparedStatement.setInt(3, object.getId());
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
    public void delete(Client object) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Delete from Client where id =? ")) {
            preparedStatement.setInt(1, object.getId());
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
    public void usePreparedStatement(List<Client> clientList) throws SQLException {
        Savepoint savepointOne = connection.setSavepoint("SavepointOne");
        try {
            String insertEmployeeSQL = "INSERT INTO Client(ID, NAME, Address) VALUES (NEXT VALUE FOR Client_id,?,?);";
            PreparedStatement employeeStmt = connection.prepareStatement(insertEmployeeSQL);

            for (Client client: clientList) {
                employeeStmt.setString(2, client.getName());
                employeeStmt.setString(3, client.getAddress());
                employeeStmt.addBatch();
            }
            employeeStmt.executeBatch();
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

    @Override
    public List<Client> getAll() {
        List<Client> list = new ArrayList<>();
        String sql ="SELECT * FROM Client" ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Client client = new Client(rs.getInt("id"),
                                                rs.getString("name"),
                                                rs.getString("address") );
                    list.add(client);
                }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
        return list;
    }
}
