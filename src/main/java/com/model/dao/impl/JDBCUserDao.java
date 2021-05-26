package com.model.dao.impl;

import com.model.Status;
import com.model.dao.UserDao;
import com.model.dao.mapper.UserMapper;
import com.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class.getName());
    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login,password,typeUser,cash,status) VALUES (?,?,?,?,?)";
    public static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id LIKE (?)";
    public static final String SQL_FIND_ALL_USERS =
            "SELECT * FROM users";
    public static final String SQL_UPDATE_USER =
            "UPDATE users SET login = ?, password = ?, typeUser = ?, cash = ?,status= ? WHERE id = ?";
    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id=?";

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, Status.ACTIVE.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public User findById(long id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }


    @Override
    public List<User> findAll() {
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery((SQL_FIND_ALL_USERS));
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                userList.add(userMapper.extractFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole().getName());
            preparedStatement.setBigDecimal(4, entity.getCash());
            preparedStatement.setString(5, entity.getStatus().getName());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
