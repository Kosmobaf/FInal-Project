package com.model.dao.impl;

import com.controller.MyException;
import com.model.Status;
import com.model.dao.dao_factory.UserDao;
import com.model.dao.mapper.UserMapper;
import com.model.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private static final Logger log = Logger.getLogger(JDBCUserDao.class);

    private final Connection connection;


    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_USER =
            "INSERT INTO users (login,password,typeUser,cash,status) VALUES (?,?,?,?,?)";
    public static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id LIKE (?)";
    public static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login LIKE (?)";
    public static final String SQL_FIND_ALL_USERS =
            "SELECT * FROM users";
    public static final String SQL_UPDATE_USER =
            "UPDATE users SET login = ?, password = ?, typeUser = ?, cash = ?, status = ? WHERE id = ?";
    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id=?";

    @Override
    public void create(User user) throws MyException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, Status.ACTIVE.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot create user " + user, e);
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

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
        return null;
    }

    @Override
    public void delete(long id) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot delete user " + id, e);
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
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
    }

    @Override
    public void update(User user) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().getName());
            preparedStatement.setBigDecimal(4, user.getCash());
            preparedStatement.setString(5, user.getStatus().getName());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot update user " + user, e);
        }
    }

    @Override
    public User findByLogin(String login) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {

            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            close(resultSet);
        }
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }
}
