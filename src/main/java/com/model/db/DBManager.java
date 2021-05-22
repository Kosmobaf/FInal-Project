package com.model.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Logger;

public final class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());
    private static DBManager dbManager;

    public static DBManager getInstance() {
        DBManager result = dbManager;
        if (result != null) {
            return result;
        }
        synchronized (DBManager.class) {
            if (dbManager == null) {
                dbManager = new DBManager();
                new CreateDataBase(dbManager).createDB();
            }
            return dbManager;
        }
    }

    public Connection getConnection() {

        Context context;
        Connection connection = null;
        DataSource dataSource;
        try {
            context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/provider");
            connection = dataSource.getConnection();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return connection;
    }
    public static void close(AutoCloseable closeable){
        try {
            closeable.close();
        }
        catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
    public static void rollbackAndCloseConnection(Connection connection){
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}


