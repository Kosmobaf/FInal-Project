package com.model.dao.impl;
import com.model.dao.ServiceDao;
import com.model.entity.Service;

import java.sql.Connection;
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
    public Service findById(long id) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close(AutoCloseable autoCloseable) {

    }


}
