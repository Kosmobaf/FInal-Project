package com.sql;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class.getName());

    private ConnectionPool(){
        //private constructor
    }
    private static ConnectionPool instance = null;
    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Context ctx = null;
        Connection c = null;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/provider");
            c = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}