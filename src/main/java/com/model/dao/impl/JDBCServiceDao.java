package com.model.dao.impl;
import com.model.dao.ServiceDao;
import com.model.dao.mapper.ServiceMapper;
import com.model.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCServiceDao implements ServiceDao {
    private final Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCServiceDao.class.getName());
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
    public void create(Service service) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_INSERT_SERVICE)) {
            preparedStatement.setString(1, service.getNameService());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.severe(e.getMessage());
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
            LOGGER.severe(e.getMessage());
        } finally {
            close(resultSet);
        }
        //TODO переробити  дабл
        //TODO що має бути щоб не вертати нул ? перерробити мабуть сущності

        return null;
    }

    @Override
    public void update(Service service) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SERVICE)) {
            preparedStatement.setString(1, service.getNameService());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }


    @Override
    public void delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SERVICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
