package com.dao;

import com.entity.Tariff;
import com.sql.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO {

    public List<Tariff> getAllTariffForOneService(String service) {
        List<Tariff> tariffList = new ArrayList<>();
        String query = "SELECT * FROM tariff WHERE nameService = ?";
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
}
