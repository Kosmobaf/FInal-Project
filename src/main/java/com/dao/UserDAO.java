package com.dao;

import com.Fields;
import com.entity.User;
import com.sql.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login,password,typeUser,cash) VALUES (?,?,?,?)";
    public static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login LIKE (?)";


    public void insertUser(User user) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USER)) {
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
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong(Fields.ENTITY__ID),
                        resultSet.getString(Fields.USER__LOGIN),
                        resultSet.getString(Fields.USER__PASSWORD),
                        resultSet.getString(Fields.USER__TYPE),
                        resultSet.getDouble(Fields.USER__CASH)
                );
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
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("can`t delate user" + userName);
            LOGGER.severe(e.getMessage());
        }
    }
}
