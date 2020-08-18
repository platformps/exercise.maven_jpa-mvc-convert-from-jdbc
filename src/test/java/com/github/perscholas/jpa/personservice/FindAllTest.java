package com.github.perscholas.jpa.personservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.config.ConfigurationInterface;
import com.github.perscholas.config.JdbcConfigurator;
import com.github.perscholas.dao.PersonJpaRepository;
import com.github.perscholas.model.Person;
import com.github.perscholas.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by leon on 8/14/2020.
 */
public class FindAllTest {
    private DatabaseConnection databaseConnection;

    @Before
    public void setup() {
        // given
        this.databaseConnection = DatabaseConnection.TESTING_DATABASE;
        ConfigurationInterface configurator = new JdbcConfigurator(databaseConnection);
        configurator.appendSqlScript("testing.person_create-table.sql");
        configurator.appendSqlScript("testing.person_populate-table.sql");
        configurator.initialize();
    }

    @Test
    public void test() {
        // given
        PersonService personService = new PersonService(new PersonJpaRepository("testing"));

        // when
        List<Person> personList = personService.findAll();

        // then
        Assert.assertFalse(personList.isEmpty());
    }
}
