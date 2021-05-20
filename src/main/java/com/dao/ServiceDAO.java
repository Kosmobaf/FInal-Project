package com.dao;

import com.entity.Service;
import com.sql.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    public List<Service> getAllServicesAndTariff() {
        List<Service> serviceList = new ArrayList<>();
        String query = "SELECT * FROM services nameService ";
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                serviceList.add(new Service(resultSet.getString("nameService"),
                        getAllTariffForOneService(resultSet.getString("nameService"))));
            }
            return serviceList;
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
