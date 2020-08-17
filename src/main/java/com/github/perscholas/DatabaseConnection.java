package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.*;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    PRODUCTION_DATABASE(new ConnectionBuilder()
            .setUser("root")
            .setPassword("")
            .setPort(3306)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1")),

    TESTING_DATABASE(new ConnectionBuilder()
            .setUser("root")
            .setPassword("")
            .setPort(3306)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1"));

    private static final IOConsole successConsole = new IOConsole(IOConsole.AnsiColor.CYAN);
    private volatile ConnectionBuilder connectionBuilder;

    DatabaseConnection(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public String getDatabaseName() {
        return name().toLowerCase();
    }

    @Override
    public Connection getDatabaseConnection() {
        return connectionBuilder
                .setDatabaseName(name().toLowerCase())
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void drop() {
        try {
            String sqlStatement = "DROP DATABASE IF EXISTS " + name().toLowerCase() + ";";
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            successConsole.println(successMessage);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void create() {
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + name().toLowerCase() + ";";
        try {
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            successConsole.println(successMessage);
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to connect to execute statement\n\t`%s`", sqlStatement);
            throw new Error(errorMessage, e);
        }
    }

    @Override
    public void use() {
        try {
            String sqlStatement = "USE " + name().toLowerCase() + ";";
            getDatabaseEngineConnection()
                    .prepareStatement(sqlStatement)
                    .execute();
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            successConsole.println(successMessage);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            sqlStatement = sqlStatement.trim();
            getScrollableStatement().execute(sqlStatement);
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            successConsole.println(successMessage);
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to execute statement \n\t`%s`", sqlStatement);
            throw new Error(errorMessage, e);
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            sqlQuery = sqlQuery.trim();
            ResultSet result = getScrollableStatement().executeQuery(sqlQuery);
            String successMessage = String.format("Successfully executed query \n\t`%s`", sqlQuery);
            successConsole.println(successMessage);
            return result;
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to execute query \n\t`%s`", sqlQuery);
            throw new Error(errorMessage, e);
        }
    }

    private Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new Error(e);
        }
    }
}
