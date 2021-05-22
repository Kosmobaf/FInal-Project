package com.model.dao.impl;

import com.model.Fields;
import com.model.Role;
import com.model.Status;
import com.model.builder.UserBuilder;
import com.model.dao.UserDao;
import com.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO implements UserDao {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login,password,typeUser,cash) VALUES (?,?,?,?,?)";
    public static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login LIKE (?)";
    public static final String SQL_DELETE_BY_LOGIN =
            "DELETE FROM users WHERE login=?";

@Override
    public void create(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USER)) {
            //TODO добавити транзакцію
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5,Status.ACTIVE.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("User create");
            LOGGER.severe(e.getMessage());
        }
    }

    public  User getUserByLogin(String login) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserBuilder builder = new UserBuilder();
                builder.setId(resultSet.getLong(Fields.ENTITY__ID));
                builder.setLogin(resultSet.getString(Fields.USER__LOGIN));
                builder.setPassword(resultSet.getString(Fields.USER__PASSWORD));
                builder.setRole(Role.valueOf(resultSet.getString(Fields.USER__ROLE)));
                builder.setCash(resultSet.getDouble(Fields.USER__CASH));
                builder.setStatus(Status.valueOf(resultSet.getString(Fields.USER__STATUS)));
                return builder.getResult();
            }
        } catch (SQLException e) {
            System.out.println("user not found");
            LOGGER.severe(e.getMessage());
        }
        return null;
    }


    public void deleteUserByName(String userName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_LOGIN)) {
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("can`t delate user -" + userName);
            LOGGER.severe(e.getMessage());
        }
        System.out.println("User deleted");
    }



    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
