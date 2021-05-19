package com.db_meneger;

import com.entity.Services;
import com.entity.Tariff;
import com.entity.User;

import javax.ejb.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public final class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());
    private static DBManager dbManager = null;

    public static DBManager getInstance() {
        DBManager result = dbManager;
        if (result != null) {
            return result;
        }
        synchronized (DBManager.class) {
            if (dbManager == null) {
                dbManager = new DBManager();
                new CreateDataBase(dbManager).createDB();
            }
            return dbManager;
        }
    }

    public Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }


    public void insertUser(User user) {
        String query = "INSERT INTO users (login,password,typeUser,cash) VALUES (?,?,?,?)";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            //TODO добавити транзакцію
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getTypeUser());
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public User getUser(String userName) {
        String query = "SELECT * FROM users WHERE login LIKE (?)";
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("typeUser"),
                        resultSet.getDouble("cash"),
                        new ArrayList<>());
                //TODO зробити повернення листа
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return null;
    }


    public void deleteUser(String userName) {
        String query = "DELETE FROM users WHERE login=?";
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("can`t delate user" + userName);
            LOGGER.severe(e.getMessage());
        }
    }

    public List<Services> getAllServicesAndTariff() {
        List<Services> servicesList = new ArrayList<>();
        String query = "SELECT * FROM services nameService ";
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                servicesList.add(new Services(resultSet.getString("nameService"),
                        getAllTariffForOneService(resultSet.getString("nameService"))));
            }
            return servicesList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return null;
    }

    public List<Tariff> getAllTariffForOneService(String service) {
        List<Tariff> tariffList = new ArrayList<>();
        String query = "SELECT * FROM tariff WHERE nameService = ?";
        ResultSet resultSet = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, service);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariffList.add(new Tariff(resultSet.getString(""), resultSet.getDouble("cost")));
            }
            return tariffList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return null;
    }
}


