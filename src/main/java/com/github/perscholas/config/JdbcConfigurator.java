package com.github.perscholas.config;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.DatabaseConnectionInterface;
import com.github.perscholas.dao.PersonJpaRepository;
import com.github.perscholas.model.Person;
import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;
import org.mariadb.jdbc.Driver;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leon on 8/13/2020.
 */
public class JdbcConfigurator {
    private final DatabaseConnectionInterface dbc;
    private List<String> scriptFiles;

    public JdbcConfigurator() {
        this(DatabaseConnection.PRODUCTION_DATABASE);
    }

    public JdbcConfigurator(DatabaseConnectionInterface databaseConnection, String... scriptFiles) {
        // Attempt to register JDBC Driver
        try {
            DriverManager.registerDriver(Driver.class.newInstance());
            this.scriptFiles = new ArrayList<>(Arrays.asList(scriptFiles));
            this.dbc = databaseConnection;
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error(e1);
        }
    }

    public void initialize() {
        dbc.drop();
        dbc.create();
        dbc.use();
        scriptFiles.forEach(this::executeSqlFile);
    }

    public void appendSqlScript(String fileName) {
        scriptFiles.add(fileName);
    }

    private void executeSqlFile(String fileName) {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory(fileName);
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] statements = fileReader.toString().split(";");
        for (int i = 0; i < statements.length; i++) {
            String statement = statements[i];
            dbc.executeStatement(statement);
        }
    }
}
