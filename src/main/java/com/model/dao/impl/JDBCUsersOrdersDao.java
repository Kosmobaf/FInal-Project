package com.model.dao.impl;

import com.model.bean.UserOrderBean;
import com.model.dao.UsersOrdersDao;
import com.model.dao.mapper.UserMapper;
import com.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCUsersOrdersDao implements UsersOrdersDao {
    private final Connection connection;

    public JDBCUsersOrdersDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCUsersOrdersDao.class.getName());
    private static final String SQL_INSERT_USERS_ORDERS =
            "INSERT INTO users_orders (user_id,tariff_id,isActive,dateAdd) VALUES (?,?,?,?)";
    public static final String SQL_FIND_USERS_ORDERS_BY_ID =
            "SELECT * FROM users_orders WHERE id LIKE (?)";
    public static final String SQL_FIND_ALL_USERS_ORDERS =
            "SELECT * FROM users_orders";
    public static final String SQL_UPDATE_USERS_ORDERS =
            "UPDATE users_orders SET user_id = ?, tariff_id = ?, isActive = ?, dateAdd = ? WHERE id = ?";
    public static final String SQL_DELETE_USERS_ORDERS_BY_ID =
            "DELETE FROM users_orders WHERE id=?";
    public static final String SQL_FIND_USERS_ORDERS_BY_ID_USER =
            "SELECT * FROM users_orders WHERE user_id LIKE (?)";

    @Override
    public void create(UserOrderBean bean) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USERS_ORDERS)) {
            preparedStatement.setLong(1, bean.getUserId());
            preparedStatement.setString(2, bean.getPassword());
            preparedStatement.setString(3, bean.getRole().getName());
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public UserOrderBean findById(long id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS_ORDERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());

        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USERS_ORDERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }


    @Override
    public List<UserOrderBean> findAll() {
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery((SQL_FIND_ALL_USERS_ORDERS));
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                userList.add(userMapper.extractFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());

        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public void update(UserOrderBean bean) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USERS_ORDERS)) {
            preparedStatement.setString(1, bean.getLogin());
            preparedStatement.setString(2, bean.getPassword());
            preparedStatement.setString(3, bean.getRole().getName());
            preparedStatement.setBigDecimal(4, bean.getCash());
            preparedStatement.setLong(6, bean.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
