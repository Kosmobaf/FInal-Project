package com.model.dao.impl;
import com.model.dao.ServiceDao;
import com.model.entity.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JDBCServiceDao implements ServiceDao {
    private Connection connection;

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    private static final Logger LOGGER = Logger.getLogger(JDBCServiceDao.class.getName());

    @Override
    public void create(Service entity) {

    }

    @Override
    public Service findById(int id) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public void update(Service entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
