package com.github.perscholas.config;

import com.github.perscholas.DatabaseConnectionInterface;
import org.mariadb.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by leon on 8/13/2020.
 */
public class JdbcConfigurator extends AbstractConfiguration {
    private final DatabaseConnectionInterface dbc;

    public JdbcConfigurator(DatabaseConnectionInterface databaseConnection, String... scriptFiles) {
        super(scriptFiles);
        try { // Attempt to register JDBC Driver
            DriverManager.registerDriver(Driver.class.newInstance());
            this.dbc = databaseConnection;
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error(e1);
        }
    }

    @Override
    public void executeStatement(String statement, Object... arguments) {
        dbc.executeStatement(String.format(statement, arguments));
    }
}
