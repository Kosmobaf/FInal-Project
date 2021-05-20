package com.sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Logger;

public class CreateDataBase {
    DBManager dbManager;
    private static final String PATH_TO_DB_CREATE = "db-create.sql";
    private static final Logger LOGGER = Logger.getLogger(CreateDataBase.class.getName());

    public CreateDataBase(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void createDB() {
        try (Statement statement = Objects.requireNonNull(dbManager.getConnection()).
                createStatement()) {
            for (String s : getScenario()) {
                statement.executeUpdate(s);
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public String[] getScenario() {
        StringBuilder s = new StringBuilder();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(PATH_TO_DB_CREATE))) {
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                s.append(tmp);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return s.toString().split(";");
    }
}
