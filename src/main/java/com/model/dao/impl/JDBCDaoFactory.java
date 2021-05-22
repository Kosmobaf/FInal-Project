package com.model.dao.impl;

import com.model.dao.DaoFactory;
import com.model.dao.ServiceDao;
import com.model.dao.TariffDao;
import com.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServiceDao createServiceDao() {
        return new JDBCServiceDao(getConnection());
    }

    @Override
    public TariffDao createTariffDao() {
        return new TariffDAO(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new UserDAO(getConnection());
    }
}
