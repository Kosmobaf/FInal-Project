package com.model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public final class ConnectionPoolHolder {
    private ConnectionPoolHolder() {
    }

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
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                }
            }
        }
        return dataSource;
    }
}


