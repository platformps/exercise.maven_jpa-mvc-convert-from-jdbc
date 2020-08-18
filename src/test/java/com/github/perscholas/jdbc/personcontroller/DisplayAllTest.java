package com.github.perscholas.jdbc.personcontroller;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.config.ConfigurationInterface;
import com.github.perscholas.config.JdbcConfigurator;
import com.github.perscholas.controllers.PersonController;
import com.github.perscholas.dao.PersonJdbcRepository;
import com.github.perscholas.service.PersonService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by leon on 8/14/2020.
 */
public class DisplayAllTest {
    private DatabaseConnection databaseConnection;

    @Before
    public void setup() {
        // given
        this.databaseConnection = DatabaseConnection.TESTING_DATABASE;
        databaseConnection.drop();
        databaseConnection.create();
        databaseConnection.use();
        ConfigurationInterface configurator = new JdbcConfigurator(databaseConnection);
        configurator.appendSqlScript("testing.person_create-table.sql");
        configurator.appendSqlScript("testing.person_populate-table.sql");
        configurator.initialize();
    }

    @Test
    public void test() {
        // given
        PersonController personController = new PersonController(new PersonService(new PersonJdbcRepository(databaseConnection)));

        // when
        personController.displayAll();

        // then

    }
}