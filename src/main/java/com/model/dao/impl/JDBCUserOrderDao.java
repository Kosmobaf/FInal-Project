package com.model.dao.impl;

import com.model.bean.UserOrderBean;
import com.model.dao.UserOrderDao;
import com.model.dao.mapper.TariffMapper;
import com.model.dao.mapper.UserOrderMapper;
import com.model.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCUserOrderDao implements UserOrderDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCUserOrderDao.class.getName());
    private final Connection connection;

    public JDBCUserOrderDao(Connection connection) {
        this.connection = connection;
    }

    public static final String SQL_FIND_ALL_TARIFF_BY_ID_USER =
            "SELECT users_orders.user_id, " +
                    "tariff.id_service, tariff.id,tariff.nameTariff,tariff.cost " +
                    "FROM users_orders " +
                    "JOIN tariff ON users_orders.tariff_id = tariff.id " +
                    "WHERE users_orders.user_id LIKE (?)";
    private static final String SQL_INSERT_USERS_ORDERS =
            "INSERT INTO users_orders (user_id,tariff_id,status,dateAdd) VALUES (?,?,?,?)";
    public static final String SQL_FIND_USERS_ORDERS_BY_ID =
            "SELECT users_orders.id, users_orders.user_id, " +
                    "users_orders.tariff_id, users_orders.status," +
                    "users_orders.dateAdd, provider.tariff.nameTariff, " +
                    "provider.services.nameService FROM users_orders " +
                    "JOIN tariff ON users_orders.tariff_id = tariff.id " +
                    "JOIN services ON tariff.id_service = services.id WHERE users_orders.id LIKE (?)";
    public static final String SQL_FIND_ALL_USERS_ORDERS =
            "SELECT users_orders .id, users_orders.user_id, " +
                    "users_orders.tariff_id, users_orders.status," +
                    "users_orders.dateAdd, provider.tariff.nameTariff, " +
                    "provider.services.nameService FROM users_orders " +
                    "JOIN tariff ON users_orders.tariff_id = tariff.id " +
                    "JOIN services ON tariff.id_service = services.id";
    public static final String SQL_UPDATE_USERS_ORDERS =
            "UPDATE users_orders SET user_id = ?, tariff_id = ?, status = ?, dateAdd = ? WHERE id = ?";
    public static final String SQL_DELETE_USERS_ORDERS_BY_ID =
            "DELETE FROM users_orders WHERE id=?";
    public static final String SQL_FIND_USER_ORDER_BY_ID_USER_AND_ID_TARIFF =
            "SELECT users_orders .id, users_orders.user_id, " +
                    "users_orders.tariff_id, users_orders.status, " +
                    "users_orders.dateAdd, provider.tariff.nameTariff, " +
                    "provider.services.nameService FROM users_orders " +
                    "JOIN tariff ON users_orders.tariff_id = tariff.id " +
                    "JOIN services ON tariff.id_service = services.id WHERE users_orders.user_id=? AND users_orders.tariff_id=?";
    public static final String SQL_FIND_USERS_ORDERS_BY_ID_USER =
            "SELECT users_orders.id, users_orders.user_id, " +
                    "users_orders.tariff_id, users_orders.status," +
                    "users_orders.dateAdd, provider.tariff.nameTariff, " +
                    "provider.services.nameService FROM users_orders " +
                    "JOIN tariff ON users_orders.tariff_id = tariff.id " +
                    "JOIN services ON tariff.id_service = services.id WHERE users_orders.user_id LIKE (?)";

    @Override
    public void create(UserOrderBean bean) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_USERS_ORDERS)) {
            preparedStatement.setLong(1, bean.getUserId());
            preparedStatement.setLong(2, bean.getTariffId());
            preparedStatement.setString(3, bean.getStatus());
            preparedStatement.setString(4, bean.getDateAdd());
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
        List<UserOrderBean> beans = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
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

    @Override
    public List<UserOrderBean> findAllOrdersByIdUser(Long id) {
        ResultSet resultSet = null;
        List<UserOrderBean> beans = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS_ORDERS_BY_ID_USER)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            UserOrderMapper orderMapper = new UserOrderMapper();

            while (resultSet.next()) {
                beans.add(orderMapper.extractFromResultSet(resultSet));
            }
            return beans;

        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public List<Tariff> findAllTariffByIdUser(long idUser) {
        ResultSet resultSet = null;
        List<Tariff> tariffs = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TARIFF_BY_ID_USER)) {
            preparedStatement.setLong(1, idUser);
            resultSet = preparedStatement.executeQuery();
            TariffMapper mapper = new TariffMapper();
            while (resultSet.next()) {
                tariffs.add(mapper.extractFromResultSet(resultSet));
            }
            return tariffs;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public UserOrderBean findByIdTariffAndIdUser(long idTariff, long idUser) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_ORDER_BY_ID_USER_AND_ID_TARIFF)) {

            preparedStatement.setLong(1, idUser);
            preparedStatement.setLong(2, idTariff);
            resultSet = preparedStatement.executeQuery();
            UserOrderMapper orderMapper = new UserOrderMapper();
            if (resultSet.next()) {
                return orderMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
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
            preparedStatement.setString(3, bean.getStatus());
            preparedStatement.setString(4, bean.getDateAdd());
            preparedStatement.setLong(5, bean.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
