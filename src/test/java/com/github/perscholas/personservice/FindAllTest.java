package com.github.perscholas.personservice;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.JdbcConfigurator;
import com.github.perscholas.dao.PersonRepository;
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
        JdbcConfigurator configurator = new JdbcConfigurator(databaseConnection);
        configurator.appendSqlScript("testing.person_create-table.sql");
        configurator.appendSqlScript("testing.person_populate-table.sql");
        configurator.initialize();
    }

    @Test
    public void test() {
        // given
        PersonService personService = new PersonService(new PersonRepository(databaseConnection));

        // when
        List<Person> personList = personService.findAll();

        // then
        Assert.assertFalse(personList.isEmpty());
    }
}
