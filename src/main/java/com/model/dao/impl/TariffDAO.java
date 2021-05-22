package com.model.dao.impl;

import com.model.dao.TariffDao;
import com.model.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TariffDAO implements TariffDao {
    private Connection connection;

    public TariffDAO(Connection connection)  {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(TariffDAO.class.getName());

    public  List<Tariff> getAllTariffForOneService(String service) {
        List<Tariff> tariffList = new ArrayList<>();
        String query = "SELECT * FROM tariff WHERE nameService = ?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, service);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tariffList.add(new Tariff(resultSet.getString(""), resultSet.getDouble("cost")));
            }
            return tariffList;
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void create(Tariff entity) {

    }

    @Override
    public Tariff findById(int id) {
        return null;
    }

    @Override
    public List<Tariff> findAll() {
        return null;
    }

    @Override
    public void update(Tariff entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
