package com.model.dao.impl;

import com.controller.MyException;
import com.model.dao.dao_factory.ServiceDao;
import com.model.dao.mapper.ServiceMapper;
import com.model.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class JDBCServiceDao implements ServiceDao {
    private static final Logger log = Logger.getLogger(JDBCServiceDao.class);

    private final Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_SERVICE =
            "INSERT INTO services (nameService) VALUES (?)";
    public static final String SQL_FIND_SERVICE_BY_ID =
            "SELECT * FROM services WHERE id LIKE (?)";
    public static final String SQL_FIND_ALL_SERVICES =
            "SELECT * FROM services";
    public static final String SQL_UPDATE_SERVICE =
            "UPDATE services SET nameService = ? WHERE id = ?";
    public static final String SQL_DELETE_SERVICE_BY_ID =
            "DELETE FROM services WHERE id=?";

    @Override
    public void create(Service service) throws MyException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_SERVICE)) {

            preparedStatement.setString(1, service.getNameService());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot create service" + service, e);
        }
    }

    @Override
    public Service findById(long id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_SERVICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ServiceMapper serviceMapper = new ServiceMapper();
                return serviceMapper.extractFromResultSet(resultSet);
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
    public List<Service> findAll() {
        ResultSet resultSet = null;
        List<Service> tariffList = new ArrayList<>();

        try (Statement st = connection.createStatement()) {

            resultSet = st.executeQuery((SQL_FIND_ALL_SERVICES));
            ServiceMapper serviceMapper = new ServiceMapper();

            while (resultSet.next()) {
                tariffList.add(serviceMapper.extractFromResultSet(resultSet));
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
    public void update(Service service) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SERVICE)) {
            preparedStatement.setString(1, service.getNameService());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot update service " + service, e);
        }
    }


    @Override
    public void delete(long id) throws MyException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SERVICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new MyException("Cannot delete service " + id, e);
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
}
