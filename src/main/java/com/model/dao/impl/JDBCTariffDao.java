package com.model.dao.impl;

import com.model.dao.TariffDao;
import com.model.dao.mapper.TariffMapper;
import com.model.entity.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCTariffDao implements TariffDao {
    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCTariffDao.class.getName());
    private static final String SQL_INSERT_TARIFF =
            "INSERT INTO tariff (nameTariff, id_service, cost) VALUES (?,?,?)";
    public static final String SQL_FIND_TARIFF_BY_ID =
            "SELECT * FROM tariff WHERE id LIKE (?)";
    public static final String SQL_FIND_TARIFF_BY_NAME =
            "SELECT * FROM tariff WHERE nameTariff LIKE (?)";
    public static final String SQL_FIND_ALL_TARIFFS =
            "SELECT * FROM tariff";
    public static final String SQL_UPDATE_TARIFFS =
            "UPDATE tariff SET nameTariff = ?, id_service = ?, cost = ? WHERE id = ?";
    public static final String SQL_DELETE_TARIFF_BY_ID =
            "DELETE FROM tariff WHERE id=?";

    @Override
    public void create(Tariff tariff) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_TARIFF)) {
            preparedStatement.setString(1, tariff.getNameTariff());
            preparedStatement.setLong(2, tariff.getIdServices());
            preparedStatement.setBigDecimal(3, tariff.getCost());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public Tariff findById(long id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TARIFF_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TariffMapper tariffMapper = new TariffMapper();
                return tariffMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public List<Tariff> findAll() {
        ResultSet resultSet = null;
        List<Tariff> tariffList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery((SQL_FIND_ALL_TARIFFS));
            TariffMapper tariffMapper = new TariffMapper();
            while (resultSet.next()) {
                tariffList.add(tariffMapper.extractFromResultSet(resultSet));
            }
            return tariffList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }

    @Override
    public void update(Tariff tariff) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TARIFFS)) {
            preparedStatement.setString(1, tariff.getNameTariff());
            preparedStatement.setLong(2, tariff.getIdServices());
            preparedStatement.setBigDecimal(3, tariff.getCost());
            preparedStatement.setLong(3, tariff.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        throw new RuntimeException();
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TARIFF_BY_ID)) {
            preparedStatement.setLong(1, id);
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

    @Override
    public Tariff findByName(String nameTariff) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TARIFF_BY_NAME)) {
            preparedStatement.setString(1, nameTariff);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TariffMapper tariffMapper = new TariffMapper();
                return tariffMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
    }
}
