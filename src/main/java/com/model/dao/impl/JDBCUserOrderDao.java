package com.model.dao.impl;

import com.model.bean.UserOrderBean;
import com.model.dao.UserOrderDao;
import com.model.dao.mapper.UserOrderMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCUserOrderDao implements UserOrderDao {
    private final Connection connection;

    public JDBCUserOrderDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCUserOrderDao.class.getName());
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
            preparedStatement.setLong(2, bean.getTariffId());
            preparedStatement.setBoolean(3, bean.isActive());
            preparedStatement.setString(4,bean.getDateAdd());
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
                UserOrderMapper mapper = new UserOrderMapper();
                return mapper.extractFromResultSet(resultSet);
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
        try (Statement st = connection.createStatement()) {
            List<UserOrderBean> beans = new ArrayList<>();
            resultSet = st.executeQuery((SQL_FIND_ALL_USERS_ORDERS));
            UserOrderMapper orderMapper = new UserOrderMapper();
            while (resultSet.next()) {
                beans.add(orderMapper.extractFromResultSet(resultSet));
            }
            return beans;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());

        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }
    public List<UserOrderBean> findAllByIdUser(Long id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS_ORDERS_BY_ID_USER)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            List<UserOrderBean> beans = new ArrayList<>();
            UserOrderMapper orderMapper = new UserOrderMapper();
            while (resultSet.next()) {
                beans.add(orderMapper.extractFromResultSet(resultSet));
            }
            return beans;
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
            preparedStatement.setLong(1, bean.getUserId());
            preparedStatement.setLong(2, bean.getTariffId());
            preparedStatement.setBoolean(3, bean.isActive());
            preparedStatement.setString(4,bean.getDateAdd());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
