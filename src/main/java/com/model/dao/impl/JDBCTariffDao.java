package com.model.dao.impl;

import com.controller.MyException;
import com.model.dao.dao_factory.TariffDao;
import com.model.dao.mapper.TariffMapper;
import com.model.entity.Tariff;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTariffDao implements TariffDao {
    private static final Logger log = Logger.getLogger(JDBCTariffDao.class);

    private static final String SORT_BY_NAME = "sortByName";
    private static final String SORT_BY_NAME_REVERSE = "sortByNameReverse";
    private static final String SORT_BY_COAST = "sortByCoast";

    private final Connection connection;

    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    public static final String SELECT_FOUND_ROWS = "SELECT COUNT(id) FROM tariff";
    private static final String SQL_INSERT_TARIFF =
            "INSERT INTO tariff (nameTariff, id_service, cost) VALUES (?,?,?)";
    public static final String SQL_FIND_TARIFF_BY_ID =
            "SELECT * FROM tariff WHERE id LIKE (?)";
    private static final String SQL_FIND_ALL_TARIFFS =
            "SELECT * FROM tariff";
    private static final String SQL_FIND_ALL_TARIFFS_BY_SERVICE =
            "SELECT * FROM tariff WHERE id_service = ?";
    public static final String SQL_FIND_TARIFF_BY_NAME =
            "SELECT * FROM tariff WHERE nameTariff LIKE (?)";
    public static final String SQL_FIND_ALL_TARIFFS_FOR_PAGE =
            "SELECT SQL_CALC_FOUND_ROWS * FROM tariff LIMIT ?, ?";
    public static final String SQL_UPDATE_TARIFFS =
            "UPDATE tariff SET nameTariff = ?, id_service = ?, cost = ? WHERE id = ?";
    public static final String SQL_DELETE_TARIFF_BY_ID =
            "DELETE FROM tariff WHERE id=?";
    private static final String SQL_FIND_ALL_TARIFFS_SORT_BY_NAME =
            "SELECT * FROM tariff WHERE id_service = ? ORDER BY nameTariff";
    private static final String SQL_FIND_ALL_TARIFFS_SORT_BY_NAME_REVERSE =
            "SELECT * FROM tariff WHERE id_service = ? ORDER BY nameTariff DESC ";
    private static final String SQL_FIND_ALL_TARIFFS_SORT_BY_COAST =
            "SELECT * FROM tariff WHERE id_service = ? ORDER BY cost";

    @Override
    public void create(Tariff tariff) throws MyException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_TARIFF)) {
            preparedStatement.setString(1, tariff.getNameTariff());
            preparedStatement.setLong(2, tariff.getIdServices());
            preparedStatement.setBigDecimal(3, tariff.getCost());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot create tariff" + tariff, e);
        }
    }

    @Override
    public int getNoOfRecords() {
        ResultSet resultSet = null;
        try (Statement st = connection.createStatement()) {
            resultSet = st.executeQuery((SELECT_FOUND_ROWS));
            if (resultSet.next())

                return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
        throw new RuntimeException();
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
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
        return null;
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
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
    }

    @Override
    public List<Tariff> findTariffsFromPage(int offset, int noOfRecords) {
        List<Tariff> tariffList = new ArrayList<>();
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_FIND_ALL_TARIFFS_FOR_PAGE)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            resultSet = preparedStatement.executeQuery();
            TariffMapper tariffMapper = new TariffMapper();

            while (resultSet.next()) {
                tariffList.add(tariffMapper.extractFromResultSet(resultSet));
            }

            return tariffList;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
        }
    }

    @Override
    public void update(Tariff tariff) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TARIFFS)) {
            preparedStatement.setString(1, tariff.getNameTariff());
            preparedStatement.setLong(2, tariff.getIdServices());
            preparedStatement.setBigDecimal(3, tariff.getCost());
            preparedStatement.setLong(3, tariff.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot update tariff " + tariff, e);
        }
    }

    @Override
    public void delete(long id) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TARIFF_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot update tariff " + id, e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tariff> findAllTariffFromOneServiceAndSorted(long idService, String sort) {
        ResultSet resultSet = null;
        List<Tariff> tariffList = new ArrayList<>();
        PreparedStatement pst = null;
        try {

            if (SORT_BY_NAME.equals(sort)) {
                pst = connection.prepareStatement((SQL_FIND_ALL_TARIFFS_SORT_BY_NAME));
            } else if (SORT_BY_NAME_REVERSE.equals(sort)) {
                pst = connection.prepareStatement((SQL_FIND_ALL_TARIFFS_SORT_BY_NAME_REVERSE));
            } else if (SORT_BY_COAST.equals(sort)) {
                pst = connection.prepareStatement((SQL_FIND_ALL_TARIFFS_SORT_BY_COAST));
            } else {
                pst = connection.prepareStatement(SQL_FIND_ALL_TARIFFS_BY_SERVICE);
            }

            pst.setLong(1, idService);
            resultSet = pst.executeQuery();

            TariffMapper tariffMapper = new TariffMapper();
            while (resultSet.next()) {
                tariffList.add(tariffMapper.extractFromResultSet(resultSet));
            }
            return tariffList;

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        } finally {
            close(resultSet);
            close(pst);
            close(connection);
        }
    }
}
