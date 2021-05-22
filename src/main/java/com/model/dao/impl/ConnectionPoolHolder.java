package com.model.dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.logging.Logger;

public final class ConnectionPoolHolder {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolHolder.class.getName());
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    Context context;
                    try {
                        context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:comp/env/jdbc/provider");
                    } catch (Exception e) {
                        LOGGER.severe(e.getMessage());
                    }
                }
            }
        }
        return dataSource;
    }
}


